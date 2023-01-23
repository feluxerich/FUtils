package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.utils.ExtendedPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimerCommand implements CommandExecutor, TabCompleter {
    ExtendedPlugin fUtils = FUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("tt")) {
            fUtils.getTimer().toggle();
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "toggle":
                fUtils.getTimer().toggle();
                break;
            case "reset":
                fUtils.getTimer().running(false);
                fUtils.getTimer().time(0);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Please specify an operation");
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Stream.of("toggle", "reset").filter(suggestion -> suggestion.startsWith(args[0])).collect(Collectors.toList());
    }
}
