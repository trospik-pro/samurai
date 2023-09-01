package dev.xhyrom.samurai.config.serializers;

import dev.xhyrom.samurai.entity.Hologram;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;

public class HologramSerializer implements ObjectSerializer<Hologram> {

    @Override
    public boolean supports(@NonNull Class<? super Hologram> type) {
        return Hologram.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Hologram object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("location", object.getLocation());
        data.add("text", object.getText());
        data.add("shadow", object.shadow());
        data.add("bgColor", object.bgColor());
        data.add("scale", object.scale());
    }

    @Override
    public Hologram deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        Pos location = data.get("location", Pos.class);
        String text = data.get("text", String.class);
        boolean shadow = data.get("shadow", Boolean.class);
        int bgColor = data.get("bgColor", Integer.class);
        Vec scale = data.get("scale", Vec.class);

        return new Hologram(location, text)
                .shadow(shadow)
                .bgColor(bgColor)
                .scale(scale);
    }
}
