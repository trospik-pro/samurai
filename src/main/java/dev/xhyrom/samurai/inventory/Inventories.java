package dev.xhyrom.samurai.inventory;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import dev.xhyrom.samurai.inventory.impl.DemoInventory;
import dev.xhyrom.samurai.inventory.impl.ServerSelectorInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;

import java.util.logging.Level;

public enum Inventories {
    SERVER_SELECTOR(ServerSelectorInventory.class, InventoryType.CHEST_3_ROW, Component.text("Server Selector")),
    DEMO(DemoInventory.class, InventoryType.WINDOW_3X3, MiniMessage.miniMessage().deserialize(
            "<gradient:#1E9AFE:#60DFCD><title></gradient>",
            Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle())
    ));

    private final Class<? extends MenuInventory> clazz;
    private final InventoryType type;
    private final Component title;

    Inventories(Class<? extends MenuInventory> clazz, InventoryType type, Component title) {
        this.clazz = clazz;
        this.type = type;
        this.title = title;
    }

    public boolean show(Player player) {
        try {
            MenuInventory inventory = this.clazz.getConstructor(InventoryType.class, Component.class).newInstance(this.type, this.title);
            return player.openInventory(inventory);
        } catch (Exception e) {
            Samurai.logger.log(Level.SEVERE, "Failed to open inventory " + this.name(), e);

            return false;
        }
    }
}
