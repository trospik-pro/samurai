package dev.xhyrom.samurai.config.serializers.actions;

import dev.xhyrom.samurai.action.ActionType;
import dev.xhyrom.samurai.action.impl.OpenInventoryAction;
import dev.xhyrom.samurai.inventory.Inventory;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public class OpenInventoryActionSerializer extends ActionSerializer<OpenInventoryAction> {
    @Override
    protected void serializeExtend(@NonNull OpenInventoryAction object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("inventory", object.getInventory());
    }

    @Override
    protected OpenInventoryAction deserializeExtend(@NonNull ActionType type, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        Inventory inventory = data.get("inventory", Inventory.class);

        return OpenInventoryAction.of(inventory);
    }

    @Override
    public boolean supports(@NonNull Class<? super OpenInventoryAction> type) {
        return OpenInventoryAction.class.isAssignableFrom(type);
    }
}
