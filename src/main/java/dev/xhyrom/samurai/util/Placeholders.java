package dev.xhyrom.samurai.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minestom.server.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class Placeholders {
    public TagResolver apply() {
        return TagResolver.resolver(
                "papi",
                (tag, attributes) -> {
                    String name = tag.popOr("requires name").value();
                    switch (name.toLowerCase()) {
                        case "date" -> {
                            String format = tag.pop().value();

                            Date date = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat(format);

                            return Tag.selfClosingInserting(Component.text(dateFormat.format(date)));
                        }
                        case "playercount" -> {
                            String serverName = tag.pop().value();

                            return Tag.selfClosingInserting(Component.text(String.valueOf(ServerPlayerCount.get(serverName))));
                        }
                    }

                    return Tag.selfClosingInserting(Component.text(""));
                }
        );
    }

    public TagResolver apply(Player player) {
        return TagResolver.resolver(
                "papi",
                (tag, attributes) -> {
                    String name = tag.popOr("requires name").value();
                    switch (name.toLowerCase()) {
                        case "player" -> {
                            return Tag.selfClosingInserting(Component.text(player.getUsername()));
                        }
                        case "date" -> {
                            String format = tag.pop().value();

                            Date date = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat(format);

                            return Tag.selfClosingInserting(Component.text(dateFormat.format(date)));
                        }
                        case "luckperms" -> {
                            String type = tag.pop().value();

                            if (type.equalsIgnoreCase("rank"))
                                return Tag.selfClosingInserting(Component.text(LuckPermsAccessor.getPrimaryGroup(player.getUuid())));
                        }
                        case "playercount" -> {
                            String serverName = tag.pop().value();

                            return Tag.selfClosingInserting(Component.text(String.valueOf(ServerPlayerCount.get(serverName))));
                        }
                    }

                    return Tag.selfClosingInserting(Component.text(""));
                }
        );
    }
}
