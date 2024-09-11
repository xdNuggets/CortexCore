package dev.cortex.cortexcore.modules;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ModuleManagerImpl implements ModuleManager {

    private final Map<String, CortexModule> modules;

    public ModuleManagerImpl() {
        modules = new HashMap<>();
        registerModules();
    }

    @Override
    public void register(@NotNull CortexModule module) {
        modules.putIfAbsent(module.getId(), module);
    }

    @Override
    public @Nullable CortexModule getModule(@NotNull String id) {
        return modules.getOrDefault(id, null);
    }

    public void registerModules() {
        // new YourModule().register();
    }

    public void onLoad() {
        modules.forEach((id, module) -> module.onLoad());
    }

    public void onEnable() {
        modules.forEach((id, module) -> module.onEnable());
    }

    public void onDisable() {
        modules.forEach((id, module) -> module.onDisable());
    }

    public void onReload() {
        modules.forEach((id, module) -> module.onReload());
    }

}
