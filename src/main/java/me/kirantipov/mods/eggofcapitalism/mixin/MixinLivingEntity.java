package me.kirantipov.mods.eggofcapitalism.mixin;

import me.kirantipov.mods.eggofcapitalism.client.DamageableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Mixin that implements DamageableEntity interface for the LivingEntity class.
 */
@Mixin(LivingEntity.class)
public class MixinLivingEntity implements DamageableEntity {
    @Shadow
    private DamageSource lastDamageSource;

    /**
     * {@inheritDoc}
     */
    public DamageSource getLastDamageSource() {
        return lastDamageSource;
    }
}