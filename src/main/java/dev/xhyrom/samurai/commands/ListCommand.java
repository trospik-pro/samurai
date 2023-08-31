package dev.xhyrom.samurai.commands;

import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;

import java.util.Collection;

public class ListCommand extends Command {
    public ListCommand() {
        super("list");

        setDefaultExecutor((sender, context) -> {
            final Collection<Player> players = MinecraftServer.getConnectionManager().getOnlinePlayers();
            final int playerCount = players.size();

            sender.sendMessage(Component.text("Total players: " + playerCount));
        });
    }
}
