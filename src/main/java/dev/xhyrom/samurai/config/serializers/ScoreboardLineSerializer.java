package dev.xhyrom.samurai.config.serializers;

import dev.xhyrom.samurai.module.scoreboard.ScoreboardLine;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public class ScoreboardLineSerializer implements ObjectSerializer<ScoreboardLine> {
    @Override
    public boolean supports(@NonNull Class<? super ScoreboardLine> type) {
        return ScoreboardLine.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull ScoreboardLine object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("text", object.getText());
    }

    @Override
    public ScoreboardLine deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        String text = data.get("text", String.class);

        return ScoreboardLine.of(text);
    }
}
