package dev.cortex.command;

import org.jetbrains.annotations.NotNull;

public interface SubCommand<C> {

    @NotNull String getName();

    @NotNull String[] getAliases();

    @NotNull String getDescription();

    @NotNull String getParentCommandName();

    void execute(final @NotNull C sender, final @NotNull String[] args);
}
