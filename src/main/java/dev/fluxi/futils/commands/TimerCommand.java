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
        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "resume":
                if (fUtils.getTimer().running()) {
                    sender.sendMessage(ChatColor.RED + "Timer is already running");
                    break;
                }
                fUtils.getTimer().running(true);
                showTimerIfHidden();
                break;
            case "pause":
                if (!fUtils.getTimer().running()) {
                    sender.sendMessage(ChatColor.RED + "Timer is already paused");
                    break;
                }
                fUtils.getTimer().running(false);
                showTimerIfHidden();
                break;
            case "reset":
                fUtils.getTimer().running(false);
                fUtils.getTimer().time(0);
                showTimerIfHidden();
                break;

            case "remove":
                fUtils.getTimer().running(false);
                fUtils.getTimer().time(0);
                fUtils.getTimer().hidden(true);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Please specify an operation");
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Stream.of("resume", "pause", "reset", "remove").filter(suggestion -> suggestion.startsWith(args[0])).collect(Collectors.toList());
    }

    private void showTimerIfHidden() {
        if (!fUtils.getTimer().hidden()) {
            return;
        }
        fUtils.getTimer().hidden(false);
    }
}
