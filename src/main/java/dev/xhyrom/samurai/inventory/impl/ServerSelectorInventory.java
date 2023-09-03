package dev.xhyrom.samurai.inventory.impl;

import dev.xhyrom.samurai.inventory.MenuInventory;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;

public class ServerSelectorInventory extends MenuInventory {
    public ServerSelectorInventory(InventoryType type, Component title) {
        super(type, title);
    }

    @Override
    public void init(Player player) {
    }

    @Override
    public void execute(Player player, int slot) {
        player.sendMessage(Component.text("You clicked on slot " + slot));
    }
}
