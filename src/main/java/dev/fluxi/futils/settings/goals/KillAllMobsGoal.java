package dev.fluxi.futils.settings.goals;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.events.PlayerKillEntityEvent;
import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class KillAllMobsGoal extends ToggleableSetting {
    private final List<EntityType> killedEntities = new ArrayList<>();

    public KillAllMobsGoal() {
        super(Material.SPAWNER, coloredComponent("Kill All Mobs"), "kill-all-mobs-goal");
    }

    @EventHandler
    public void onPlayerAdvancement(PlayerKillEntityEvent event) {
        if (killedEntities.contains(event.getMob().getType()) || event.getMob() instanceof Mob) return; // TODO: check if mob is in vanilla survival available
        killedEntities.add(event.getMob().getType());
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + event.getPlayer().getName() + " killed Mob: " + ChatColor.GOLD + event.getMob().getType().name());
        }
        for (EntityType type : EntityType.values()) {
            if (killedEntities.contains(type)) continue;
            return;
        }
        FUtils.getInstance().getTimer().running(false);
    }
}
