package dev.xhyrom.samurai;

import dev.xhyrom.okaeri.serdes.minestom.SerdesMinestom;
import dev.xhyrom.samurai.block.Blocks;
import dev.xhyrom.samurai.commands.Commands;
import dev.xhyrom.samurai.config.Config;
import dev.xhyrom.samurai.config.serializers.SerdesCustom;
import dev.xhyrom.samurai.entity.Entities;
import dev.xhyrom.samurai.listeners.Listeners;
import dev.xhyrom.samurai.listeners.RedisPubSubListener;
import dev.xhyrom.samurai.module.PlayerScoreboard;
import dev.xhyrom.samurai.util.Dimension;
import dev.xhyrom.samurai.util.LuckPermsAccessor;
import dev.xhyrom.samurai.util.VelocityBridge;
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
import net.minestom.server.timer.ExecutionType;
import redis.clients.jedis.*;

import java.nio.file.Path;
import java.util.logging.Logger;

@UtilityClass
public final class Samurai {
    public static ExtensionBootstrap server;
    public static Jedis redisPub;
    public static Jedis redisSub;
    private static JedisPool jedisPool;

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
        Blocks.init();
        Worlds.init();
        Entities.init();
        PlayerScoreboard.init();

        try {
            jedisPool = new JedisPool(new HostAndPort(config.redis.host, config.redis.port), DefaultJedisClientConfig.builder()
                    .password(config.redis.password.isEmpty() ? null : config.redis.password)
                    .build());

            redisPub = jedisPool.getResource();
            redisSub = jedisPool.getResource();

            MinecraftServer.getSchedulerManager().buildTask(() -> {
                redisSub.subscribe(new RedisPubSubListener(), "vspc");
            }).executionType(ExecutionType.ASYNC).schedule();

            // Close jedis pool on shutdown
            MinecraftServer.getSchedulerManager().buildShutdownTask(() -> {
                jedisPool.close();
            });
        } catch (Exception e) {
            logger.severe("Failed to connect to Redis: ");
            logger.throwing("Samurai", "init", e);
        }
    }

    private static void postInit() {
        // Init luckperms accessor
        try {
            LuckPermsAccessor.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void run() {
        String[] address = config.address.split(":");
        String ip = address[0];
        int port = address.length > 1 ? Integer.parseInt(address[1]) : 25565;

        server.start(ip, port);

        VelocityBridge.fetchServerPlayerCounts();

        postInit();
    }
}
