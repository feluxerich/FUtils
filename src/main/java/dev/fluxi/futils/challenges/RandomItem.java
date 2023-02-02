package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.challenges.utils.TimedChallenge;
import dev.fluxi.futils.utils.RandomUtils;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RandomItem extends TimedChallenge {
    private BossBar bossBar;

    public RandomItem() {
        super(Material.CHEST, coloredComponent("Random Item"), "random-item", 20, 20);
    }

    @Override
    public void run() {
        if (!FUtils.getInstance().getTimer().running()) return;
        int time = FUtils.getInstance().getTimer().time();
        float progress = (time % 60) / 60F;
        bossBar.progress(progress);
        if (time % 60 != 0) return;
        giveItem();
        System.out.println(1);
    }

    @Override
    public void enable() {
        super.enable();
        updateBossBar();
    }

    @Override
    public void disable() {
        super.disable();
        hideBossBar();
    }

    private void giveItem() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            giveItem(player);
        }
    }

    private void giveItem(Player player) {
        player.getWorld().dropItem(player.getLocation(), getRandomItem());
    }

    private ItemStack getRandomItem() {
        Material material = Material.values()[RandomUtils.random(Material.values().length)];
        return new ItemStack(material);
    }

    private void updateBossBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            createBossBar(player);
        }
    }

    private void createBossBar(Player player) {
        bossBar = BossBar.bossBar(coloredComponent("New Item"), 0, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS);
        player.showBossBar(bossBar);
    }

    private void hideBossBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.hideBossBar(bossBar);
        }
    }
}
