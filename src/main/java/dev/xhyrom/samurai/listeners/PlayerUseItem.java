package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.item.Items;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerUseItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerUseItem implements EventListener<PlayerUseItemEvent> {
    @Override
    public @NotNull Class<PlayerUseItemEvent> eventType() {
        return PlayerUseItemEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerUseItemEvent event) {
        Items.handle(event.getPlayer(), event.getPlayer().getHeldSlot());

        return Result.SUCCESS;
    }
}
