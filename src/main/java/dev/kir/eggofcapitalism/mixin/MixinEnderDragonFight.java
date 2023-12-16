package dev.kir.eggofcapitalism.mixin;

import dev.kir.eggofcapitalism.util.CompoundHelper;
import dev.kir.eggofcapitalism.util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
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
    private @Shadow UUID dragonUuid;

    private @Shadow @Final ServerWorld world;

    private Set<UUID> dragonKilledBy;

    private static final String DRAGON_KILLED_BY_TAG_NAME = "DragonKilledBy";

    /**
     * Initializes `dragonKilledBy` field.
     *
     * @param world The world.
     * @param l Random seed.
     * @param compoundTag NBT that can be used to restore the current state of the EnderDragonFight object.
     * @param ci Callback information.
     */
    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;JLnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
    private void readDragonKilledByFromTag(ServerWorld world, long l, NbtCompound compoundTag, CallbackInfo ci) {
        this.dragonKilledBy = new HashSet<>();

        if (compoundTag.contains(DRAGON_KILLED_BY_TAG_NAME)) {
            CompoundHelper.copyUuidListTo(compoundTag, DRAGON_KILLED_BY_TAG_NAME, this.dragonKilledBy);
        }
    }

    /**
     * Adds `dragonKilledBy` to the NBT.
     *
     * @param cir Callback information.
     */
    @Inject(method = "toNbt()Lnet/minecraft/nbt/NbtCompound;", at = @At("RETURN"))
    private void writeDragonKilledByToTag(CallbackInfoReturnable<NbtCompound> cir) {
        CompoundHelper.putUuidList(cir.getReturnValue(), DRAGON_KILLED_BY_TAG_NAME, this.dragonKilledBy);
    }

    /**
     * Generates a dragon egg if all conditions are met.
     *
     * @param dragon The dragon that was slain.
     * @param ci Callback information.
     */
    @Inject(method = "dragonKilled", at = @At("TAIL"))
    private void generateDragonEggIfNeeded(EnderDragonEntity dragon, CallbackInfo ci) {
        if (!dragon.getUuid().equals(this.dragonUuid)) {
            return;
        }

        Entity killer = EntityHelper.getKiller(dragon);
        if (!(killer instanceof PlayerEntity) || this.dragonKilledBy.contains(killer.getUuid())) {
            return;
        }

        this.dragonKilledBy.add(killer.getUuid());

        // If the egg hasn't been generated yet by something else (e.g., another mod), generate it now.
        BlockPos endPortalTop = this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, EndPortalFeature.ORIGIN);
        BlockState existingState = this.world.getBlockState(endPortalTop.offset(Direction.DOWN));
        if (existingState == null || !existingState.isOf(Blocks.DRAGON_EGG)) {
            this.world.setBlockState(endPortalTop, Blocks.DRAGON_EGG.getDefaultState());
        }
    }
}
