package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.events.PlayerDamageEvent;
import dev.fluxi.futils.events.PlayerLevelUpEvent;
import dev.fluxi.futils.events.StatusChangeEvent;
import dev.fluxi.futils.managers.InventoryManager;
import dev.fluxi.futils.utils.misc.Status;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {
    InventoryManager inventoryManager = FUtils.getInstance().getInventoryManager();

    @EventHandler
    public void onStatusChange(StatusChangeEvent event) {
        Player player = event.getPlayer();
        if (event.getStatus() == Status.DEFAULT && FUtils.getInstance().getTimer().running()) {
            inventoryManager.setNormalInventory(player);
            return;
        }
        inventoryManager.setManagementInventory(player);
    }


    @EventHandler
    public void onPlayerDamage(PlayerDamageEvent event) {
        if (FUtils.getInstance().getTimer().running()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!FUtils.getInstance().getTimer().running()) inventoryManager.setManagementInventory(event.getPlayer());
        if (!event.getPlayer().isOp()) return;
        event.getPlayer().sendMessage(
                Component.text("This Plugin is still under hard development.", Style.style(NamedTextColor.LIGHT_PURPLE))
        );
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        inventoryManager.setNormalInventory(event.getPlayer(), false);
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
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        if (event.getNewLevel() < event.getOldLevel()) return;
        PlayerLevelUpEvent playerLevelUpEvent = new PlayerLevelUpEvent(event.getPlayer(), event.getNewLevel() - event.getOldLevel());
        Bukkit.getPluginManager().callEvent(playerLevelUpEvent);
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Status status = Status.DEFAULT;
        switch (event.getNewGameMode()) {
            case CREATIVE:
                status = Status.SPECTATOR;
                break;
            case SPECTATOR:
                status = Status.SPECTATOR;
                event.getPlayer().setGameMode(GameMode.CREATIVE);
                break;
            case ADVENTURE:
                event.getPlayer().setGameMode(GameMode.SURVIVAL);
                break;
            default:
                break;
        }
        StatusChangeEvent statusChangeEvent = new StatusChangeEvent(event.getPlayer(), status);
        Bukkit.getPluginManager().callEvent(statusChangeEvent);
    }
}
