package dev.xhyrom.samurai.listeners;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDeath implements EventListener<PlayerDeathEvent> {
    @Override
    public @NotNull Class<PlayerDeathEvent> eventType() {
        return PlayerDeathEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerDeathEvent event) {
        event.setDeathText(null);
        event.setChatMessage(null);

        return Result.SUCCESS;
    }
}