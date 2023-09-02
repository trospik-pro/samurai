package dev.xhyrom.samurai.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class ModeConfig extends OkaeriConfig {
    @Comment("Mode")
    public Mode mode = Mode.OFFLINE;

    @Comment("Secret")
    public String secret = "";

    public enum Mode {
        OFFLINE,
        ONLINE,
        VELOCITY;
    }
}
