package dev.cortex.item;

import dev.cortex.exceptions.InvalidMaterialProvidedException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A generic builder for creating {@link ItemStack} instances with customized {@link ItemMeta}.
 * This abstract class defines a fluent API for setting various properties such as type, amount,
 * display name, lore, enchantments, and more.
 *
 * @param <T> The type of the concrete builder class that extends this class.
 * @param <M> The type of the {@link ItemMeta} associated with the {@link ItemStack}.
 */
public abstract class ItemBuilder<T extends ItemBuilder<?, ?>, M extends ItemMeta> {

    private final Class<T> thisClass;
    private final Class<M> metaClass;
    private final ItemStack item;
    private boolean hasItemMeta;

    private static final Predicate<ItemBuilder<?, ?>> CONST_TRUE_PRED = builder -> true;
    private static final Predicate<ItemBuilder<?, ?>> CONST_FALSE_PRED = builder -> false;

    /**
     * Constructs an {@link ItemBuilder} for the specified {@link ItemStack}.
     *
     * @param thisClass The class of the builder (used for fluent API casting).
     * @param metaClass The class of the {@link ItemMeta} associated with the item.
     * @param item The {@link ItemStack} to be customized.
     * @throws InvalidMaterialProvidedException If the provided item is null or invalid.
     */
    public ItemBuilder(Class<T> thisClass, Class<M> metaClass, ItemStack item) throws InvalidMaterialProvidedException {
        if (thisClass == null) {
            throw new NullPointerException("The provided class is null");
        }

        if (metaClass == null) {
            throw new NullPointerException("The provided class is null");
        }

        if (item == null) {
            throw new InvalidMaterialProvidedException(item.getType());
        }

        if (item.hasItemMeta()) {
            this.hasItemMeta = false;
        } else {
            if (!metaClass.isInstance(item.getItemMeta())) {
                throw new IllegalArgumentException(String.format("The provided item has an invalid ItemMeta, %s is not an instance of %s", Objects.requireNonNull(item.getItemMeta()).getClass().getName(), metaClass.getName()));
            }

            this.hasItemMeta = true;
        }

        this.thisClass = thisClass;
        this.metaClass = metaClass;
        this.item = item;
    }

    /**
     * Sets the type of the {@link ItemStack}.
     *
     * @param material The material type to set.
     * @return The builder instance for method chaining.
     */
    public T withType(Material material) {
        this.item.setType(material);
        return thisClass.cast(this);
    }


    /**
     * Sets the amount of the {@link ItemStack}.
     *
     * @param amount The amount to set.
     * @return The builder instance for method chaining.
     */
    public T withAmount(int amount) {
        this.item.setAmount(amount);
        return thisClass.cast(this);
    }

    /**
     * Sets the display name of the item.
     *
     * @param displayName The display name to set (supports color codes).
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public T withDisplayName(String displayName) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        return this.withProperties(meta -> meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName == null ? " " : displayName)));
    }

    /**
     * Sets the lore of the item.
     *
     * @param lore The lore to set (supports color codes).
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public T withLore(List<String> lore) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        if (lore != null) {
            lore = lore.stream()
                    .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                    .collect(Collectors.toList());
        }

        List<String> finalLore = lore;
        return this.withProperties(meta -> meta.setLore(finalLore == null ? Collections.emptyList() : finalLore));
    }

    public T withLore(String... lines) {
        return this.withLore(lines == null ? null : Arrays.asList(lines));
    }

    public T withLore(String firstLine) {
        return this.withLore(firstLine == null ? null : Collections.singletonList(firstLine));
    }

    /**
     * Adds custom model data to the item.
     *
     * @param customModelData The custom model data integer.
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public T withCustomModelData(int customModelData) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        return this.withProperties(meta -> meta.setCustomModelData(customModelData));
    }

    /**
     * Adds enchantment to the item.
     *
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     * @param ignoreLevelRestriction If true, ignores normal level restrictions.
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public T withEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        return this.withProperties(meta -> meta.addEnchant(enchantment, level, ignoreLevelRestriction));
    }

    /**
     * Adds {@link ItemFlag}s to the item's {@link ItemMeta}.
     *
     * @param flags The {@link ItemFlag}s to add.
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public T withItemFlag(ItemFlag... flags) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        return this.withProperties(meta -> meta.addItemFlags(flags));
    }

    /**
     * Sets persistent data on the item's {@link ItemMeta}.
     *
     * @param key The {@link NamespacedKey} identifying the persistent data.
     * @param type The {@link PersistentDataType} of the data.
     * @param value The value to set.
     * @param <P> The type of the persistent data key.
     * @param <C> The type of the persistent data value.
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public <P, C> T withPersistentData(NamespacedKey key, PersistentDataType<P, C> type, C value) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        return this.withProperties(meta -> meta.getPersistentDataContainer().set(key, type, value));
    }

    /**
     * Applies the given {@link Consumer} to the item's {@link ItemMeta} if the specified condition is met.
     *
     * @param metaMapper The {@link Consumer} to apply to the {@link ItemMeta}.
     * @return The builder instance for method chaining.
     * @throws IllegalStateException If the item does not have an {@link ItemMeta}.
     */
    public T withProperties(Consumer<M> metaMapper) {
        if (!this.hasItemMeta) {
            throw new IllegalStateException("This method requires items that have an ItemMeta");
        }

        ItemMeta meta = this.item.getItemMeta();
        metaMapper.accept(this.metaClass.cast(meta));
        this.item.setItemMeta(meta);

        return thisClass.cast(this);
    }

    /**
     * Applies the given {@link Consumer} to this builder if the specified {@link Predicate} evaluates to true.
     *
     * @param test The {@link Predicate} to test the condition.
     * @param application The {@link Consumer} to apply if the condition is true.
     * @return The builder instance for method chaining.
     */
    public T applyIf(Predicate<ItemBuilder<?, ?>> test, Consumer<ItemBuilder<?, ?>> application) {
        if (test.test(this)) {
            application.accept(this);
        }
        return (T) this;
    }

    /**
     * Applies the given {@link Consumer} to this builder if the specified condition is true.
     *
     * @param condition The condition to test.
     * @param application The {@link Consumer} to apply if the condition is true.
     * @return The builder instance for method chaining.
     */
    public T applyIf(boolean condition, Consumer<ItemBuilder<?, ?>> application) {
        return applyIf(condition ? CONST_TRUE_PRED : CONST_FALSE_PRED, application);
    }

    /**
     * Returns the final result as a cloned {@link ItemStack}.
     *
     * @return The customized {@link ItemStack}.
     */
    public ItemStack result() {
        return this.item.clone();
    }
}