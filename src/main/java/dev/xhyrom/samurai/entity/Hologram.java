package dev.xhyrom.samurai.entity;

import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.display.AbstractDisplayMeta;
import net.minestom.server.entity.metadata.display.TextDisplayMeta;
import net.minestom.server.instance.Instance;

@Getter
public class Hologram extends Entity {
    private final Point location;
    private final String text;

    public Hologram(Point location, String text) {
        super(EntityType.TEXT_DISPLAY);

        this.location = location;
        this.text = text;

        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setNotifyAboutChanges(false);

        meta.setHasNoGravity(true);
        meta.setBillboardRenderConstraints(AbstractDisplayMeta.BillboardConstraints.CENTER);
        meta.setText(MiniMessage.miniMessage().deserialize(text));
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

    public Hologram build() {
        TextDisplayMeta meta = (TextDisplayMeta) this.getEntityMeta();
        meta.setNotifyAboutChanges(true);

        return this;
    }

    public void spawn(Instance instance) {
        this.setInstance(instance, location);
    }
}
