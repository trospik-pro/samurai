package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.SamuraiBootstrap;
import net.hollowcube.minestom.extensions.ExtensionBootstrap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.command.builder.Command;
import net.minestom.server.extensions.DiscoveredExtension;
import net.minestom.server.extensions.Extension;

import java.util.Arrays;
import java.util.Collection;

public class ExtensionsCommand extends Command {
    public ExtensionsCommand() {
        super("extensions");

        setDefaultExecutor((sender, context) -> {
            Collection<DiscoveredExtension> extensions = ExtensionBootstrap.getExtensionManager().getExtensions()
                    .stream().map(Extension::getOrigin)
                    .toList();

            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<gradient:#1E9AFE:#60DFCD><title> <white>• There are </white><loaded> <white>extensions loaded: <gray><extensions>",
                    Placeholder.unparsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                    Placeholder.unparsed("loaded", String.valueOf(extensions.size())),
                    Placeholder.parsed("extensions", extensions.stream().map(e -> {
                        StringBuilder builder = new StringBuilder();

                        builder.append("<gray>Authors:</gray> <gradient:#1E9AFE:#60DFCD>" + Arrays.stream(e.getAuthors()).reduce((a, b) -> a + ", " + b).orElse("none"));
                        builder.append("\n");
                        builder.append("<gray>Version:</gray> " + e.getVersion());
                        builder.append("\n");
                        builder.append("<gray>Entrypoint:</gray> " + e.getEntrypoint());
                        builder.append("\n");
                        builder.append("<gray>Dependencies:</gray> " + Arrays.stream(e.getDependencies()).reduce((a, b) -> a + ", " + b).orElse("none"));

                        return "<hover:show_text:\""+builder.toString()+"\">" + e.getName() + "</hover>";
                    }).reduce((a, b) -> a + ", " + b).orElse("none"))
            ));
        });
    }
}
