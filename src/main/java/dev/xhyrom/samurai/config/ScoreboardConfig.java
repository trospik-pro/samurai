package dev.xhyrom.samurai.config;

import com.google.common.collect.ImmutableList;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.List;

public class ScoreboardConfig extends OkaeriConfig {
    @Comment("Scoreboard title")
    public String title = "<gradient:#1e9afe:#60DFCD><bold>Samurai</bold></gradient>";

    @Comment("Scoreboard lines")
    public List<String> lines = ImmutableList.of(
            "",
            "<white>Name: <#1e9afe><papi:player>",
            "",
            "<#60DFCD>ʜᴜʙ ɪᴍᴘʟᴇᴍᴇɴᴛᴀᴛɪᴏɴ"
    );
}
