package me.kirantipov.mods.eggofcapitalism.mixin;

import me.kirantipov.mods.eggofcapitalism.entity.DamageableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Mixin that implements DamageableEntity interface for the LivingEntity class.
 */
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements DamageableEntity {
    @Shadow
    private DamageSource lastDamageSource;

    @Shadow
    public abstract LivingEntity getAttacker();

    /**
     * {@inheritDoc}
     */
    public Entity getKiller() {
        Entity attacker = this.getAttacker();
        if (attacker != null) {
            return attacker;
        }

        DamageSource source = this.lastDamageSource;
        if (source != null) {
            return source.getSource();
        }

        return null;
    }
}