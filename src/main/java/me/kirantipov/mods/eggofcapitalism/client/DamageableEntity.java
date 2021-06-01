package me.kirantipov.mods.eggofcapitalism.client;

import net.minecraft.entity.damage.DamageSource;

public interface DamageableEntity {
    DamageSource getLastDamageSource();
}
