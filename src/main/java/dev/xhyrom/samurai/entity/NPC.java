package dev.xhyrom.samurai.entity;

import dev.xhyrom.samurai.team.Teams;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.*;
import net.minestom.server.entity.metadata.PlayerMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.network.packet.server.play.PlayerInfoUpdatePacket;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class NPC extends EntityCreature {
    private static int id = 0;

    private final int localName;
    @Getter
    private final Point location;
    private PlayerSkin skin;
    private String skinName;

    public NPC(@NotNull Point location) {
        super(EntityType.PLAYER);

        this.localName = id++;
        this.location = location;

        final PlayerMeta meta = (PlayerMeta) getEntityMeta();

        meta.setNotifyAboutChanges(false);

        meta.setCapeEnabled(false);
        meta.setJacketEnabled(true);
        meta.setLeftSleeveEnabled(true);
        meta.setRightSleeveEnabled(true);
        meta.setLeftLegEnabled(true);
        meta.setRightLegEnabled(true);
        meta.setHatEnabled(true);
        meta.setHasNoGravity(true);
    }

    public String skinName() {
        return this.skinName;
    }

    public NPC skin(@NotNull String name) {
        this.skin = PlayerSkin.fromUsername(name);
        this.skinName = name;

        return this;
    }

    public NPC build() {
        final PlayerMeta meta = (PlayerMeta) getEntityMeta();
        meta.setNotifyAboutChanges(true);

        return this;
    }

    public void spawn(Instance instance) {
        setTeam(MinecraftServer.getTeamManager().getTeam("nt"));

        this.setInstance(instance, location);
    }

    @Override
    public void updateNewViewer(@NotNull Player player) {
        // Required to spawn player
        final List<PlayerInfoUpdatePacket.Property> properties = List.of(
                new PlayerInfoUpdatePacket.Property("textures", skin.textures(), skin.signature())
        );

        player.sendPacket(new PlayerInfoUpdatePacket(PlayerInfoUpdatePacket.Action.ADD_PLAYER,
                        new PlayerInfoUpdatePacket.Entry(
                                getUuid(), "n-" + this.localName, properties, false, 0, GameMode.SURVIVAL, null,
                                null)
                )
        );

        Teams.NPC.addMember("n-" + this.localName);

        super.updateNewViewer(player);
    }
}
