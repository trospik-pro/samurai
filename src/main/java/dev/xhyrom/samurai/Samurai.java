package dev.xhyrom.samurai;

import dev.xhyrom.samurai.listeners.PlayerLogin;
import dev.xhyrom.samurai.util.FullbrightDimension;
import gg.astromc.slimeloader.loader.SlimeLoader;
import gg.astromc.slimeloader.source.FileSlimeSource;
import gg.astromc.slimeloader.source.SlimeSource;
import net.hollowcube.polar.PolarLoader;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;
import net.minestom.server.world.biomes.Biome;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Random;

public class Samurai {
    public static InstanceContainer instanceContainer;

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        EventNode<Event> entityNode = EventNode.type("listeners", EventFilter.ALL);
        entityNode
                .addListener(new PlayerLogin());
        globalEventHandler.addChild(entityNode);

        initWorlds();

        // Start the server on port 25565
        minecraftServer.start("0.0.0.0", 25565);
    }

    public static Path getPath(String path) {
        try {
            return Path.of(
                            new File(
                                    Samurai.class
                                            .getProtectionDomain()
                                            .getCodeSource()
                                            .getLocation()
                                            .toURI()
                            ).getPath()
                    ).getParent()
                    .resolve(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initWorlds() {
        // Fail and stop server if hub doesn't exist
        if (!getPath("config/worlds/hub.polar").toFile().exists()) {
            System.out.println("Missing hub world, please place a Polar world at ./config/worlds/hub.polar and restart the server");
            MinecraftServer.stopCleanly();
            return;
        }

        // Load hub now we know it exists
        instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer(FullbrightDimension.INSTANCE);/*MinecraftServer.getInstanceManager().createInstanceContainer(
                FullbrightDimension.INSTANCE,
                new PolarLoader(getPath("config/worlds/hub.polar"))
        );*/

        instanceContainer.setChunkLoader(new SlimeLoader(instanceContainer, new FileSlimeSource(getPath("config/worlds/hub.slime").toFile()), true));

        System.out.println("Loaded Hub world");
        instanceContainer.setTimeRate(0);
    }
}
