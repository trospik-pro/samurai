package dev.xhyrom.samurai.config.serializers;

import dev.xhyrom.samurai.action.Action;
import dev.xhyrom.samurai.action.ActionType;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public class ActionSerializer implements ObjectSerializer<Action> {
    @Override
    public boolean supports(@NonNull Class<? super Action> type) {
        return Action.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Action object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("type", object.type());
        data.add("value", object.value());
    }

    @Override
    public Action deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        ActionType type = data.get("type", ActionType.class);
        String value = data.get("value", String.class);

        return new Action(type, value);
    }
}
