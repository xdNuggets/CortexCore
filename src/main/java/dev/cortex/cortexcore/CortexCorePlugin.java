package dev.cortex.cortexcore;


import dev.cortex.cortexcore.modules.ModuleManager;
import dev.cortex.cortexcore.modules.ModuleManagerImpl;
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

    private ModuleManagerImpl moduleManager;


    @Override
    public void onLoad() {
        instance = this;
        CortexCore.onLoad(this);
        configYml = new YamlConfig("config");
        CortexLog.onLoad();


        moduleManager = new ModuleManagerImpl();
        moduleManager.onLoad();

    }

    @Override
    public void onEnable() {
        // Plugin startup logic


        moduleManager.onEnable();


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


        moduleManager.onDisable();

        CortexLog.log(CortexLogLevel.SUCCESS, "CortexCore disabled!");
    }

    public void onReload() {
        // Plugin (built-in) reload logic

        configYml.save();
        configYml.reload();
        CortexLog.onReload();


        moduleManager.onReload();
    }

    @Override
    public YamlConfig getConfigYml() {
        return configYml;
    }


    @Override
    public ModuleManager getModuleManager() {
        return moduleManager;
    }


}
