package dev.cortex.item;

import dev.cortex.exceptions.InvalidMaterialProvidedException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created on: 2024-09-11
 * Author: Simon
 */

/**
 * A concrete implementation of {@link ItemBuilder} for building {@link ItemStack} instances
 * with {@link ItemMeta}. This class is specifically designed for items that use the standard
 * {@link ItemMeta}.
 */
public final class RegularItemBuilder extends ItemBuilder<RegularItemBuilder, ItemMeta> {

    /**
     * Constructs a {@link RegularItemBuilder} for the specified {@link ItemStack}.
     *
     * @param item The {@link ItemStack} to be customized.
     * @throws InvalidMaterialProvidedException If the provided item is null or invalid.
     */
    public RegularItemBuilder(ItemStack item) throws InvalidMaterialProvidedException {
        super(RegularItemBuilder.class, ItemMeta.class, item);
    }

    /**
     * Constructs a {@link RegularItemBuilder} for the specified {@link Material}.
     *
     * @param material The {@link Material} to initialize the {@link ItemStack} with.
     * @throws InvalidMaterialProvidedException If the provided material is invalid.
     */
    public RegularItemBuilder(Material material) throws InvalidMaterialProvidedException {
        this(new ItemStack(material));
    }
}