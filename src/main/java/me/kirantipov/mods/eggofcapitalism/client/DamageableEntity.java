package me.kirantipov.mods.eggofcapitalism.client;

import net.minecraft.entity.damage.DamageSource;

/**
 * Represents an entity that can take damage.
 */
public interface DamageableEntity {

    /**
     * Returns the last damage source, if present;
     * otherwise null.
     *
     * @return The last damage source, if any; otherwise null.
     */
    DamageSource getLastDamageSource();
}
