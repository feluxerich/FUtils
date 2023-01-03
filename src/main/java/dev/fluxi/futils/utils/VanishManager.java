package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VanishManager implements Listener {
    private final Plugin plugin;
    private final List<Player> vanished;


    public VanishManager(Plugin plugin) {
        this.plugin = plugin;
        this.vanished = new ArrayList<>();
    }

    public boolean isVanished(Player player) {
        return vanished.contains(player);
    }

    public void toggleVanish(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (isVanished(player)) {
                onlinePlayer.showPlayer(plugin, player);
            } else {
                onlinePlayer.hidePlayer(plugin, player);
            }
        }

        if (isVanished(player)) {
            vanished.remove(player);
            player.sendMessage(ChatColor.GREEN + "You are now visible");
            return;
        }
        vanished.add(player);
        player.sendMessage(ChatColor.GREEN + "You are now vanished");
    }

    public void vanishAll(Player player) {
        vanished.forEach(vanishedPlayer -> player.hidePlayer(plugin, vanishedPlayer));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        VanishManager vanishManager = FUtils.getInstance().getVanishManager();
        vanishManager.vanishAll(event.getPlayer());
    }
}
