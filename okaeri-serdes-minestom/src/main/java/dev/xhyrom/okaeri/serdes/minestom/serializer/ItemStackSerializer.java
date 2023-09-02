package dev.xhyrom.okaeri.serdes.minestom.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ItemStackSerializer implements ObjectSerializer<ItemStack> {
    @Override
    public boolean supports(@NonNull Class<? super ItemStack> type) {
        return ItemStack.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull ItemStack object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("material", object.material());
        data.add("displayName", object.getDisplayName());
    }

    @Override
    public ItemStack deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        Material material = data.get("material", Material.class);
        String displayName = data.get("displayName", String.class);

        return ItemStack.builder(material)
                .displayName(MiniMessage.miniMessage().deserialize(displayName))
                .build();
    }
}
