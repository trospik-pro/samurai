package dev.xhyrom.samurai.inventory;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.inventory.impl.DemoInventory;
import dev.xhyrom.samurai.inventory.impl.ServerSelectorInventory;
import net.minestom.server.entity.Player;

import java.util.logging.Level;

public enum Inventory {
    SERVER_SELECTOR(ServerSelectorInventory.class),
    DEMO(DemoInventory.class);

    private final Class<? extends MenuInventory> clazz;

    Inventory(Class<? extends MenuInventory> clazz) {
        this.clazz = clazz;
    }

    public boolean show(Player player) {
        try {
            MenuInventory inventory = this.clazz.getConstructor().newInstance();
            return player.openInventory(inventory);
        } catch (Exception e) {
            Samurai.logger.log(Level.SEVERE, "Failed to open inventory " + this.name(), e);

            return false;
        }
    }
}
