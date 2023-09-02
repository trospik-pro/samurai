package dev.xhyrom.samurai.listeners;

import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.GlobalEventHandler;

@UtilityClass
public final class Listeners {
    public static void init() {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        EventNode<Event> entityNode = EventNode.type("listeners", EventFilter.ALL);

        entityNode
                .addListener(new PlayerLogin())
                .addListener(new PlayerDisconnect())
                .addListener(new PlayerSpawn())
                .addListener(new InventoryPreClick())
                .addListener(new ItemDrop())
                .addListener(new PlayerSwapItem())
                .addListener(new PlayerUseItem())
                .addListener(new PlayerDeath())
                .addListener(new AsyncPlayerPreLogin())
                .addListener(new ServerListPing());

        globalEventHandler.addChild(entityNode);
    }
}
