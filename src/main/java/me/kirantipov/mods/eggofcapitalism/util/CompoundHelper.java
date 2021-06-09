package me.kirantipov.mods.eggofcapitalism.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.Tag;

import java.util.Collection;
import java.util.UUID;

/**
 * Provides some useful methods for working with compound tags.
 */
public final class CompoundHelper {
    /**
     * Reads a UUID list from the specified key.
     *
     * @param tag The tag.
     * @param key The key.
     * @param collection Target collection.
     */
    public static void copyUuidListTo(CompoundTag tag, String key, Collection<UUID> collection) {
        final int INT_ARRAY_TYPE = 11;
        ListTag list = tag.getList(key, INT_ARRAY_TYPE);
        for (Tag listEntry : list) {
            collection.add(NbtHelper.toUuid(listEntry));
        }
    }

    /**
     * Puts a UUID list at the specified key.
     *
     * @param tag The tag.
     * @param key The key.
     * @param collection The collection to retrieve data from.
     */
    public static void putUuidList(CompoundTag tag, String key, Collection<UUID> collection) {
        ListTag listTag = new ListTag();
        for (UUID uuid : collection) {
            listTag.add(NbtHelper.fromUuid(uuid));
        }
        tag.put(key, listTag);
    }
}
