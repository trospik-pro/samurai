package dev.xhyrom.okaeri.serdes.minestom;

import dev.xhyrom.okaeri.serdes.minestom.serializer.PosSerializer;
import dev.xhyrom.okaeri.serdes.minestom.serializer.VecSerializer;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import lombok.NonNull;

public class SerdesMinestom implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        registry.register(new PosSerializer());
        registry.register(new VecSerializer());
    }
}
