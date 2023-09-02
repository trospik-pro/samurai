package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.module.PlayerHider;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerSpawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerSpawn implements EventListener<PlayerSpawnEvent> {
    @Override
    public @NotNull Class<PlayerSpawnEvent> eventType() {
        return PlayerSpawnEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerSpawnEvent event) {
        Player player = event.getPlayer();

        if (!Samurai.config.debug)
            player.setReducedDebugScreenInformation(true);

        for (UUID uniqueId : PlayerHider.getWantHiddenPlayers()) {
            player.updateOldViewer(MinecraftServer.getConnectionManager().getPlayer(uniqueId));
        }

        return Result.SUCCESS;
    }
}

