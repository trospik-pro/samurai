package dev.xhyrom.samurai.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class WorldBorderConfig extends OkaeriConfig {
    @Comment("Center of the world border")
    public float x = 84;
    public float z = 84;

    @Comment("Diameter of the world border")
    public double diameter = 30;
}
