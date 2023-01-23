package dev.fluxi.futils.challenges;

import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.util.Vector;

public class PickupItemAirLaunch extends Toggleable {
    public PickupItemAirLaunch() {
        super(Material.DROPPER, coloredComponent("Pickup Item Air Launch"));
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player) || !ChallengeUtils.shouldExecute((Player) event.getEntity())) {
            return;
        }
        event.getEntity().setVelocity(new Vector(0, 3.7, 0));
    }
}
