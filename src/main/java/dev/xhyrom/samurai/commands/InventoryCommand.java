package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.inventory.Inventory;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class InventoryCommand extends Command {
    public InventoryCommand() {
        super("inventory", "inv");

        ArgumentEnum<Inventory> inventoryName = ArgumentType.Enum("name", Inventory.class);
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player))
                return;

            Inventory inventory = context.get(inventoryName);
            inventory.show(player);
        }, inventoryName);
    }
}
