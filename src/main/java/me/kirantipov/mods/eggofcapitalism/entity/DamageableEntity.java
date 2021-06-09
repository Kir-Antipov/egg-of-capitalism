package me.kirantipov.mods.eggofcapitalism.entity;

import net.minecraft.entity.Entity;

/**
 * Represents an entity that can take damage.
 */
public interface DamageableEntity {
    /**
     * Returns the killer of the entity, if any;
     * otherwise, null.
     *
     * @return The killer of the entity, if any; otherwise, null.
     */
    Entity getKiller();
}
