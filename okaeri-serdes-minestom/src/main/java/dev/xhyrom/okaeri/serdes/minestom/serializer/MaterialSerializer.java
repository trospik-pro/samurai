package dev.xhyrom.okaeri.serdes.minestom.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.minestom.server.item.Material;
import net.minestom.server.utils.NamespaceID;

public class MaterialSerializer implements ObjectSerializer<Material> {

    @Override
    public boolean supports(@NonNull Class<? super Material> type) {
        return Material.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Material object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.setValue(object.namespace());
    }

    @Override
    public Material deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        NamespaceID namespace = data.getValue(NamespaceID.class);

        return Material.fromNamespaceId(namespace);
    }
}
