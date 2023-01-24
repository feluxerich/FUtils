package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResetCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Confirm with: /reset confirm");
            return true;
        }
        if (args[0].equals("config")) {
            for (String entry : FUtils.getInstance().getConfig().getKeys(false)) {
                FUtils.getInstance().getConfig().set(entry, null);
            }
            FUtils.getInstance().saveConfig();
            sender.sendMessage(ChatColor.RED + "Reset of the config successful");
            return true;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kick(Component.text("Server resetting")
                    .color(TextColor.fromHexString("#ef4444"))
                    .toBuilder().build());
        }
        FUtils.getInstance().getConfig().set("reset", true);
        FUtils.getInstance().saveConfig();

        ConfigurationSection configurationSection = Bukkit.spigot().getSpigotConfig().getConfigurationSection("settings");
        if (configurationSection == null || configurationSection.get("restart-script") == null) {
            Bukkit.shutdown();
            return true;
        }
        Bukkit.spigot().restart();
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Stream.of("confirm", "config").filter(suggestion -> suggestion.startsWith(args[0])).collect(Collectors.toList());
    }
}
