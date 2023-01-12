package dev.fluxi.futils.settings;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Hardcore extends GuiAccessible {
    World world;

    public Hardcore() {
        name("Hardcore");
        itemMaterial(Material.TOTEM_OF_UNDYING);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (!active()) {
            return;
        }
        FUtils.getInstance().getVanishManager().toggleVanish(event.getPlayer());
    }
}