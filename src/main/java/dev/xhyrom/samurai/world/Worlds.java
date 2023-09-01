package dev.xhyrom.samurai.world;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import lombok.experimental.UtilityClass;
import net.hollowcube.polar.PolarLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@UtilityClass
public final class Worlds {
    public static void init() {
        Path worldPath = SamuraiBootstrap.getPath("worlds/hub.polar");
        File world = worldPath.toFile();

        if (!world.exists()) {
            throw new RuntimeException("Missing hub world, please place a Slime world at " + worldPath + " and restart the server");
        }

        /*Samurai.instance.setChunkLoader(new SlimeLoader(Samurai.instance, new FileSlimeSource(world), true));*/
        try {
            Samurai.instance.setChunkLoader(new PolarLoader(worldPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Samurai.instance.setTime(0);
        Samurai.instance.setTimeRate(0);

        Samurai.logger.info("Loaded Hub world");
    }
}
