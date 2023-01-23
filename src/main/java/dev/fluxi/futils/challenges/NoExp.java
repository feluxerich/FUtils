package dev.fluxi.futils.challenges;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class NoExp extends Toggleable {
    public NoExp() {
        super(Material.EXPERIENCE_BOTTLE, coloredComponent("No Experience"));
    }

    @EventHandler
    public void onPlayerPickupExp(PlayerPickupExperienceEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        event.getPlayer().setHealth(0);
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        event.getPlayer().setHealth(0);
    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        event.getPlayer().setHealth(0);
    }
}
