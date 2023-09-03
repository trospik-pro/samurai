package dev.xhyrom.samurai.block;

import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;

@UtilityClass
public class Blocks {
    public static void init() {
        MinecraftServer.getBlockManager().registerHandler("minecraft:banner", BannerBlockHandler::new);
    }
}
