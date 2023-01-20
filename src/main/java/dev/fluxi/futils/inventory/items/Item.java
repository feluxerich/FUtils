package dev.fluxi.futils.inventory.items;

import dev.fluxi.futils.inventory.BaseInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Item {
    private final Material material;
    private Component name = Component.text("");
    private List<Component> description = null;

    public Item(Material material) {
        this.material = material;
    }

    public Item(Material material, Component name) {
        this.material = material;
        this.name = name;
    }

    public Item(Material material, Component name, List<Component> description) {
        this.material = material;
        this.name = name;
        this.description = description;
    }

    public void onClick() {}

    /**
     * Creates the item defined in this class.
     * @return Returns item as {@link ItemStack}.
     */
    public ItemStack item() {
        return BaseInventory.createItem(material, name, description);
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
