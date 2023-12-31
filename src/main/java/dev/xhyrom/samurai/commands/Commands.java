package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.Samurai;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;

@UtilityClass
public final class Commands {
    public static void init() {
        CommandManager commandManager = MinecraftServer.getCommandManager();

        commandManager.register(new ListCommand());
        commandManager.setUnknownCommandCallback((sender, command) ->
                sender.sendMessage(MiniMessage.miniMessage().deserialize(Samurai.config.messages.unknownCommand))
        );

        if (!Samurai.config.debug)
            return;

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        commandManager.register(new ExtensionsCommand());
        commandManager.register(new MemoryCommand());
        commandManager.register(new PerformanceCommand(
                globalEventHandler
        ));
        commandManager.register(new InventoryCommand());
        commandManager.register(new VersionCommand());
    }
}
