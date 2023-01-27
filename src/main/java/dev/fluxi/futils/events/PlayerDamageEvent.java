package dev.fluxi.futils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDamageEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final EntityDamageEvent.DamageCause cause;
    private final double damage;
    private final Cancellable event;

    public PlayerDamageEvent(@NotNull Player who, EntityDamageEvent.DamageCause cause, double damage, Cancellable event) {
        super(who);
        this.cause = cause;
        this.damage = damage;
        this.event = event;
    }

    public EntityDamageEvent.DamageCause getCause() {
        return cause;
    }

    public double getDamage() {
        return damage;
    }

    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    @Override
    public void setCancelled(boolean b) {
        event.setCancelled(b);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
