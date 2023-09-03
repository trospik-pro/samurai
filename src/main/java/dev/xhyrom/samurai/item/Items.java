package dev.xhyrom.samurai.item;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.inventory.Inventories;
import dev.xhyrom.samurai.module.PlayerHider;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.entity.Player;

@UtilityClass
public class Items {
    public static void give(Player player) {
        player.getInventory().setItemStack(0, Samurai.config.items.serverSelector);
        player.getInventory().setItemStack(4, Samurai.config.items.store);
        player.getInventory().setItemStack(8, Samurai.config.items.playerHiderHide);
    }

    public static void handle(Player player, byte slot) {
        switch (slot) {
            case 0 -> {
                Inventories.SERVER_SELECTOR.show(player);
                // Server selector
            }
            case 1 -> {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Coming soon1!"));
                // Lobby selector
            }
            case 4 -> {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Coming soon4!"));
                // Store
            }
            case 8 -> {
                PlayerHider.Action action = PlayerHider.toggle(player);

                if (action == PlayerHider.Action.COOLDOWN_HIT) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Please wait before doing that again!"));
                } else if (action == PlayerHider.Action.HIDE) {
                    player.getInventory().setItemStack(8, Samurai.config.items.playerHiderShow);
                } else {
                    player.getInventory().setItemStack(8, Samurai.config.items.playerHiderHide);
                }
            }
        }
    }
}
