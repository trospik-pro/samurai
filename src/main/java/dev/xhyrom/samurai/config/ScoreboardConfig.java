package dev.xhyrom.samurai.config;

import dev.xhyrom.samurai.module.scoreboard.ScoreboardLineGroup;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.List;

public class ScoreboardConfig extends OkaeriConfig {
    @Comment("Scoreboard title")
    public String title = "<gradient:#1e9afe:#60DFCD><bold>Samurai</bold></gradient>";

    @Comment("Scoreboard groups")
    public List<ScoreboardLineGroup> groups = List.of(
            ScoreboardLineGroup.of("<gray><papi:date:dd/MM/yyyy>"),
            ScoreboardLineGroup.of(""),
            ScoreboardLineGroup.of("<white>Name: <#1e9afe><papi:player>"),
            ScoreboardLineGroup.of(""),
            ScoreboardLineGroup.of(List.of(
                    "<white><papi:date:\"dd/MM/yyyy hh:mm:ss\">",
                    "<white><papi:date:\"dd/MM/yyyy hh:mm:ss\">"
            ), 20),
            ScoreboardLineGroup.of(""),
            ScoreboardLineGroup.of("<#60DFCD>ʜᴜʙ ɪᴍᴘʟᴇᴍᴇɴᴛᴀᴛɪᴏɴ")
    );
}
