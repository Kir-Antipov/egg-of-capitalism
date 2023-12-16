package dev.kir.eggofcapitalism.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;

/**
 * Provides some useful methods for working with entities.
 */
public final class EntityHelper {
    /**
     * Returns the killer of the entity, if any;
     * otherwise, null.
     *
     * @param entity The entity to identify a killer for.
     *
     * @return The killer of the entity, if any; otherwise, null.
     */
    public static Entity getKiller(LivingEntity entity) {
        if (entity == null) {
            return null;
        }

        Entity killer = entity.getAttacker();

        if (killer == null && entity.lastDamageSource != null) {
            killer = entity.lastDamageSource.getSource();
        }

        if (killer instanceof ProjectileEntity) {
            killer = ((ProjectileEntity)killer).getOwner();
        }

        return killer;
    }
}
