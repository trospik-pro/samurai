package dev.xhyrom.samurai.module;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.module.scoreboard.Scoreboard;
import dev.xhyrom.samurai.module.scoreboard.ScoreboardLine;
import dev.xhyrom.samurai.module.scoreboard.ScoreboardLineGroup;
import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.TaskSchedule;

import java.util.HashMap;
import java.util.UUID;

@UtilityClass
public class PlayerScoreboard {
    private final HashMap<UUID, Scoreboard> scoreboards = new HashMap<>();

    public void init() {
        for (ScoreboardLineGroup group : Samurai.config.scoreboard.groups) {
            if (group.getRefreshRate() == 0)
                continue;

            MinecraftServer.getSchedulerManager().buildTask(() -> {
                for (ScoreboardLine line : group.getLines()) {
                    updateLine(line);
                }
            }).repeat(TaskSchedule.tick(group.getRefreshRate())).schedule();
        }
    }

    public void create(Player player) {
        Scoreboard scoreboard = new Scoreboard(player, Samurai.config.scoreboard.title, Samurai.config.scoreboard.groups);
        scoreboards.put(player.getUuid(), scoreboard);

        scoreboard.show(player);
    }

    public void updateLine(ScoreboardLine line) {
        scoreboards.forEach((uuid, scoreboard) -> {
            Player player = MinecraftServer.getConnectionManager().getPlayer(uuid);
            scoreboard.updateLine(player, line);
        });
    }

    public void destroy(Player player) {
        scoreboards.remove(player.getUuid());
    }
}
