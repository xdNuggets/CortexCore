package dev.cortex.item;


import dev.cortex.exceptions.InvalidMaterialProvidedException;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private Material material;
    private ItemMeta meta;

    public ItemBuilder() {
        this.item = new ItemStack(Material.AIR);
        this.material = Material.AIR;
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.material = material;
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
        this.material = item.getType();
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
        this.material = material;
        this.meta = item.getItemMeta();
    }

    public Material getMaterial() {
        return material;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setItemStack(ItemStack item) {
        this.item = item;
        this.material = item.getType();
        this.meta = item.getItemMeta();
        return this;
    }



    public ItemBuilder setItemName(String name) {
        this.meta.displayName(Component.text(name));
        return this;
    }

    public ItemBuilder lore(Component... line) {
        this.meta.lore(List.of(line));
        return this;
    }

    public ItemBuilder setItemAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setItemMeta(ItemMeta meta) {
        this.item.setItemMeta(meta);
        return this;
    }



    /**
     * Builds the item and returns it
     *
     * @return the item
     */
    private ItemStack build() throws InvalidMaterialProvidedException {
        if(this.material == null) {
            throw new InvalidMaterialProvidedException(this);
        }

        this.item.setItemMeta(this.meta);
        return item;
    }
}
