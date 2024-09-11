package dev.cortex.cortexcore;

import dev.cortex.cortexcore.modules.ModuleManager;
import dev.cortex.cortexcore.util.configuration.YamlConfig;

public interface CortexCoreUtils {

    YamlConfig getConfigYml();

    ModuleManager getModuleManager();


}
