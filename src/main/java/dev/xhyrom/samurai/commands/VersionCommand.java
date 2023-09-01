package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.SamuraiBootstrap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;

public class VersionCommand extends Command {
    public VersionCommand() {
        super("version", "ver");

        setDefaultExecutor((sender, context) -> sender.sendMessage(MiniMessage.miniMessage().deserialize(
                "<gradient:#1E9AFE:#60DFCD><title> <white>• </white><version>" +
                " <dark_gray>for </dark_gray><minecraft_version>" +
                " <dark_gray>protocol</dark_gray> <protocol></gradient>",
                Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                Placeholder.unparsed("version", SamuraiBootstrap.PACKAGE.getImplementationVersion()),
                Placeholder.unparsed("minecraft_version", MinecraftServer.VERSION_NAME),
                Placeholder.unparsed("protocol", String.valueOf(MinecraftServer.PROTOCOL_VERSION))
        )));
    }
}
