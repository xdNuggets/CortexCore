package dev.cortex.cortexcore.util.logging;

import org.jetbrains.annotations.NotNull;

public enum CortexLogLevel {

    INFO,
    SUCCESS("<green>"),
    WARN("<yellow>"),
    ERROR("<red>");

    final @NotNull String color;

    CortexLogLevel(final @NotNull String color) {
        this.color = color;
    }

    CortexLogLevel() {
        this("");
    }

}
