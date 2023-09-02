package dev.xhyrom.samurai.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minestom.server.entity.Player;

@UtilityClass
public class Placeholders {
    public TagResolver apply(Player player) {
        return TagResolver.resolver(
                "papi",
                (tag, attributes) -> {
                    String name = tag.popOr("requires name").value();
                    if (name.equals("player")) {
                        return Tag.selfClosingInserting(Component.text(player.getUsername()));
                    }

                    return Tag.selfClosingInserting(Component.text(""));
                }
        );
    }
}
