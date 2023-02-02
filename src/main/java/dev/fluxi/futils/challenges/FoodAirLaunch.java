package dev.fluxi.futils.challenges;

import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.util.Vector;

public class FoodAirLaunch extends Challenge {
    public FoodAirLaunch() {
        super(Material.GOLDEN_CARROT, coloredComponent("Food Air Launch"), "food-air-launch");
    }

    @EventHandler
    public void onPlayerConsumeItem(PlayerItemConsumeEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        event.getPlayer().setVelocity(new Vector(0, 3.7, 0));
    }
}
