package dev.xhyrom.samurai.module.scoreboard;

import lombok.Getter;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public final class ScoreboardLineGroup {
    @Getter
    @Nullable private List<ScoreboardLine> lines;
    @Getter
    private final int refreshRate;

    public static ScoreboardLineGroup of(String line) {
        return new ScoreboardLineGroup(List.of(ScoreboardLine.of(line)), 0);
    }

    public static ScoreboardLineGroup of(List<ScoreboardLine> lines) {
        return new ScoreboardLineGroup(lines, 0);
    }

    public static ScoreboardLineGroup of(List<String> lines, int refreshRate) {
        return new ScoreboardLineGroup(lines.stream().map(
                ScoreboardLine::of
        ).toList(), refreshRate);
    }

    public ScoreboardLineGroup(List<ScoreboardLine> lines, int refreshRate) {
        this.lines = lines;
        this.refreshRate = refreshRate;
    }
}
