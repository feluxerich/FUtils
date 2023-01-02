package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.utils.challenge.Challenge;
import dev.fluxi.futils.utils.challenge.ChallengeManager;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChallengeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ChallengeManager challengeManager = FUtils.getInstance().getChallengeManager();
        switch (args[0]) {
            case "start":
                challengeManager.start();
                break;
            case "stop":
                challengeManager.stop();
                break;
            case "pause":
                challengeManager.pause();
                break;
            case "resume":
                challengeManager.resume();
                break;
            case "active": {
                List<String> activeChallenges = new ArrayList<>();
                for (Challenge activeChallenge : challengeManager.getActiveChallenges()) {
                    activeChallenges.add(activeChallenge.challengeName);
                }
                String active = "No active challenges";
                if (!activeChallenges.isEmpty()) {
                    active = StringUtils.join(activeChallenges, ", ");
                }
                sender.sendMessage(active);
                break;
            }
            case "enable": {
                if (args.length < 2) {
                    return false;
                }
                Challenge challenge = challengeManager.getChallenge(args[1]);
                challenge.setActive(true);
                sender.sendMessage(ChatColor.GREEN + challenge.challengeName + " enabled");
                break;
            }
            case "disable": {
                if (args.length < 2) {
                    return false;
                }
                Challenge challenge = challengeManager.getChallenge(args[1]);
                challenge.setActive(false);
                sender.sendMessage(ChatColor.GREEN + challenge.challengeName + " disabled");
                break;
            }
            case "gamemode":
            case "gm":
                challengeManager.gameMode(GameMode.SURVIVAL);
                break;
            default: return false;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("start", "stop", "pause", "resume", "active", "enable", "disable", "gamemode", "gm");
        }
        return FUtils.getInstance().getChallengeManager().getChallengeNames();
    }
}
