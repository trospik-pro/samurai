package dev.xhyrom.samurai.listeners;

import dev.xhyrom.samurai.Samurai;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
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
        event.setSpawningInstance(Samurai.instanceContainer);
        player.setRespawnPoint(new Pos(88, 61, 88));
        player.setGameMode(GameMode.CREATIVE);

        // Tell players, and the log, that someone joined
        Component playerJoinMessage = player.getName().append(Component.text(" joined the server")).color(NamedTextColor.YELLOW);
        Audiences.players().sendMessage(playerJoinMessage);
        //logger.info(ANSIComponentSerializer.ansi().serialize(playerJoinMessage));

        // Set team
        player.setTeam(MinecraftServer.getTeamManager().getTeam("noCollision"));
        return Result.SUCCESS;
    }
}
