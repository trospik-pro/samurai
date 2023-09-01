package dev.xhyrom.samurai.config.serializers;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;

public class SerdesCustom implements OkaeriSerdesPack {
    @Override
    public void register(SerdesRegistry registry) {
        registry.register(new HologramSerializer());
        registry.register(new NPCSerializer());
    }
}
