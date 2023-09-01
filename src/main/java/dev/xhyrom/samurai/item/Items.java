package dev.xhyrom.samurai.item;

import dev.xhyrom.samurai.module.PlayerHider;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

@UtilityClass
public class Items {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static final ItemStack SERVER_SELECTOR = ItemStack.builder(Material.COMPASS)
            .displayName(miniMessage.deserialize("<gradient:#1e9afe:#60DFCD>Server Selector</gradient>"))
            .build();

    public static final ItemStack STORE = ItemStack.builder(Material.EMERALD)
            .displayName(miniMessage.deserialize("<gradient:#1e9afe:#60DFCD>Store</gradient>"))
            .build();

    public static final ItemStack PLAYER_HIDER_HIDE = ItemStack.builder(Material.LIME_DYE)
            .displayName(miniMessage.deserialize("<green>Hide"))
            .build();

    public static final ItemStack PLAYER_HIDER_SHOW = ItemStack.builder(Material.RED_DYE)
            .displayName(miniMessage.deserialize("<red>Show"))
            .build();

    public static void give(Player player) {
        player.getInventory().setItemStack(0, SERVER_SELECTOR);
        player.getInventory().setItemStack(4, STORE);
        player.getInventory().setItemStack(8, PLAYER_HIDER_HIDE);
    }

    public static void handle(Player player, byte slot) {
        switch (slot) {
            case 0 -> {
                player.sendMessage(miniMessage.deserialize("<red>Coming soon0!"));
                // Server selector
            }
            case 1 -> {
                player.sendMessage(miniMessage.deserialize("<red>Coming soon1!"));
                // Lobby selector
            }
            case 4 -> {
                player.sendMessage(miniMessage.deserialize("<red>Coming soon4!"));
                // Store
            }
            case 8 -> {
                PlayerHider.Action action = PlayerHider.toggle(player);
                if (action == PlayerHider.Action.HIDE) {
                    player.getInventory().setItemStack(8, PLAYER_HIDER_SHOW);
                } else {
                    player.getInventory().setItemStack(8, PLAYER_HIDER_HIDE);
                }
            }
        }
    }
}
