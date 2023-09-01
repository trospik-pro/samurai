package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import dev.xhyrom.samurai.item.Items;
import dev.xhyrom.samurai.team.Teams;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLogin implements EventListener<PlayerLoginEvent> {
    @Override
    public @NotNull Class<PlayerLoginEvent> eventType() {
        return PlayerLoginEvent.class;
    }

    @Override
    public @NotNull Result run(@NotNull PlayerLoginEvent event) {
        Player player = event.getPlayer();

        event.setSpawningInstance(Samurai.instance);

        player.setRespawnPoint(Samurai.config.spawn);
        player.setGameMode(GameMode.ADVENTURE);
        player.setReducedDebugScreenInformation(true);

        Items.give(player);

        // Set team
        player.setTeam(Teams.NO_COLLISIONS);

        return Result.SUCCESS;
    }
}
