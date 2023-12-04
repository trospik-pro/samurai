package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent;
import org.jetbrains.annotations.NotNull;

public class AsyncPlayerPreLogin implements EventListener<AsyncPlayerPreLoginEvent> {
    @Override
    public @NotNull Class<AsyncPlayerPreLoginEvent> eventType() {
        return AsyncPlayerPreLoginEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull AsyncPlayerPreLoginEvent event) {
        int size = MinecraftServer.getConnectionManager().getOnlinePlayerCount();

        if (size >= Samurai.config.maxPlayers) {
            event.getPlayer().kick(MiniMessage.miniMessage().deserialize(
                    Samurai.config.messages.serverIsFull
            ));
        }

        return Result.SUCCESS;
    }
}
