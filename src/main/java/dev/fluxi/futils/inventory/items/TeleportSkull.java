package dev.fluxi.futils.inventory.items;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TeleportSkull extends Item {
    Player player;
    public TeleportSkull(ItemStack item, Player player) {
        super(item);
        this.player = player;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().teleport(player.getLocation());
    }
}
