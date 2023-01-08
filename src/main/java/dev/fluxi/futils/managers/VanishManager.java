package dev.fluxi.futils.managers;

import dev.fluxi.futils.FUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class VanishManager implements Listener {
    private final FUtils plugin = FUtils.getInstance();
    private final List<Player> vanished = new ArrayList<>();

    public VanishManager() {
        plugin.registerEvent(this);
    }

    public boolean isVanished(Player player) {
        return vanished.contains(player);
    }

    public void toggleVanish(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (isVanished(player)) {
                onlinePlayer.showPlayer(plugin, player);
                continue;
            }
            onlinePlayer.hidePlayer(plugin, player);
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
        VanishManager vanishManager = plugin.getVanishManager();
        vanishManager.vanishAll(event.getPlayer());
    }
}
