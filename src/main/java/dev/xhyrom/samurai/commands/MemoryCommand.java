package dev.xhyrom.samurai.commands;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
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

            sender.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<green>Ram Usage: <used>/<xmx> (<percent>)\n<green>Allocated: <allocated>\n<green>Free: <free>",
                    Placeholder.component("allocated", format(percentage, heapMemoryUsage.getCommitted())),
                    Placeholder.component("free", format(percentage, free)),
                    Placeholder.unparsed("percent", ((int) (percentage * 100)) + "%"),
                    Placeholder.component("used", format(percentage, used)),
                    Placeholder.component("xmx", format(percentage, xmx))
            ));
        });
    }

    public Component format(float percent, long v) {
        String color;
        if (percent < 0.60F) {
            color = "<gradient:#55ff55:#00aa00><text></gradient>";
        } else if (percent < 0.85F) {
            color = "<gradient:#ffff55:#ffaa00><text></gradient>";
        } else {
            color = "<gradient:#ff5555:#aa0000><text></gradient>";
        }

        String value;
        if (v < 1024) {
            value = v + "B";
        } else {
          int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
          value = String.format("%.1f%s", (double) v / (1L << (z * 10)), "BKMGTPE".charAt(z));
       }

        return MiniMessage.miniMessage().deserialize(color, Placeholder.unparsed("text", value));
    }
}
