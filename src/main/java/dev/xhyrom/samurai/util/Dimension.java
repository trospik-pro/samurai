package dev.xhyrom.samurai.util;

import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;
import net.minestom.server.world.biomes.Biome;

@UtilityClass
public final class Dimension {
    public static final DimensionType INSTANCE = DimensionType.builder(NamespaceID.from("samurai:hub"))
            .ambientLight(2.0f)
            .build();

    public static final Biome JUNGLE = Biome.builder()
            .category(Biome.Category.JUNGLE)
            .name(NamespaceID.from("minecraft:jungle"))
            .temperature(0.95f)
            .downfall(0.9f)
            .precipitation(Biome.Precipitation.NONE)
            .build();

    static {
        MinecraftServer.getDimensionTypeManager().addDimension(INSTANCE);
        MinecraftServer.getBiomeManager().addBiome(Dimension.JUNGLE);
    }
}
