package dev.xhyrom.samurai.entity;

import dev.xhyrom.samurai.util.Placeholders;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.display.AbstractDisplayMeta;
import net.minestom.server.entity.metadata.display.TextDisplayMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.timer.TaskSchedule;

@Getter
public class Hologram extends Entity {
    private final Point location;
    private final String text;
    private int textRefreshRate = 0;

    public Hologram(Point location, String text) {
        super(EntityType.TEXT_DISPLAY);

        this.location = location;
        this.text = text;

        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setNotifyAboutChanges(false);

        meta.setHasNoGravity(true);
        meta.setBillboardRenderConstraints(AbstractDisplayMeta.BillboardConstraints.CENTER);
        meta.setText(MiniMessage.miniMessage().deserialize(text, Placeholders.apply()));
    }

    public boolean shadow() {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        return meta.isShadow();
    }

    public Hologram shadow(boolean shadow) {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setShadow(shadow);

        return this;
    }

    public int bgColor() {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        return meta.getBackgroundColor();
    }

    public Hologram bgColor(int bgColor) {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setBackgroundColor(bgColor);

        return this;
    }

    public Vec scale() {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        return meta.getScale();
    }

    public Hologram scale(Vec scale) {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setScale(scale);

        return this;
    }

    public int textRefreshRate() {
        return this.textRefreshRate;
    }

    public Hologram textRefreshRate(int textRefreshRate) {
        this.textRefreshRate = textRefreshRate;

        return this;
    }

    public Hologram build() {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setNotifyAboutChanges(true);

        if (this.textRefreshRate == 0)
            return this;

        MinecraftServer.getSchedulerManager().buildTask(() -> {
            meta.setText(MiniMessage.miniMessage().deserialize(text, Placeholders.apply()));
        }).repeat(TaskSchedule.tick(this.textRefreshRate)).schedule();

        return this;
    }

    public void spawn(Instance instance) {
        this.setInstance(instance, location);
    }
}
