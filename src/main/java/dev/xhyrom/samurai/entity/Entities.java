package dev.xhyrom.samurai.entity;

import dev.xhyrom.samurai.Samurai;

public class Entities {
    public static void init() {
        for (Hologram hologram : Samurai.config.holograms) {
            hologram.build();
            hologram.spawn(Samurai.instance);
        }
    }
}
