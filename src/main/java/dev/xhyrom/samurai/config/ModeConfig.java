package dev.xhyrom.samurai.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Set;

public class ModeConfig extends OkaeriConfig {
    @Comment("Mode")
    public Mode mode = Mode.OFFLINE;

    @Comment("Velocity secret")
    @Comment("Used only in Velocity mode")
    public String velocitySecret = "";

    @Comment("Bungeeguard secrets")
    @Comment("Used only in Bungeeguard mode")
    public Set<String> bungeeguardSecrets = Set.of("");

    public enum Mode {
        OFFLINE,
        ONLINE,
        BUNGEECORD,
        VELOCITY;
    }
}
