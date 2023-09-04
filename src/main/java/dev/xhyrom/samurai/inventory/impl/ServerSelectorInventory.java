package dev.xhyrom.samurai.inventory.impl;

import dev.xhyrom.samurai.inventory.MenuInventory;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.PlayerHeadMeta;

import java.util.UUID;

public class ServerSelectorInventory extends MenuInventory {
    public ServerSelectorInventory() {
        super(InventoryType.CHEST_5_ROW, Component.text("Herní módy"));
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
                                ),
                                translate(
                                    "<#07E844>● <white>Verze: <#07E844>1.19.4+ <dark_gray>(<gray>Doporučujeme <#07E844>1.20.1<dark_gray>)"
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

        setItemStack(
                40,
                ItemStack.builder(Material.PLAYER_HEAD)
                        .meta(new PlayerHeadMeta.Builder()
                                .skullOwner(UUID.fromString("fa7446ed-005c-4dfa-96df-5d4c362e0498"))
                                .playerSkin(new PlayerSkin("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM4YWIxNDU3NDdiNGJkMDljZTAzNTQzNTQ5NDhjZTY5ZmY2ZjQxZDllMDk4YzY4NDhiODBlMTg3ZTkxOSJ9fX0=", null))
                                .displayName(translate("<#E74C3C>❌ <bold>Zavřít"))
                                .lore(
                                        translate(
                                                "<white>Zavři toto menu"
                                        )
                                )
                                .tagHandler()
                        )
                        .build()
        );

        // oliverkov for lúp
        for (int i = 0; i < 9; i++) {
            setItemStack(
                    i,
                    ItemStack.builder(Material.GRAY_STAINED_GLASS_PANE)
                            .displayName(translate(""))
                            .build()
            );
        }

        for (int i = 36; i < 45; i++) {
            if (i == 40) continue;
            setItemStack(
                    i,
                    ItemStack.builder(Material.GRAY_STAINED_GLASS_PANE)
                            .displayName(translate(""))
                            .build()
            );
        }
    }

    @Override
    public void execute(Player player, int slot) {
        if (slot == 40) {
            player.closeInventory();
            player.playSound(Sound.sound(Key.key("item.book.page_turn"), Sound.Source.PLAYER, 1f, 1f));
        }
    }
}
