package dev.xhyrom.samurai.action;

import lombok.Getter;
import net.minestom.server.entity.Player;

public abstract class Action {
    @Getter
    private final ActionType type;

    protected Action(ActionType type) {
        this.type = type;
    }

    public abstract void execute(Player player);
}
