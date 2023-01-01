package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.utils.Challenge;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ChallengeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            // TODO: if no args are given open the challenge gui
            return false;
        }

        switch (args[0]) {
            case "start": {
                if (args.length < 2) {
                    return false;
                }

                Challenge challenge = FUtils.getInstance().getChallengeManager().getChallenge(args[1]);
                if (challenge == null) {
                    return false;
                }
                if (FUtils.getInstance().getChallengeManager().getActiveChallenge() != null) {
                    sender.sendMessage(ChatColor.RED + "There is already a challenge running");
                    return false;
                }
                challenge.startChallenge();
                sender.sendMessage(ChatColor.GREEN + challenge.getChallengeName() + " started");
                break;
            }
            case "stop": {
                Challenge challenge = FUtils.getInstance().getChallengeManager().getActiveChallenge().stopChallenge();
                sender.sendMessage(ChatColor.GREEN + challenge.getChallengeName() + " stopped");
                break;
            }
            case "pause": {
                Challenge challenge = FUtils.getInstance().getChallengeManager().getActiveChallenge().pauseChallenge();
                sender.sendMessage(ChatColor.GREEN + challenge.getChallengeName() + " paused");
                break;
            }
            case "resume": {
                Challenge challenge = FUtils.getInstance().getChallengeManager().getActiveChallenge().resumeChallenge();
                sender.sendMessage(ChatColor.GREEN + challenge.getChallengeName() + " resumed");
                break;
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("start", "stop", "pause", "resume");
        }
        return FUtils.getInstance().getChallengeManager().getChallengeNames();
    }
}
