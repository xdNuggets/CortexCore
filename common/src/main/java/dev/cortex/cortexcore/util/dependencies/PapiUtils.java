package dev.cortex.cortexcore.util.dependencies;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PapiUtils {

    /**
     * If the server has the <a href="https://www.spigotmc.org/resources/placeholderapi.6245/">PlaceholderAPI</a> plugin.
     */
    public static final boolean HAS_PLACEHOLDER_API;

    static {
        HAS_PLACEHOLDER_API = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    /**
     * Parse any <a href="https://www.spigotmc.org/resources/placeholderapi.6245/">PlaceholderAPI</a> placeholders in the input string for the input player
     * @param player The {@link Player} to parse the placeholders for
     * @param input The {@link String} to parse the placeholders in
     * @return The parsed {@link String} or the input {@link String} if the server does not have PlaceholderAPI or the player is null
     */
    public static @NotNull String parse(final @Nullable Player player, final @NotNull String input) {
        if (!HAS_PLACEHOLDER_API || player == null) {
            return input;
        }

        return PlaceholderAPI.setPlaceholders(player, input);
    }

    /**
     * Parse any <a href="https://www.spigotmc.org/resources/placeholderapi.6245/">PlaceholderAPI</a> placeholders in the input string list for the input player
     * @param player The {@link Player} to parse the placeholders for
     * @param input The {@link List} of strings to parse the placeholders in
     * @return The parsed {@link String} {@link List} or the input {@link String} {@link List} if the server does not have PlaceholderAPI or the player is null
     */
    public static @NotNull List<String> parse(final @Nullable Player player, final @NotNull List<String> input) {
        if (!HAS_PLACEHOLDER_API || player == null) {
            return input;
        }

        return PlaceholderAPI.setPlaceholders(player, input);
    }

    /**
     * Parse any <a href="https://www.spigotmc.org/resources/placeholderapi.6245/">PlaceholderAPI</a> placeholders in the input string list for the input player
     * @param player The {@link Player} to parse the placeholders for
     * @param input The {@link String}(s) to parse the placeholders in
     * @return The parsed {@link String} {@link List} or the input as a {@link String} {@link List} if the server does not have PlaceholderAPI or the player is null
     * @see PapiUtils#parse(Player, List)
     */
    public static @NotNull List<String> parse(final @Nullable Player player, final @NotNull String... input) {
        return parse(player, List.of(input));
    }

}
