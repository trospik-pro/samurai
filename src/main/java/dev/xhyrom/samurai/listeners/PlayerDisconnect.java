package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.module.PlayerHider;
import dev.xhyrom.samurai.module.PlayerScoreboard;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDisconnect implements EventListener<PlayerDisconnectEvent> {
    @Override
    public @NotNull Class<PlayerDisconnectEvent> eventType() {
        return PlayerDisconnectEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerDisconnectEvent event) {
        Player player = event.getPlayer();

        PlayerHider.getWantHiddenPlayers().remove(player.getUuid());
        PlayerScoreboard.destroy(player);

        return Result.SUCCESS;
    }
}
