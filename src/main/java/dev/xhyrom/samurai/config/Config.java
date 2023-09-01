package dev.xhyrom.samurai.config;

import dev.xhyrom.samurai.entity.Hologram;
import dev.xhyrom.samurai.entity.NPC;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.PlayerSkin;

import java.util.List;

public class Config extends OkaeriConfig {
    @Comment("The address of the server")
    public String address = "0.0.0.0:25565";

    @Comment("The brand of the server")
    public String brand = "Samurai";

    @Comment("Spawn location")
    public Pos spawn = new Pos(84, 61, 84, -39.3f, 1.6f);
    @Comment("Holograms")
    public List<Hologram> holograms = List.of(
            new Hologram(
                    new Pos(90, 61, 91, 135.5f, 11.1f),
                    "<gradient:#1e9afe:#60DFCD>Samurai</gradient>" + "\n" +
                            "<#60DFCD>Hub Implementation" + "\n"
            ).shadow(true).bgColor(0x000000).scale(new Vec(3f, 3f, 3f))
    );

    @Comment("NPCs")
    public List<NPC> npcs = List.of(
            new NPC(
                    new Pos(90, 61, 91, 137.4f, 0.0f)
            ).skin("general_kubo")
    );
}
