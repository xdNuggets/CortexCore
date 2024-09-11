package dev.cortex.cortexcore;

import dev.cortex.cortexcore.util.configuration.YamlConfig;
import dev.cortex.cortexcore.util.logging.CortexLog;
import dev.cortex.cortexcore.util.logging.CortexLogLevel;
import org.bukkit.plugin.java.JavaPlugin;

public final class CortexCorePlugin extends JavaPlugin implements CortexCoreUtils {

    private static CortexCorePlugin instance;

    public static CortexCorePlugin getInstance() {
        return instance;
    }

    private YamlConfig configYml;

    @Override
    public void onLoad() {
        instance = this;
        CortexCore.onLoad(this);
        configYml = new YamlConfig("config");
        CortexLog.onLoad();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        CortexLog.divider();
        CortexLog.log(CortexLogLevel.SUCCESS, "CortexCore enabled!");
        CortexLog.log(
                "Version: " + getPluginMeta().getVersion(),
                "Developed by members of Cortex Development!"
        );
        CortexLog.divider();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        CortexLog.log(CortexLogLevel.SUCCESS, "CortexCore disabled!");
    }

    public void onReload() {
        // Plugin (built-in) reload logic

        configYml.save();
        configYml.reload();
        CortexLog.onReload();
    }

    @Override
    public YamlConfig getConfigYml() {
        return configYml;
    }

}
