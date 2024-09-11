package dev.cortex.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import dev.cortex.exceptions.InvalidMaterialProvidedException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Created on: 2024-09-11
 * Author: Simon
 */

/**
 * A concrete implementation of {@link ItemBuilder} for building {@link ItemStack} instances
 * with {@link SkullMeta}. This class is specifically designed for items that use the player skull
 * {@link ItemMeta} and allows setting player textures and profiles.
 */
public final class SkullItemBuilder extends ItemBuilder<SkullItemBuilder, SkullMeta> {

    private static final MethodHandle CRAFT_META_SKULL_SET_PROFILE_METHOD_HANDLE;

    static {
        MethodType type = MethodType.methodType(void.class, GameProfile.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            CRAFT_META_SKULL_SET_PROFILE_METHOD_HANDLE = lookup.findVirtual(Objects.requireNonNull(Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD)).getClass(), "setProfile", type);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    /**
     * Constructs a {@link SkullItemBuilder} for the specified {@link ItemStack}.
     *
     * @param item The {@link ItemStack} to be customized.
     * @throws InvalidMaterialProvidedException If the provided item is null or invalid.
     */
    public SkullItemBuilder(ItemStack item) throws InvalidMaterialProvidedException {
        super(SkullItemBuilder.class, SkullMeta.class, item);
    }

    /**
     * Constructs a {@link SkullItemBuilder} for a new player head {@link ItemStack}.
     *
     * @throws InvalidMaterialProvidedException If the default player head item is invalid.
     */
    public SkullItemBuilder() throws InvalidMaterialProvidedException {
        this(new ItemStack(Material.PLAYER_HEAD));
    }

    /**
     * Copies the textures of the given {@link OfflinePlayer} to the item.
     *
     * @param player The {@link OfflinePlayer} whose textures to copy.
     * @return The builder instance for method chaining.
     */
    public SkullItemBuilder copyTextures(OfflinePlayer player) {
        return this.withProperties(meta -> meta.setOwningPlayer(player));
    }

    /**
     * Copies the textures of the player identified by the given UUID to the item.
     *
     * @param playerUUID The UUID of the player whose textures to copy.
     * @return The builder instance for method chaining.
     */
    public SkullItemBuilder copyTextures(UUID playerUUID) {
        return this.copyTextures(Bukkit.getOfflinePlayer(playerUUID));
    }

    /**
     * Sets the textures of the item using a base64 encoded string.
     *
     * @param base64Textures The base64 encoded string representing the textures.
     * @return The builder instance for method chaining.
     */
    public SkullItemBuilder setTextures(String base64Textures) {
        return this.withProperties(meta -> {
            GameProfile textureHolderProfile = new GameProfile(UUID.randomUUID(), String.valueOf(new Random().nextInt()));
            PropertyMap textureHolderMap = textureHolderProfile.getProperties();

            Property texture = new Property("textures", base64Textures);

            textureHolderMap.removeAll("textures");
            textureHolderMap.put("textures", texture);

            Class<?> metaClass = meta.getClass();

            try {
                CRAFT_META_SKULL_SET_PROFILE_METHOD_HANDLE.invoke(meta, textureHolderProfile);
            } catch (Throwable throwable) {
                throw new RuntimeException(String.format("Exception encountered while invoking %s.setProfile(GameProfile)", metaClass.getName()));
            }
        });
    }
}