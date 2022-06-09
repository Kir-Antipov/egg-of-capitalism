package dev.kir.eggofcapitalism.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;

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
    public static void copyUuidListTo(NbtCompound tag, String key, Collection<UUID> collection) {
        NbtList list = tag.getList(key, NbtElement.INT_ARRAY_TYPE);
        for (NbtElement listEntry : list) {
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
    public static void putUuidList(NbtCompound tag, String key, Collection<UUID> collection) {
        NbtList listTag = new NbtList();
        for (UUID uuid : collection) {
            listTag.add(NbtHelper.fromUuid(uuid));
        }
        tag.put(key, listTag);
    }
}
