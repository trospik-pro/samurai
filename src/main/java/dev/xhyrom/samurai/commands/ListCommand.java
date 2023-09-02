package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentLiteral;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

import java.util.Collection;

public class ListCommand extends Command {
    public ListCommand() {
        super("list");

        if (!Samurai.config.debug)
            setCondition((sender, commandString) -> sender.hasPermission("samurai.command.list"));

        setDefaultExecutor((sender, context) -> {
            final Collection<Player> players = MinecraftServer.getConnectionManager().getOnlinePlayers();
            final int playerCount = players.size();

            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<gradient:#1E9AFE:#60DFCD><title> <white>• There are </white><online> <white>of a max of </white><max> <white>players online: ",
                    Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                    Placeholder.unparsed("online", String.valueOf(playerCount)),
                    Placeholder.unparsed("max", String.valueOf(Samurai.config.maxPlayers))
            ));
        });

        ArgumentLiteral literalArgument = ArgumentType.Literal("--print-all");
        addSyntax((sender, context) -> {
            final Collection<Player> players = MinecraftServer.getConnectionManager().getOnlinePlayers();
            final int playerCount = players.size();

            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<gradient:#1E9AFE:#60DFCD><title> <white>• There are </white><online> <white>of a max of </white><max> <white>players online: <gray><players>",
                    Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                    Placeholder.unparsed("online", String.valueOf(playerCount)),
                    Placeholder.unparsed("max", String.valueOf(Samurai.config.maxPlayers)),
                    Placeholder.unparsed("players", players.stream().map(Player::getUsername).reduce((a, b) -> a + ", " + b).orElse("none"))
            ));
        }, literalArgument);
    }
}
