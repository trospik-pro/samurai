package dev.xhyrom.samurai.module.scoreboard;

import lombok.Getter;

public final class ScoreboardLine {
    @Getter
    private static int gId = 0;

    @Getter
    private final String text;
    @Getter
    private final int id;

    public static ScoreboardLine of(String line) {
        return new ScoreboardLine(line);
    }

    public ScoreboardLine(String text) {
        this.text = text;
        this.id = gId++;
    }
}
