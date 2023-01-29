package dev.fluxi.futils.events;

import dev.fluxi.futils.utils.misc.Status;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class StatusChangeEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final Status status;

    public StatusChangeEvent(@NotNull Player who, Status status) {
        super(who);
        this.status = status;
    }


    public Status getStatus() {
        return status;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
