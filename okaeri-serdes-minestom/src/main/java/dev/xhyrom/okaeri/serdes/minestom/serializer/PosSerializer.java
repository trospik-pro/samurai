package dev.xhyrom.okaeri.serdes.minestom.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.minestom.server.coordinate.Pos;

public class PosSerializer implements ObjectSerializer<Pos> {
    @Override
    public boolean supports(@NonNull Class<? super Pos> type) {
        return Pos.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Pos object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("x", object.x());
        data.add("y", object.y());
        data.add("z", object.z());
        data.add("yaw", object.yaw());
        data.add("pitch", object.pitch());
    }

    @Override
    public Pos deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        double x = data.get("x", Double.class);
        double y = data.get("y", Double.class);
        double z = data.get("z", Double.class);

        float yaw = data.get("yaw", Float.class);
        float pitch = data.get("pitch", Float.class);

        return new Pos(x, y, z, yaw, pitch);
    }
}
