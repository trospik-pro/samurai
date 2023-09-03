package dev.xhyrom.samurai.inventory;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.inventory.condition.InventoryConditionResult;
import org.jetbrains.annotations.NotNull;

public abstract class MenuInventory extends Inventory {
    protected MenuInventory(InventoryType type, Component title) {
        super(type, title);

        addInventoryCondition((Player p, int slot, ClickType clickType, InventoryConditionResult inventoryConditionResult) -> {
            inventoryConditionResult.setCancel(true);

            execute(p, slot);
        });
    }

    @Override
    public boolean addViewer(@NotNull Player player) {
        boolean result = super.addViewer(player);
        if (result)
            this.init(player);

        return result;
    }

    public abstract void init(Player player);
    public abstract void execute(Player player, int slot);

    protected Component translate(String text, TagResolver... resolvers) {
        return MiniMessage.miniMessage().deserialize(text, resolvers).decoration(TextDecoration.ITALIC, false);
    }
}
