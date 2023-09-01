package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.Samurai;
import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;

@UtilityClass
public final class Commands {
    public static void init() {
        if (!Samurai.config.debug)
            return;

        CommandManager commandManager = MinecraftServer.getCommandManager();
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        commandManager.register(new ExtensionsCommand());
        commandManager.register(new ListCommand());
        commandManager.register(new MemoryCommand());
        commandManager.register(new PerformanceCommand(
                globalEventHandler
        ));
        commandManager.register(new VersionCommand());
    }
}
