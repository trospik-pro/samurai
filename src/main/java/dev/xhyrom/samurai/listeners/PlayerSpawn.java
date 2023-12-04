package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.item.Items;
import dev.xhyrom.samurai.module.PlayerHider;
import dev.xhyrom.samurai.module.PlayerScoreboard;
import dev.xhyrom.samurai.team.Teams;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
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

        player.setEnableRespawnScreen(false);
        player.setRespawnPoint(Samurai.config.spawn);
        player.setGameMode(GameMode.ADVENTURE);
        player.setInvulnerable(true);

        Items.give(player);

        // Set team
        player.setTeam(Teams.NO_COLLISIONS);

        if (!Samurai.config.debug)
            player.setReducedDebugScreenInformation(true);

        for (UUID uniqueId : PlayerHider.getWantHiddenPlayers()) {
            player.updateOldViewer(MinecraftServer.getConnectionManager().getPlayer(uniqueId));
        }

        PlayerScoreboard.create(player);

        return Result.SUCCESS;
    }
}

