package dev.xhyrom.samurai.entity;

import dev.xhyrom.samurai.Samurai;
import lombok.experimental.UtilityClass;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;

@UtilityClass
public final class Entities {
    public static void init() {
        for (Hologram hologram : Samurai.config.holograms) {
            hologram
                    .build()
                    .spawn(Samurai.instance);
        }

        for (NPC npc : Samurai.config.npcs) {
            Samurai.instance.eventNode()
                    .addListener(EntityAttackEvent.class, npc::handle)
                    .addListener(PlayerEntityInteractEvent.class, npc::handle);

            npc
                    .build()
                    .spawn(Samurai.instance);
        }
    }
}
