package dev.cortex.cortexcore.modules;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ModuleManager {

    void register(final @NotNull CortexModule module);
    @Nullable CortexModule getModule(final @NotNull String id);

}
