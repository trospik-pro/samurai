package dev.xhyrom.samurai.config.serializers;

import dev.xhyrom.samurai.module.scoreboard.ScoreboardLine;
import dev.xhyrom.samurai.module.scoreboard.ScoreboardLineGroup;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.List;

public class ScoreboardLineGroupSerializer implements ObjectSerializer<ScoreboardLineGroup> {
    @Override
    public boolean supports(@NonNull Class<? super ScoreboardLineGroup> type) {
        return ScoreboardLineGroup.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull ScoreboardLineGroup object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("lines", object.getLines());
        data.add("refreshRate", object.getRefreshRate());
    }

    @Override
    public ScoreboardLineGroup deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        List<ScoreboardLine> line = data.getAsList("lines", ScoreboardLine.class);
        int refreshRate = data.get("refreshRate", int.class);

        return new ScoreboardLineGroup(line, refreshRate);
    }
}
