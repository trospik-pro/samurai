package dev.xhyrom.samurai.config.serializers;

import dev.xhyrom.samurai.config.serializers.actions.OpenInventoryActionSerializer;
import dev.xhyrom.samurai.config.serializers.actions.SendToServerActionSerializer;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;

public class SerdesCustom implements OkaeriSerdesPack {
    @Override
    public void register(SerdesRegistry registry) {
        registry.register(new OpenInventoryActionSerializer());
        registry.register(new SendToServerActionSerializer());

        registry.register(new HologramSerializer());
        registry.register(new NPCSerializer());
        registry.register(new ScoreboardLineSerializer());
        registry.register(new ScoreboardLineGroupSerializer());
    }
}
