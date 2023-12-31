package dev.xhyrom.samurai.config.serializers;

import dev.xhyrom.samurai.action.Action;
import dev.xhyrom.samurai.action.ActionType;
import dev.xhyrom.samurai.entity.NPC;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import net.minestom.server.coordinate.Pos;

import java.util.Map;

public class NPCSerializer implements ObjectSerializer<NPC> {
    @Override
    public boolean supports(@NonNull Class<? super NPC> type) {
        return NPC.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull NPC object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("location", object.getLocation());
        data.add("skin", object.skinName());
        data.add("action", object.action());
    }

    @Override
    public NPC deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        Pos location = data.get("location", Pos.class);
        String skin = data.get("skin", String.class);

        Map<String, ?> rawAction = (Map<String, ?>) data.getRaw("action");
        ActionType actionType = ActionType.valueOf((String) rawAction.get("type"));
        Action action = data.get("action", actionType.resolve());

        return new NPC(location).skin(skin).action(action);
    }
}
