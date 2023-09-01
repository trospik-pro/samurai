package dev.xhyrom.samurai.world;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import gg.astromc.slimeloader.loader.SlimeLoader;
import gg.astromc.slimeloader.source.FileSlimeSource;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Path;

@UtilityClass
public final class Worlds {
    public static void init() {
        Path worldPath = SamuraiBootstrap.getPath("worlds/hub.slime");
        File world = worldPath.toFile();

        if (!world.exists()) {
            throw new RuntimeException("Missing hub world, please place a Slime world at " + worldPath + " and restart the server");
        }

        Samurai.instance.setChunkLoader(new SlimeLoader(Samurai.instance, new FileSlimeSource(world), true));

        Samurai.instance.setTime(0);
        Samurai.instance.setTimeRate(0);

        Samurai.logger.info("Loaded Hub world");
    }
}
