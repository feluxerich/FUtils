package dev.fluxi.futils.inventory.items;

import dev.fluxi.futils.inventory.BaseInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Item {
    private Material material = null;
    private Component name = Component.text("");
    private List<Component> description = null;
    private int amount = 1;
    private ItemStack item = null;

    public Item(ItemStack item) {
        this.item = item;
    }

    public Item(ItemStack item, int amount) {
        this.amount = amount;
        this.item = item;
    }

    public Item(Material material) {
        this.material = material;
    }

    public Item(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public Item(Material material, Component name) {
        this.material = material;
        this.name = name;
    }

    public Item(Material material, Component name, int amount) {
        this.material = material;
        this.name = name;
        this.amount = amount;
    }

    public Item(Material material, Component name, List<Component> description) {
        this.material = material;
        this.name = name;
        this.description = description;
    }

    public Item(Material material, Component name, List<Component> description, int amount) {
        this.material = material;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public void onClick(Player clicker) {}

    /**
     * Creates the item defined in this class.
     * @return Returns item as {@link ItemStack}.
     */
    public ItemStack item() {
        if (item != null) {
            return item;
        }
        return BaseInventory.createItem(material, name, description, amount);
    }

    public Material material() {
        return material;
    }

    public Component name() {
        return name;
    }

    public void name(Component name) {
        this.name = name;
    }

    public List<Component> description() {
        return description;
    }

    public void description(List<Component> description) {
        this.description = description;
    }
}
