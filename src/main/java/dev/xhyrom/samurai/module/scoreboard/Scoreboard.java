package dev.xhyrom.samurai.module.scoreboard;

import dev.xhyrom.samurai.util.Placeholders;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;

import java.util.List;

public class Scoreboard {
    private final String title;
    private final List<ScoreboardLineGroup> groups;

    private final Sidebar sidebar;

    public Scoreboard(Player player, String title, List<ScoreboardLineGroup> groups) {
        this.title = title;
        this.groups = groups;

        this.sidebar = new Sidebar(MiniMessage.miniMessage().deserialize(
                title
        ));

        this.insertLines(player, groups);
    }

    private void insertLines(Player player, List<ScoreboardLineGroup> groups) {
        for (ScoreboardLineGroup group : groups) {
            for (ScoreboardLine line : group.getLines()) {
                this.insertLine(player, line);
            }
        }
    }

    private void insertLine(Player player, ScoreboardLine line) {
        sidebar.createLine(new Sidebar.ScoreboardLine(
                String.valueOf(line.getId()),
                MiniMessage.miniMessage().deserialize(
                        line.getText(),
                        Placeholders.apply(player)
                ),
                0
        ));
    }

    public void updateLine(Player player, ScoreboardLine line) {
        sidebar.updateLineContent(String.valueOf(line.getId()), MiniMessage.miniMessage().deserialize(
                        line.getText(),
                        Placeholders.apply(player)
                )
        );
    }

    public void show(Player player) {
        sidebar.addViewer(player);
    }

    public void hide(Player player) {
        sidebar.removeViewer(player);
    }
}
