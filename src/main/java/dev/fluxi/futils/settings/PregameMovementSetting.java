package dev.fluxi.futils.settings;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PregameMovementSetting extends ToggleableSetting {
    public PregameMovementSetting() {
        super(Material.ICE, coloredComponent("Disable Pregame Movement"), "disable-pregame-movement");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL || FUtils.getInstance().getTimer().running()) {
            return;
        }
        event.setCancelled(true);
    }
}
