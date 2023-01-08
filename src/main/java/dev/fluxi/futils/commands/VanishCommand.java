package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.managers.VanishManager;
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
import java.util.stream.Collectors;

public class VanishCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        VanishManager vanishManager = FUtils.getInstance().getVanishManager();
        Player player;

        if (args.length == 1) {
            player = Bukkit.getPlayer(args[0]);
        } else if (sender instanceof Player) {
             player = (Player) sender;
        } else {
            sender.sendMessage(ChatColor.RED + "You have to be a Player");
            return false;
        }
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "This Player is not online");
            return false;
        }
        vanishManager.toggleVanish(player);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            suggestions.add(onlinePlayer.getName());
        }
        return suggestions.stream().filter(suggestion -> suggestion.startsWith(args[0])).collect(Collectors.toList());
    }
}
