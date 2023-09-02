package dev.xhyrom.samurai.module;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.util.Scoreboard;
import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@UtilityClass
public class PlayerScoreboard {
    private final HashMap<UUID, Scoreboard> scoreboards = new HashMap<>();

    public void create(Player player) {
        Scoreboard scoreboard = new Scoreboard(player, Samurai.config.scoreboard.title, Samurai.config.scoreboard.lines);
        scoreboards.put(player.getUuid(), scoreboard);

        scoreboard.show(player);
    }

    public void updateLine(Player player, int line, String text) {
        scoreboards.get(player.getUuid()).updateLine(player, line, text);
    }

    public void updateLine(int line, String text) {
        scoreboards.forEach((uuid, scoreboard) -> {
            Player player = MinecraftServer.getConnectionManager().getPlayer(uuid);
            scoreboard.updateLine(player, line, text);
        });
    }

    public void destroy(Player player) {
        scoreboards.remove(player.getUuid());
    }
}
