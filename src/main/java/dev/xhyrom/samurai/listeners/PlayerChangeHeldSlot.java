package dev.xhyrom.samurai.listeners;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerChangeHeldSlotEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerChangeHeldSlot implements EventListener<PlayerChangeHeldSlotEvent> {
    @Override
    public @NotNull Class<PlayerChangeHeldSlotEvent> eventType() {
        return PlayerChangeHeldSlotEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerChangeHeldSlotEvent event) {
        event.setCancelled(true);
        return Result.SUCCESS;
    };
}