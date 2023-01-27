package dev.fluxi.futils.settings.goals.utils;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.events.PlayerKillEntityEvent;
import dev.fluxi.futils.settings.utils.ToggleableSetting;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;

public class KillMobGoal extends ToggleableSetting {
    private final EntityType entityType;

    public KillMobGoal(Material material, Component name, String config, EntityType entityType) {
        super(material, name, config);
        this.entityType = entityType;
    }

    @EventHandler
    public void onPlayerKillEntity(PlayerKillEntityEvent event) {
        if (event.getMob().getType() != entityType) return;
        FUtils.getInstance().getTimer().running(false);
    }
}