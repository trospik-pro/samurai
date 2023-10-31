package dev.xhyrom.samurai.config.serializers.actions;

import dev.xhyrom.samurai.action.Action;
import dev.xhyrom.samurai.action.ActionType;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public abstract class ActionSerializer<T extends Action> implements ObjectSerializer<T> {
    protected abstract void serializeExtend(@NonNull T object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics);
    @Override
    public void serialize(@NonNull T object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("type", object.getType());

        this.serializeExtend(object, data, generics);
    }

    protected abstract T deserializeExtend(@NonNull ActionType type, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics);
    @Override
    public T deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        ActionType type = data.get("type", ActionType.class);

        return this.deserializeExtend(type, data, generics);
    }
}
