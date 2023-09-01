package dev.xhyrom.samurai.world;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import lombok.experimental.UtilityClass;
import net.hollowcube.polar.PolarLoader;
import net.minestom.server.instance.WorldBorder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@UtilityClass
public final class Worlds {
    public static void init() {
        Path worldPath = SamuraiBootstrap.getPath("worlds/hub.polar");
        File world = worldPath.toFile();

        if (!world.exists()) {
            throw new RuntimeException("Missing hub world, please place a Polar world at " + worldPath + " and restart the server");
        }

        try {
            Samurai.instance.setChunkLoader(new PolarLoader(worldPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load hub world", e);
        }

        {
            WorldBorder border = Samurai.instance.getWorldBorder();

            border.setCenter(Samurai.config.worldBorder.x,Samurai.config.worldBorder.z);
            border.setDiameter(Samurai.config.worldBorder.diameter);
        }

        Samurai.instance.setTime(0);
        Samurai.instance.setTimeRate(0);

        Samurai.logger.info("Successfully loaded world: " + worldPath);
    }
}
