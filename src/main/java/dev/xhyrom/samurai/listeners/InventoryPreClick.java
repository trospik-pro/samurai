package dev.xhyrom.samurai.listeners;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import org.jetbrains.annotations.NotNull;

public class InventoryPreClick implements EventListener<InventoryPreClickEvent> {
    @Override
    public @NotNull Class<InventoryPreClickEvent> eventType() {
        return InventoryPreClickEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull InventoryPreClickEvent event) {
        event.setCancelled(true);
        return Result.SUCCESS;
    }
}

