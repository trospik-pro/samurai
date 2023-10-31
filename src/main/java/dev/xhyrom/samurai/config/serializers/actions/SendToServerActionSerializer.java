package dev.xhyrom.samurai.config.serializers.actions;

import dev.xhyrom.samurai.action.ActionType;
import dev.xhyrom.samurai.action.impl.SendToServerAction;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public class SendToServerActionSerializer extends ActionSerializer<SendToServerAction> {
    @Override
    protected void serializeExtend(@NonNull SendToServerAction object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("server-name", object.getServerName());
    }

    @Override
    protected SendToServerAction deserializeExtend(@NonNull ActionType type, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        String serverName = data.get("server-name", String.class);

        return SendToServerAction.of(serverName);
    }

    @Override
    public boolean supports(@NonNull Class<? super SendToServerAction> type) {
        return SendToServerAction.class.isAssignableFrom(type);
    }
}
