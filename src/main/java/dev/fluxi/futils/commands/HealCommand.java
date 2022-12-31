package dev.fluxi.futils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HealCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "This player is not online");
                return false;
            }
            healPlayer(player);
        }
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            healPlayer(onlinePlayer);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            suggestions.add(onlinePlayer.getName());
        }
        return suggestions;
    }

    private void healPlayer(Player player) {
        player.setHealth(20d);
        player.setFoodLevel(20);
    }
}
