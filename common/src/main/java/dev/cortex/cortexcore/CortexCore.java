package dev.cortex.cortexcore;

import dev.cortex.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class CortexCore {

    private static JavaPlugin plugin;
    private static CortexCoreUtils utils;
    private static CommandManager<?> commandManager;

    static void onLoad(final @NotNull JavaPlugin plugin) {
        if (!(plugin instanceof CortexCoreUtils)) {
            return;
        }

        CortexCore.plugin = plugin;
        utils = (CortexCoreUtils) plugin;
        commandManager = new CommandManager<CommandSender>();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static CortexCoreUtils getUtils() {
        return utils;
    }

}
