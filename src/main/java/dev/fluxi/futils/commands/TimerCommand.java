package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {
    FUtils fUtils = FUtils.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "resume":
                if (fUtils.getTimer().isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Timer is already running");
                    break;
                }
                fUtils.getTimer().setRunning(true);
                showTimerIfHidden();
                break;
            case "pause":
                if (!fUtils.getTimer().isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Timer is already paused");
                    break;
                }
                fUtils.getTimer().setRunning(false);
                showTimerIfHidden();
                break;
            case "reset":
                fUtils.getTimer().setRunning(false).setTime(0);
                showTimerIfHidden();
                break;

            case "remove":
                fUtils.getTimer().setRunning(false);
                fUtils.getTimer().setTime(0);
                fUtils.getTimer().setHidden(true);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Please specify an operation");
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Arrays.asList("resume", "pause", "reset", "remove");
    }

    private void showTimerIfHidden() {
        if (!fUtils.getTimer().isHidden()) {
            return;
        }
        fUtils.getTimer().setHidden(false);
    }
}
