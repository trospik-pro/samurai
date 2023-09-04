package dev.xhyrom.samurai.inventory.impl;

import dev.xhyrom.samurai.SamuraiBootstrap;
import dev.xhyrom.samurai.inventory.MenuInventory;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class DemoInventory extends MenuInventory {
    public DemoInventory() {
        super(InventoryType.WINDOW_3X3, MiniMessage.miniMessage().deserialize(
                "<gradient:#1E9AFE:#60DFCD><title></gradient>",
                Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle())
        ));
    }

    @Override
    public void init(Player player) {
        setItemStack(
                4,
                ItemStack.builder(Material.DIAMOND_BLOCK)
                        .displayName(getTitle())
                        .lore(
                                MiniMessage.miniMessage().deserialize(
                                    "<gradient:#1E9AFE:#60DFCD><name> <dark_gray>(</dark_gray><uuid><dark_gray>)</dark_gray>",
                                        Placeholder.unparsed("name", player.getUsername()),
                                        Placeholder.unparsed("uuid", player.getUuid().toString())
                                ).decoration(TextDecoration.ITALIC, false)
                        )
                        .build()
        );
    }

    @Override
    public void execute(Player player, int slot) {
        player.sendMessage(MiniMessage.miniMessage().deserialize(
                "<gradient:#1E9AFE:#60DFCD><title> <white>• You clicked on slot </white><slot>",
                Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                Placeholder.unparsed("slot", String.valueOf(slot))
        ));
    }
}
