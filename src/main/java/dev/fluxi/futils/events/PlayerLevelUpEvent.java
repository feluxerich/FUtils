package dev.fluxi.futils.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLevelUpEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private int level;

    public PlayerLevelUpEvent(@NotNull Player who, int level) {
        super(who);
        level(level);
    }

    public int level() {
        return level;
    }

    private void level(int level) {
        this.level = level;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
