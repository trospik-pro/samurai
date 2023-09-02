package dev.xhyrom.okaeri.serdes.minestom.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.minestom.server.utils.NamespaceID;

public class NamespaceIDSerializer implements ObjectSerializer<NamespaceID> {

    @Override
    public boolean supports(@NonNull Class<? super NamespaceID> type) {
        return NamespaceID.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull NamespaceID object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.setValue(object.toString());
    }

    @Override
    public NamespaceID deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        String value = data.getValue(String.class);

        return NamespaceID.from(value);
    }
}
