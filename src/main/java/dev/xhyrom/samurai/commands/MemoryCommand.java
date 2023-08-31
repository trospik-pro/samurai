package dev.xhyrom.samurai.commands;

import net.minestom.server.command.builder.Command;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class MemoryCommand extends Command {
    public MemoryCommand() {
        super("memory");

        setDefaultExecutor((sender, context) -> {
            MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

            long used = heapMemoryUsage.getUsed();
            long xmx = heapMemoryUsage.getMax();
            long free = xmx - used;
            float percentage = Math.max(Math.min((float) used / xmx, 1.0F), 0.0F);

            sender.sendMessage("Xmx (Max) memory: " + xmx +
                    "\nUsed memory: " + used +
                    "\nAllocated memory: " + heapMemoryUsage.getCommitted() +
                    "\nXms memory: " + heapMemoryUsage.getInit() +
                    "\nFree memory: " + free +
                    "\nPercentage used: " + percentage + "%");
        });
    }
}
