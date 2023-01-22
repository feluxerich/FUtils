package dev.fluxi.futils.inventory.guis;

import dev.fluxi.futils.inventory.ScrollMenu;
import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.inventory.items.TeleportSkull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SpectatorCompassMenu extends ScrollMenu {
    public SpectatorCompassMenu() {
        super(Component.text("Online Players", Style.style(TextColor.fromHexString("#5b45ff"))), getItems());
    }

    private static ItemStack createPlayerSkull(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skull.setItemMeta(skullMeta);
        skullMeta.displayName(Component.text(player.getName(),
                Style.style(TextColor.fromHexString("#5b45ff"),
                        TextDecoration.ITALIC.withState(false))));
        skull.setItemMeta(skullMeta);
        skull.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return skull;
    }

    public static List<Item> getItems() {
        List<Item> playerHeads = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerHeads.add(new TeleportSkull(createPlayerSkull(player), player));
        }
        return playerHeads;
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        items().get(event.getSlot() + scrollIndex).onClick((Player) event.getWhoClicked());
    }
}
