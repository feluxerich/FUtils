package dev.fluxi.futils.managers;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.guis.MainMenu;
import dev.fluxi.futils.inventory.guis.TimerMenu;
import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.inventory.guis.SpectatorCompassMenu;
import dev.fluxi.futils.utils.Base64;
import dev.fluxi.futils.utils.ConfigUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InventoryManager implements Listener {
    private final Map<OfflinePlayer, ItemStack[]> inventoryMap = new HashMap<>();

    public InventoryManager() {
        FUtils.getInstance().registerEvent(this);
        readConfig();
    }

    public boolean containsPlayer(Player player) {
        return inventoryMap.containsKey(player);
    }

    public void overridePlayerInventory(Player player) {
        inventoryMap.put(player, player.getInventory().getContents());
        player.getInventory().clear();
        writeConfig();
        if (!player.isOp()) {
            return;
        }

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.displayName(Component.text("Menu",
                Style.style(TextColor.fromHexString("#5b45ff"),
                        TextDecoration.ITALIC.withState(false))));
        skull.setItemMeta(skullMeta);

        player.getInventory().setItem(3, new Item(Material.COMPASS,
                Component.text("Spectator Compass",
                        Style.style(TextColor.fromHexString("#5b45ff"),
                                TextDecoration.ITALIC.withState(false)))).item());
        player.getInventory().setItem(4, skull);
        player.getInventory().setItem(5, new Item(
                Material.CLOCK,
                Component.text("Timer Settings",
                        Style.style(TextColor.fromHexString("#5b45ff"),
                                TextDecoration.ITALIC.withState(false)))
        ).item());
    }

    public void removePlayer(Player player) {
        player.getInventory().clear();
        if (containsPlayer(player)) {
            player.getInventory().setContents(inventoryMap.get(player));
        }
        inventoryMap.remove(player);
        writeConfig();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK || !containsPlayer(event.getPlayer())) return;
        switch (event.getMaterial()) {
            case COMPASS:
                event.getPlayer().openInventory(new SpectatorCompassMenu().inventory());
                break;
            case PLAYER_HEAD:
                event.getPlayer().openInventory(new MainMenu().inventory());
                break;
            case CLOCK:
                event.getPlayer().openInventory(new TimerMenu(
                        Component.text("Timer", Style.style(TextColor.fromHexString("#7866ff")))).inventory());
                break;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (FUtils.getInstance().getTimer().running()) {
            return;
        }
        overridePlayerInventory(event.getPlayer());
    }

    public void writeConfig() {
        ConfigurationSection section = ConfigUtils.getConfigSection("inventory");
        for (Map.Entry<OfflinePlayer, ItemStack[]> entry : inventoryMap.entrySet()) {
            section.set(Objects.requireNonNull(entry.getKey().getName()), Base64.serializeAndEncode(entry.getValue()));
        }
        FUtils.getInstance().saveConfig();
    }

    public void readConfig() {
        ConfigurationSection section = ConfigUtils.getConfigSection("inventory");
        for (String playerName : section.getKeys(false)) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
            inventoryMap.put(player, (ItemStack[]) Base64.deserializeAndDecode((String) section.get(playerName)));
        }
    }
}
