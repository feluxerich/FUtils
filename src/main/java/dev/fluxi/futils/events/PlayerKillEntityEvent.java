package dev.fluxi.futils.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerKillEntityEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final LivingEntity mob;

    public PlayerKillEntityEvent(@NotNull Player who, LivingEntity mob) {
        super(who);
        this.mob = mob;
    }

    public LivingEntity getMob() {
        return this.mob;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
