package dev.xhyrom.samurai.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.xhyrom.samurai.Samurai;
import lombok.experimental.UtilityClass;
import net.minestom.server.entity.Player;

import java.util.HashMap;

@UtilityClass
public class VelocityBridge {
    private static final HashMap<String, Integer> SERVER_PLAYER_COUNT = new HashMap<>();

    public static Integer getServerPlayerCount(String server) {
        String[] servers = server.split(",");

        int count = 0;

        for (String s : servers) {
            count += SERVER_PLAYER_COUNT.getOrDefault(s, 0);
        }

        return count;
    }

    public static void setServerPlayerCount(String server, int count) {
        if (count == 0) {
            SERVER_PLAYER_COUNT.remove(server);
            return;
        }

        SERVER_PLAYER_COUNT.put(server, count);
    }

    public static void fetchServerPlayerCounts() {
        if (Samurai.redisPub == null)
            return;

        Samurai.redisPub.publish("vspc-request", "get-players");
    }

    public static void sendPlayerToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        player.sendPluginMessage("bungeecord:main", out.toByteArray());
    }
}
