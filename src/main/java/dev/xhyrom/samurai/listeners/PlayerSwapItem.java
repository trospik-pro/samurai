package dev.xhyrom.samurai.listeners;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerSwapItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerSwapItem implements EventListener<PlayerSwapItemEvent> {
    @Override
    public @NotNull Class<PlayerSwapItemEvent> eventType() {
        return PlayerSwapItemEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerSwapItemEvent event) {
        event.setCancelled(true);
        return Result.SUCCESS;
    };
}