package dev.xhyrom.samurai.util;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;

import java.util.List;

public class Scoreboard {
    private final String title;
    private final List<String> lines;

    private final Sidebar sidebar;

    public Scoreboard(Player player, String title, List<String> lines) {
        this.title = title;
        this.lines = lines;

        this.sidebar = new Sidebar(MiniMessage.miniMessage().deserialize(
                title
        ));

        this.insertLines(player, lines);
    }

    private void insertLines(Player player, List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            sidebar.createLine(new Sidebar.ScoreboardLine(
                    String.valueOf(i),
                    MiniMessage.miniMessage().deserialize(
                            lines.get(i),
                            Placeholders.apply(player)
                    ),
                    0
            ));
        }
    }

    public void updateLine(Player player, int line, String text) {
        sidebar.updateLineContent(String.valueOf(line), MiniMessage.miniMessage().deserialize(
                        text
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
