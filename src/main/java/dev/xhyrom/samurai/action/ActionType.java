package dev.xhyrom.samurai.action;

import dev.xhyrom.samurai.action.impl.OpenInventoryAction;
import dev.xhyrom.samurai.action.impl.SendToServerAction;

public enum ActionType {
    OPEN_INVENTORY,
    SEND_TO_SERVER;

    public Class<? extends Action> resolve() {
        return switch (this) {
            case OPEN_INVENTORY -> OpenInventoryAction.class;
            case SEND_TO_SERVER -> SendToServerAction.class;
        };
    }
}
