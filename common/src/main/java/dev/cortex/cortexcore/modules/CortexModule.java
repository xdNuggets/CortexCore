package dev.cortex.cortexcore.modules;

import dev.cortex.cortexcore.CortexCore;
import org.jetbrains.annotations.NotNull;

public abstract class CortexModule {

    private final String id;

    public CortexModule(final @NotNull String id) {
        this.id = id;
    }

    public final @NotNull String getId() {
        return id;
    }

    /**
     * Registers the module to the implemented {@link ModuleManager}
     */
    public final void register() {
        CortexCore.getUtils().getModuleManager().register(this);
    }

    /**
     * Called after the main class' onLoad method
     */
    public abstract void onLoad();

    /**
     * Called during the main class' onEnable method
     */
    public abstract void onEnable();

    /**
     * Called during the main class' onDisable method
     */
    public abstract void onDisable();

    /**
     * Called from the main class' onReload method when the plugin is reloaded via the built-in command
     */
    public abstract void onReload();

}
