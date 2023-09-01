package dev.xhyrom.samurai.commands;

import lombok.experimental.UtilityClass;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;

@UtilityClass
public final class Commands {
    public static void init() {
        CommandManager commandManager = MinecraftServer.getCommandManager();

        commandManager.register(new ListCommand());
        commandManager.register(new MemoryCommand());
    }
}
