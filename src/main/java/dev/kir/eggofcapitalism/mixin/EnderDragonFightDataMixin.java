package dev.kir.eggofcapitalism.mixin;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import dev.kir.eggofcapitalism.entity.boss.dragon.EnderDragonFightDataExtension;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.UUID;

@Mixin(EnderDragonFight.Data.class)
public abstract class EnderDragonFightDataMixin implements EnderDragonFightDataExtension {
    public static @Shadow @Final @Mutable Codec<EnderDragonFight.Data> CODEC;

    private Set<UUID> dragonKilledBy;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void registerDragonKilledBy(CallbackInfo ci) {
        CODEC = CODEC.mapResult(new Codec.ResultFunction<>() {
            @Override
            public <T> DataResult<Pair<EnderDragonFight.Data, T>> apply(DynamicOps<T> ops, T input, DataResult<Pair<EnderDragonFight.Data, T>> result) {
                return EnderDragonFightDataExtension.CODEC.decode(ops, input).ap(result.map(pair -> pairExt -> {
                    EnderDragonFight.Data data = pair.getFirst();
                    EnderDragonFightDataExtension dataExt = pairExt.getFirst();

                    ((EnderDragonFightDataExtension)(Object)data).setDragonKilledBy(dataExt.getDragonKilledBy());

                    return pair;
                }));
            }

            @Override
            public <T> DataResult<T> coApply(DynamicOps<T> ops, EnderDragonFight.Data input, DataResult<T> result) {
                return result.flatMap(prefix -> EnderDragonFightDataExtension.CODEC.encode((EnderDragonFightDataExtension)(Object)input, ops, prefix));
            }
        });
    }

    @Override
    public Set<UUID> getDragonKilledBy() {
        return this.dragonKilledBy;
    }

    @Override
    public void setDragonKilledBy(Set<UUID> dragonKilledBy) {
        this.dragonKilledBy = dragonKilledBy;
    }
}