package dev.kir.eggofcapitalism.mixin;

import dev.kir.eggofcapitalism.entity.boss.dragon.EnderDragonFightDataExtension;
import dev.kir.eggofcapitalism.util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.player.PlayerEntity;
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

import java.util.Set;
import java.util.UUID;

@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin {
    private @Shadow UUID dragonUuid;

    private @Shadow @Final ServerWorld world;

    private @Shadow @Final BlockPos origin;

    private Set<UUID> dragonKilledBy;

    @Inject(method = "<init>(Lnet/minecraft/server/world/ServerWorld;JLnet/minecraft/entity/boss/dragon/EnderDragonFight$Data;Lnet/minecraft/util/math/BlockPos;)V", at = @At("RETURN"))
    private void readDragonKilledBy(ServerWorld world, long gatewaysSeed, EnderDragonFight.Data data, BlockPos origin, CallbackInfo ci) {
        this.dragonKilledBy = ((EnderDragonFightDataExtension)(Object)data).getDragonKilledBy();
    }

    @Inject(method = "toData()Lnet/minecraft/entity/boss/dragon/EnderDragonFight$Data;", at = @At("RETURN"))
    private void writeDragonKilledBy(CallbackInfoReturnable<EnderDragonFight.Data> cir) {
        ((EnderDragonFightDataExtension)(Object)cir.getReturnValue()).setDragonKilledBy(this.dragonKilledBy);
    }

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
        BlockPos endPortalTop = this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, EndPortalFeature.offsetOrigin(this.origin));
        BlockState existingState = this.world.getBlockState(endPortalTop.offset(Direction.DOWN));
        if (existingState == null || !existingState.isOf(Blocks.DRAGON_EGG)) {
            this.world.setBlockState(endPortalTop, Blocks.DRAGON_EGG.getDefaultState());
        }
    }
}
