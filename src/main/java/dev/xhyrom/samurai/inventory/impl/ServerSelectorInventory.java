package dev.xhyrom.samurai.inventory.impl;

import dev.xhyrom.samurai.inventory.MenuInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ServerSelectorInventory extends MenuInventory {
    public ServerSelectorInventory(InventoryType type, Component title) {
        super(type, title);
    }

    @Override
    public void init(Player player) {
        setItemStack(
                22,
                ItemStack.builder(Material.GRASS_BLOCK)
                        .displayName(translate("<#FFEC00><bold><underlined>ɴᴏᴠɪɴᴋᴀ"))
                        .lore(
                                translate(
                                    "<#07E844><bold>OneBlock"
                                ),
                                translate(
                                    "<#07E844>● <white>Online Hráči: <#07E844>54"
                                translate(
                                    "<#07E844>● <white>Verze: <#07E844>1.19.4 + <dark_gray>(<gray>Doporučujeme <#07E844>1.20.1<dark_gray>)"
                                ),
                                translate(
                                    ""
                                ),
                                translate(
                                    "<gray>Lorem ipsum dolor sit amet, consectetur"
                                ),
                                translate(
                                    "<gray>dipiscing elit, sed do eiusmod tempor"
                                ),
                                translate(
                                    "<gray>ut labore et dolore magna aliqua."
                                ),
                                translate(
                                    ""
                                ),
                                translate(
                                    "<#07E844>▶ Klikni pro připojení"
                                )
                        )
                        .build()
        );
    }

    @Override
    public void execute(Player player, int slot) {
        player.sendMessage(Component.text("You clicked on slot " + slot));
    }
}
