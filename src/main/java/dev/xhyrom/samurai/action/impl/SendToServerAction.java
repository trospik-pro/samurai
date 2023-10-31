package dev.xhyrom.samurai.action.impl;

import dev.xhyrom.samurai.action.Action;
import dev.xhyrom.samurai.action.ActionType;
import dev.xhyrom.samurai.util.VelocityBridge;
import lombok.Getter;
import net.minestom.server.entity.Player;

public class SendToServerAction extends Action {
    @Getter
    private final String serverName;

    public static SendToServerAction of(String serverName) {
        return new SendToServerAction(serverName);
    }

    protected SendToServerAction(String serverName) {
        super(ActionType.SEND_TO_SERVER);

        this.serverName = serverName;
    }

    @Override
    public void execute(Player player) {
        VelocityBridge.sendPlayerToServer(player, serverName);
    }
}
