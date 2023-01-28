package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.events.PlayerDamageEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!FUtils.getInstance().getTimer().running() && !FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            FUtils.getInstance().getInventoryManager().overridePlayerInventory(event.getPlayer());
        }
        if (!event.getPlayer().isOp()) return;
        event.getPlayer().sendMessage(
                Component.text("This Plugin is still under hard development.", Style.style(NamedTextColor.LIGHT_PURPLE))
        );
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            FUtils.getInstance().getInventoryManager().removePlayer(event.getPlayer());
        }
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> {
            if (Bukkit.getOnlinePlayers().size() > 0) FUtils.getInstance().getTimer().running(false);
        }, 1);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        event.getPlayer().openInventory(((Player) event.getRightClicked()).getInventory());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        PlayerDamageEvent playerDamageEvent = new PlayerDamageEvent((Player) event.getEntity(), event.getCause(), event.getDamage(), event);
        Bukkit.getPluginManager().callEvent(playerDamageEvent);
    }

    @EventHandler
    public void onPlayerDamage(PlayerDamageEvent event) {
        if (FUtils.getInstance().getTimer().running()) return;
        event.setCancelled(true);
    }
}
