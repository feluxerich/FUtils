package dev.fluxi.futils.settings;

import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class SoupSetting extends ToggleableSetting {
    public SoupSetting() {
        super(Material.MUSHROOM_STEW, coloredComponent("Soup Healing"), "soup-healing");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK ||
                player.getGameMode() != GameMode.SURVIVAL ||
                player.getHealth() >= Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue())
            return;
        if (event.getMaterial() != Material.MUSHROOM_STEW) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
        player.getInventory().setItemInMainHand(new ItemStack(Material.BOWL));
    }
}
