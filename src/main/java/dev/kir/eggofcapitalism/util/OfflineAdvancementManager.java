package dev.kir.eggofcapitalism.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.WorldSavePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Gets access to the list of players' advancements regardless of whether they're online.
 */
public final class OfflineAdvancementManager {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Returns a set of UUIDs of players who have achieved the specified advancement.
     *
     * @param server The server.
     * @param advancementId Identifier of the advancement.
     *
     * @return a set of UUIDs of players who have achieved the specified advancement.
     */
    public static HashSet<UUID> findPlayersByAdvancement(MinecraftServer server, Identifier advancementId) {
        Path advancementsPath = server.getSavePath(WorldSavePath.ADVANCEMENTS);
        String id = advancementId.toString();
        try {
            return Files
                .walk(advancementsPath)
                .parallel()
                .filter(Files::isRegularFile)
                .map(x -> {
                    try {
                        JsonObject advancementEntry = JsonHelper.deserialize(Files.newBufferedReader(x));
                        JsonElement theEndAdvancement = advancementEntry.get(id);
                        if (theEndAdvancement instanceof JsonObject) {
                            JsonElement doneProperty = ((JsonObject)theEndAdvancement).get("done");
                            if (doneProperty != null && doneProperty.getAsBoolean()) {
                                String uuidString = x.getFileName().toString().replaceFirst("[.][^.]+$", "");
                                return UUID.fromString(uuidString);
                            }
                        }
                    } catch (Throwable e) {
                        // Java is shit
                        LOGGER.error(e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(HashSet::new));
        } catch (Throwable e) {
            LOGGER.error(e);
        }

        return new HashSet<>();
    }
}