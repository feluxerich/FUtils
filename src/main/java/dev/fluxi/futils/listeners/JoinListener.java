package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.utils.VanishManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        VanishManager vanishManager = FUtils.getInstance().getVanishManager();
        vanishManager.vanishAll(event.getPlayer());
    }
}
