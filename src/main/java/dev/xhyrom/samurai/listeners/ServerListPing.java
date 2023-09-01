package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.SamuraiBootstrap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.server.ServerListPingEvent;
import org.jetbrains.annotations.NotNull;

public class ServerListPing implements EventListener<ServerListPingEvent> {
    @Override
    public @NotNull Class<ServerListPingEvent> eventType() {
        return ServerListPingEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull ServerListPingEvent event) {
        event.getResponseData().setDescription(MiniMessage.miniMessage().deserialize(
                "<dark_gray>A <gradient:#1E9AFE:#60DFCD><title> <dark_gray>server" + "\n" +
                "Running</dark_gray> <version> <dark_gray>for</dark_gray> <minecraft_version> <dark_gray>protocol</dark_gray> <protocol>",
                Placeholder.parsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
                Placeholder.parsed("version", SamuraiBootstrap.PACKAGE.getImplementationVersion()),
                Placeholder.unparsed("minecraft_version", MinecraftServer.VERSION_NAME),
                Placeholder.unparsed("protocol", String.valueOf(MinecraftServer.PROTOCOL_VERSION))
        ));

        event.getResponseData().setMaxPlayer(Samurai.config.maxPlayers);

        if (Samurai.config.debug) {
            event.getResponseData().setProtocol(-1);
            event.getResponseData().setVersion("§cDebug");
        }

        return Result.SUCCESS;
    }
}
