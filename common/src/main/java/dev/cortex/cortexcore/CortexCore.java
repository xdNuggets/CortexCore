package dev.cortex.cortexcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class CortexCore {

    private static JavaPlugin plugin;
    private static CortexCoreUtils utils;

    static void onLoad(final @NotNull JavaPlugin plugin) {
        if (!(plugin instanceof CortexCoreUtils)) {
            return;
        }

        CortexCore.plugin = plugin;
        utils = (CortexCoreUtils) plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static CortexCoreUtils getUtils() {
        return utils;
    }

}
