package dev.fluxi.futils.commands;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.utils.challenge.ChallengeManager;
import dev.fluxi.futils.utils.gui.ChallengeGui;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ChallengeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ChallengeManager challengeManager = FUtils.getInstance().getChallengeManager();
        if (args.length == 0 && (sender instanceof Player)) {
            Player player = (Player) sender;
            player.openInventory(new ChallengeGui(Component.text("Challenges"), challengeManager.getChallenges()).getInventory());
            return true;
        }

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
            case "gm":
                challengeManager.gameMode(GameMode.SURVIVAL);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Arrays.asList("start", "stop", "pause", "resume", "gm");
    }
}
