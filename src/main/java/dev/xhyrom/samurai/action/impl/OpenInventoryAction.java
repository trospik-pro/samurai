package dev.xhyrom.samurai.action.impl;

import dev.xhyrom.samurai.action.Action;
import dev.xhyrom.samurai.action.ActionType;
import dev.xhyrom.samurai.inventory.Inventory;
import lombok.Getter;
import net.minestom.server.entity.Player;

public class OpenInventoryAction extends Action {
    @Getter
    private final Inventory inventory;

    public static OpenInventoryAction of(Inventory inv) {
        return new OpenInventoryAction(inv);
    }

    protected OpenInventoryAction(Inventory inv) {
        super(ActionType.OPEN_INVENTORY);

        this.inventory = inv;
    }

    @Override
    public void execute(Player player) {
        this.inventory.show(player);
    }
}
