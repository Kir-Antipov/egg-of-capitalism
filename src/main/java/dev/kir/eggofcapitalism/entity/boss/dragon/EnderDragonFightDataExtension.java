package dev.kir.eggofcapitalism.entity.boss.dragon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Uuids;

import java.util.*;

public interface EnderDragonFightDataExtension {
    Codec<EnderDragonFightDataExtension> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                Uuids.INT_STREAM_CODEC.listOf().optionalFieldOf("DragonKilledBy", List.of())
                    .xmap(x -> (Set<UUID>)new HashSet<>(x), ArrayList::new)
                    .forGetter(EnderDragonFightDataExtension::getDragonKilledBy)
            )
            .apply(instance, EnderDragonFightDataExtension::create));

    static EnderDragonFightDataExtension create(Set<UUID> uuids) {
        return new EnderDragonFightDataExtension() {
            private Set<UUID> dragonKilledBy = uuids;

            @Override
            public Set<UUID> getDragonKilledBy() {
                return this.dragonKilledBy;
            }

            @Override
            public void setDragonKilledBy(Set<UUID> dragonKilledBy) {
                this.dragonKilledBy = dragonKilledBy;
            }
        };
    }

    Set<UUID> getDragonKilledBy();

    void setDragonKilledBy(Set<UUID> dragonKilledBy);
}