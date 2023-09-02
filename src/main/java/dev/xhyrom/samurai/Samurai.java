package dev.xhyrom.samurai;

import dev.xhyrom.okaeri.serdes.minestom.SerdesMinestom;
import dev.xhyrom.samurai.commands.Commands;
import dev.xhyrom.samurai.config.Config;
import dev.xhyrom.samurai.config.serializers.SerdesCustom;
import dev.xhyrom.samurai.entity.Entities;
import dev.xhyrom.samurai.listeners.Listeners;
import dev.xhyrom.samurai.util.Dimension;
import dev.xhyrom.samurai.world.Worlds;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.experimental.UtilityClass;
import net.hollowcube.minestom.extensions.ExtensionBootstrap;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.bungee.BungeeCordProxy;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.instance.InstanceContainer;

import java.nio.file.Path;
import java.util.logging.Logger;

@UtilityClass
public final class Samurai {
    public static ExtensionBootstrap server;
    public static InstanceContainer instance;
    public static Config config;
    public static Logger logger = Logger.getLogger("Samurai");

    public static void init() {
        config = ConfigManager.create(Config.class, it -> {
            Path path = SamuraiBootstrap.getPath("config");
            path.toFile().mkdirs();

            it.withConfigurer(new YamlSnakeYamlConfigurer(), new SerdesMinestom(), new SerdesCustom());
            it.withBindFile(path.resolve("config.yml"));
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });

        server = ExtensionBootstrap.init();
        instance = MinecraftServer.getInstanceManager().createInstanceContainer(Dimension.INSTANCE);

        MinecraftServer.setBrandName(config.brand + " " + (config.debug ? "DEBUG" : ""));

        switch (Samurai.config.mode.mode) {
            case ONLINE -> MojangAuth.init();
            case VELOCITY -> VelocityProxy.enable(Samurai.config.mode.velocitySecret);
            case BUNGEECORD -> {
                if (!Samurai.config.mode.bungeeguardSecrets.isEmpty())
                    BungeeCordProxy.setBungeeGuardTokens(Samurai.config.mode.bungeeguardSecrets);

                BungeeCordProxy.enable();
            }
        }

        Commands.init();
        Listeners.init();
        Worlds.init();
        Entities.init();
    }

    public static void run() {
        String[] address = config.address.split(":");
        String ip = address[0];
        int port = address.length > 1 ? Integer.parseInt(address[1]) : 25565;

        server.start(ip, port);
    }
}
