package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.inventory.Inventories;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class InventoryCommand extends Command {
    public InventoryCommand() {
        super("inventory", "inv");

        ArgumentEnum<Inventories> inventoryName = ArgumentType.Enum("name", Inventories.class);
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player))
                return;

            Inventories inventory = context.get(inventoryName);
            inventory.show(player);
        }, inventoryName);
    }
}
