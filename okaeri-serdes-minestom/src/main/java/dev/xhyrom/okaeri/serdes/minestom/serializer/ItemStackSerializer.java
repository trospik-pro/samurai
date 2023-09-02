package dev.xhyrom.okaeri.serdes.minestom.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.List;

public class ItemStackSerializer implements ObjectSerializer<ItemStack> {
    @Override
    public boolean supports(@NonNull Class<? super ItemStack> type) {
        return ItemStack.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull ItemStack object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("material", object.material());
        data.add("displayName", MiniMessage.miniMessage().serialize(object.getDisplayName()));
        data.add("lore", object.getLore().stream().map(s -> MiniMessage.miniMessage().serialize(s)).toList());
    }

    @Override
    public ItemStack deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        Material material = data.get("material", Material.class);
        String displayName = data.get("displayName", String.class);
        List<String> lore = data.getAsList("lore", String.class);

        return ItemStack.builder(material)
                .displayName(MiniMessage.miniMessage().deserialize(displayName))
                .lore(lore.stream().map(s -> MiniMessage.miniMessage().deserialize(s)).toList())
                .build();
    }
}
