package dev.xhyrom.samurai.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class MessageConfig extends OkaeriConfig {
    @Comment("Unknown command message")
    public String unknownCommand = "<red>Unknown command!";

    @Comment("Server is full")
    public String serverIsFull = "<red>Server is full! Please try again later.";
}
