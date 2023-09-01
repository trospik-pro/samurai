package dev.xhyrom.samurai.module;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@UtilityClass
public final class PlayerHider {
    @Getter
    private final Set<UUID> wantHiddenPlayers = new HashSet<>();

    public Action toggle(Player player) {
        if (wantHiddenPlayers.remove(player.getUuid())) {
            show(player);

            return Action.SHOW;
        }

        wantHiddenPlayers.add(player.getUuid());
        hide(player);

        return Action.HIDE;
    }

    private void show(Player player) {
        for (Player p : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            if (p.getUuid() != player.getUuid()) {
                p.updateNewViewer(player);
            }
        }
    }

    private void hide(Player player) {
        for (Player p : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            if (p.getUuid() != player.getUuid()) {
                p.updateOldViewer(player);
            }
        }
    }

    public enum Action {
        SHOW,
        HIDE;
    }
}
