package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.util.ServerPlayerCount;
import redis.clients.jedis.JedisPubSub;

public class RedisPubSubListener extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        String[] serverNames = message.split(",");

        for (String serverName : serverNames) {
            if (serverName.isEmpty()) continue;

            String[] data = serverName.split(":");
            if (data.length != 2) continue;

            String name = data[0];
            Integer count = Integer.parseInt(data[1]);

            ServerPlayerCount.set(name, count);
        }
    }
}
