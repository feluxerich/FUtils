package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().isOp()) {
            return;
        }
        event.getPlayer().sendMessage(
                Component.text("This Plugin is still under hard development.", Style.style(NamedTextColor.LIGHT_PURPLE))
        );
        if (!FUtils.getInstance().getTimer().running() && !FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            FUtils.getInstance().getInventoryManager().overridePlayerInventory(event.getPlayer());
        }
    }
}
