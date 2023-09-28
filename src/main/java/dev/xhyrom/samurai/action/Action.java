package dev.xhyrom.samurai.action;

import dev.xhyrom.samurai.inventory.Inventories;
import dev.xhyrom.samurai.util.VelocityBridge;
import lombok.Getter;
import net.minestom.server.entity.Player;

public record Action(ActionType type, @Getter String value) {
    public static Action of(Inventories inventory) {
        return new Action(ActionType.OPEN_INVENTORY, inventory.name());
    }

    public static Action of(String server) {
        return new Action(ActionType.SEND_TO_SERVER, server);
    }

    public void execute(Player player) {
        switch (type) {
            case OPEN_INVENTORY -> Inventories.valueOf(value).show(player);
            case SEND_TO_SERVER -> VelocityBridge.sendPlayerToServer(player, value);
        }
    }
}
