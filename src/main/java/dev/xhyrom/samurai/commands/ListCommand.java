package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
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

            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<gradient:#1E9AFE:#60DFCD><title></gradient> <white>• <white>There are <gradient:#1E9AFE:#60DFCD><online></gradient> <white>of a max of <gradient:#1E9AFE:#60DFCD><max></gradient> <white>players online: <gray><players>",
                    Placeholder.parsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                    Placeholder.parsed("online", String.valueOf(playerCount)),
                    Placeholder.parsed("max", String.valueOf(Samurai.config.maxPlayers)),
                    Placeholder.parsed("players", players.stream().map(Player::getUsername).reduce((a, b) -> a + ", " + b).orElse("none"))
            ));
        });
    }
}
