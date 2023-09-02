package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerMove implements EventListener<PlayerMoveEvent> {
    @Override
    public @NotNull Class<PlayerMoveEvent> eventType() {
        return PlayerMoveEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerMoveEvent event) {
        if (event.getPlayer().getPosition().y() <= Samurai.config.minimalAllowedY)
            event.setNewPosition(Samurai.config.spawn);

        return Result.SUCCESS;
    }
}
