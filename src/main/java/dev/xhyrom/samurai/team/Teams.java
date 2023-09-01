package dev.xhyrom.samurai.team;

import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.network.packet.server.play.TeamsPacket;
import net.minestom.server.scoreboard.Team;

@UtilityClass
public final class Teams {
    // Disables collisions for all players in this team
    public static Team NO_COLLISIONS = MinecraftServer.getTeamManager().createBuilder("nc")
            .collisionRule(TeamsPacket.CollisionRule.PUSH_OWN_TEAM)
            .build();

    // Disables name tags and enables collisions
    public static Team NPC = MinecraftServer.getTeamManager().createBuilder("nt")
            .nameTagVisibility(TeamsPacket.NameTagVisibility.NEVER)
            .collisionRule(TeamsPacket.CollisionRule.ALWAYS)
            .build();
}
