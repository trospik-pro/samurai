package dev.xhyrom.okaeri.serdes.minestom.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.minestom.server.coordinate.Vec;

public class VecSerializer implements ObjectSerializer<Vec> {
    @Override
    public boolean supports(@NonNull Class<? super Vec> type) {
        return Vec.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Vec object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("x", object.x());
        data.add("y", object.y());
        data.add("z", object.z());
    }

    @Override
    public Vec deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        double x = data.get("x", Double.class);
        double y = data.get("y", Double.class);
        double z = data.get("z", Double.class);

        return new Vec(x, y, z);
    }
}
