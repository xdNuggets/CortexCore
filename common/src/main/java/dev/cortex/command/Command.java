package dev.cortex.command;

import org.jetbrains.annotations.NotNull;

public interface Command<S, SC extends SubCommand<S>> {


    void addSubCommand(final SC subCommand);

    void addSubAlias(final @NotNull String alias, final SC subCommand);

}
