package dev.cortex.command.annotation;

import org.jetbrains.annotations.NotNull;

public @interface Command {

    /**
     * The name of the command
     *
     * @return the command
     */
    @NotNull String name();

    /**
     * The aliases of the commmand
     * Can be empty
     * @return the alias array of the command
     */
    @NotNull String[] aliases() default {};

    /**
     * The description of the command
     * @return the description of the command
     */
    @NotNull String description() default "I'm a command!";
}
