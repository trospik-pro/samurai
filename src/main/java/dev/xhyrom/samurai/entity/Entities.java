package dev.xhyrom.samurai.entity;

import dev.xhyrom.samurai.Samurai;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Entities {
    public static void init() {
        for (Hologram hologram : Samurai.config.holograms) {
            hologram
                    .build()
                    .spawn(Samurai.instance);
        }

        for (NPC npc : Samurai.config.npcs) {
            npc
                    .build()
                    .spawn(Samurai.instance);
        }
    }
}
