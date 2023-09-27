package dev.xhyrom.samurai.util;

import java.util.HashMap;

public class ServerPlayerCount {
    private static final HashMap<String, Integer> SERVER_PLAYER_COUNT = new HashMap<>();

    public static Integer get(String server) {
        String[] servers = server.split(",");

        int count = 0;

        for (String s : servers) {
            count += SERVER_PLAYER_COUNT.getOrDefault(s, 0);
        }

        return count;
    }

    public static void set(String server, Integer count) {
        SERVER_PLAYER_COUNT.put(server, count);
    }
}
