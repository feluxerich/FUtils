package dev.fluxi.futils.challenges;

import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnlyDown extends Challenge {
    public OnlyDown() {
        super(Material.DEEPSLATE_COAL_ORE, coloredComponent("Only Down"), "only-down");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        if (event.getTo().getBlockY() > event.getFrom().getBlockY()) {
            event.getPlayer().setHealth(0);
        }
    }
}
