package dev.xhyrom.samurai.config;

import eu.okaeri.configs.OkaeriConfig;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ItemsConfig extends OkaeriConfig {
    public ItemStack serverSelector = ItemStack.builder(Material.COMPASS)
            .displayName(MiniMessage.miniMessage().deserialize("<gradient:#1e9afe:#60DFCD>Server Selector</gradient>")
                    .decoration(TextDecoration.ITALIC, false))
            .build();

    public ItemStack store = ItemStack.builder(Material.EMERALD)
            .displayName(MiniMessage.miniMessage().deserialize("<gradient:#1e9afe:#60DFCD>Store</gradient>")
                    .decoration(TextDecoration.ITALIC, false))
            .build();

    public ItemStack playerHiderHide = ItemStack.builder(Material.LIME_DYE)
            .displayName(MiniMessage.miniMessage().deserialize("<green>Hide")
                    .decoration(TextDecoration.ITALIC, false))
            .build();

    public ItemStack playerHiderShow = ItemStack.builder(Material.RED_DYE)
            .displayName(MiniMessage.miniMessage().deserialize("<red>Show")
                    .decoration(TextDecoration.ITALIC, false))
            .build();
}
