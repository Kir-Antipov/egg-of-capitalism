package me.kirantipov.mods.eggofcapitalism.mixin;

import me.kirantipov.mods.eggofcapitalism.entity.DamageableEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.Tag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Mixin that changes the logic of egg generation.
 */
@Mixin(EnderDragonFight.class)
public abstract class MixinEnderDragonFight {
    @Final
    @Shadow
    private ServerBossBar bossBar;

    @Shadow
    private UUID dragonUuid;

    @Shadow
    protected abstract void generateEndPortal(boolean previouslyKilled);

    @Shadow
    protected abstract void generateNewEndGateway();

    @Final
    @Shadow
    private ServerWorld world;

    @Shadow
    private boolean previouslyKilled;

    @Shadow
    private boolean dragonKilled;

    private Set<UUID> dragonKilledBy;
    private final String DRAGON_KILLED_BY_TAG_NAME = "DragonKilledBy";

    /**
     * Initializes `dragonKilledBy` field.
     *
     * @param world The world.
     * @param l Random seed.
     * @param compoundTag NBT that can be used to restore the current state of the EnderDragonFight object.
     * @param ci Callback information.
     */
    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;JLnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    private void onInit(ServerWorld world, long l, CompoundTag compoundTag, CallbackInfo ci) {
        dragonKilledBy = new HashSet<>();
        if (compoundTag.contains(DRAGON_KILLED_BY_TAG_NAME)) {
            final int INT_ARRAY_TYPE = 11;
            ListTag list = compoundTag.getList(DRAGON_KILLED_BY_TAG_NAME, INT_ARRAY_TYPE);
            for (Tag tag : list) {
                dragonKilledBy.add(NbtHelper.toUuid(tag));
            }
        }
    }

    /**
     * Adds `dragonKilledBy` to the NBT.
     *
     * @param cir Callback information.
     */
    @Inject(method = "toTag()Lnet/minecraft/nbt/CompoundTag;", at = @At("RETURN"), cancellable = true)
    private void addDragonKilledByToTag(CallbackInfoReturnable<CompoundTag> cir) {
        CompoundTag tag = cir.getReturnValue();

        ListTag listTag = new ListTag();
        for (UUID uuid : dragonKilledBy) {
            listTag.add(NbtHelper.fromUuid(uuid));
        }

        tag.put(DRAGON_KILLED_BY_TAG_NAME, listTag);
        cir.setReturnValue(tag);
    }

    /**
     * Handles the dragon kill event.
     *
     * @param dragon The dragon that was slain.
     *
     * @author Kir_Antipov
     *
     * @reason
     * Technically, it is possible to implement the mixin
     * without overwriting the whole method,
     * but it will be easier, cleaner and more reliable this way.
     */
    @Overwrite
    public void dragonKilled(EnderDragonEntity dragon) {
        if (dragon.getUuid().equals(this.dragonUuid)) {
            this.bossBar.setPercent(0.0F);
            this.bossBar.setVisible(false);
            this.generateEndPortal(true);
            this.generateDragonEggIfNeeded(dragon);
            this.generateNewEndGateway();
            this.previouslyKilled = true;
            this.dragonKilled = true;
        }
    }

    /**
     * Generates a dragon egg if all conditions are met.
     *
     * @param dragon The dragon that was slain.
     */
    private void generateDragonEggIfNeeded(EnderDragonEntity dragon) {
        Entity killer = getDragonKiller(dragon);
        if (killer instanceof PlayerEntity && !dragonKilledBy.contains(killer.getUuid())) {
            dragonKilledBy.add(killer.getUuid());
            this.world.setBlockState(
                this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, EndPortalFeature.ORIGIN),
                Blocks.DRAGON_EGG.getDefaultState()
            );
        }
    }

    /**
     * Returns the brave warrior who defeated the dragon.
     *
     * @param dragon The dragon that was slain.
     * @return The entity that dealt the final blow to the dragon, if any; otherwise, null.
     */
    private static Entity getDragonKiller(EnderDragonEntity dragon) {
        Entity attacker = dragon.getAttacker();
        if (attacker != null) {
            return attacker;
        }

        DamageSource source = ((DamageableEntity)dragon).getLastDamageSource();
        if (source != null) {
            return source.getSource();
        }

        return null;
    }
}
