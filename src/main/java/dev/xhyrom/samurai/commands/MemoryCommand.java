package dev.xhyrom.samurai.commands;

import dev.xhyrom.samurai.SamuraiBootstrap;
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
                    """
                            <gradient:#1E9AFE:#60DFCD><title></gradient> <white>• Ram usage details:
                            <white>Usage:</white> <used><dark_gray>/</dark_gray><xmx> <dark_gray>(</dark_gray><gray><percent></gray><dark_gray>)</dark_gray>
                            <white>Allocated:</white> <allocated>
                            <white>Free:</white> <free>""",
                    Placeholder.parsed("title", SamuraiBootstrap.PACKAGE.getImplementationTitle()),
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
            color = "<#7CB342><text></#7CB342>";
        } else if (percent < 0.85F) {
            color = "<#FFEC00><text></#FFEC00>";
        } else {
            color = "<#E74C3C><text></#E74C3C>";
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
