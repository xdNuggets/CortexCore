package dev.cortex.cortexcore.util.logging;

import dev.cortex.cortexcore.CortexCore;
import dev.cortex.cortexcore.util.MiniColor;
import dev.cortex.cortexcore.util.configuration.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CortexLog {

    private static final @NotNull String PREFIX;
    private static final @NotNull String DEBUG_PREFIX;
    private static final @NotNull String DIVIDER;

    static {
        PREFIX = "[CortexCore] ";
        DEBUG_PREFIX = "[<yellow>i<reset>] ";
        DIVIDER = "------------------------------";
    }

    private static boolean debugMode;
    private static boolean allowFormatting;

    public static void onLoad() {
        onReload();
    }

    public static void onReload() {
        final YamlConfig config = CortexCore.getUtils().getConfigYml();
        debugMode = config.options().getBoolean("logging.debug-mode", false);
        allowFormatting = config.options().getBoolean("logging.allow-formatting", true);
    }

    private static void send(final @NotNull CortexLogLevel level, final boolean debug, final @NotNull List<String> message) {
        if (debug && !debugMode) {
            return;
        }

        final ConsoleCommandSender sender = Bukkit.getConsoleSender();

        for (final String m : message) {
            final StringBuilder builder = new StringBuilder(PREFIX);
            if (debug) {
                builder.append(DEBUG_PREFIX);
            }

            builder.append(level.color)
                    .append(m);

            String toSend = builder.toString();

            if (!allowFormatting) {
                toSend = MiniColor.CONSOLE.stripTags(toSend);
            }

            sender.sendMessage(MiniColor.CONSOLE.deserialize(toSend));
        }
    }

    private static void send(final @NotNull CortexLogLevel level, final boolean debug, final @NotNull String... message) {
        send(level, debug, List.of(message));
    }



    /**
     * Send a message to the console
     * @param level {@link CortexLogLevel} the level to send the message as
     * @param input The {@link String} {@link List} message to send to the console
     */
    public static void log(final @NotNull CortexLogLevel level, final @NotNull List<String> input) {
        send(level, false, input);
    }

    /**
     * Send a message to the console
     * @param level {@link CortexLogLevel} the level to send the message as
     * @param input The {@link String}(s) to send to the console
     * @see CortexLog#log(CortexLogLevel, List)
     */
    public static void log(final @NotNull CortexLogLevel level, final @NotNull String... input) {
        log(level, List.of(input));
    }

    /**
     * Send a message to the console
     * <br>Will send at the {@link CortexLogLevel#INFO} level
     * @param input The {@link String} {@link List} message to send to the console
     * @see CortexLog#log(CortexLogLevel, List)
     */
    public static void log(final @NotNull List<String> input) {
        log(CortexLogLevel.INFO, input);
    }

    /**
     * Send a message to the console
     * <br>Will send at the {@link CortexLogLevel#INFO} level
     * @param input The {@link String}(s) to send to the console
     * @see CortexLog#log(CortexLogLevel, List)
     */
    public static void log(final @NotNull String... input) {
        log(CortexLogLevel.INFO, List.of(input));
    }

    /**
     * Send a stacktrace to the console
     * @param level The {@link CortexLogLevel} level to send the stacktrace as
     * @param throwable The {@link Throwable} to send to the console
     */
    public static void log(final @NotNull CortexLogLevel level, final @NotNull Throwable throwable) {
        log(level, ThrowableUtils.getStackTrace(throwable));
    }

    /**
     * Send a stacktrace to the console
     * <br>Will send at the {@link CortexLogLevel#ERROR} level
     * @param throwable The {@link Throwable} to send to the console
     * @see CortexLog#log(CortexLogLevel, Throwable)
     */
    public static void log(final @NotNull Throwable throwable) {
        log(CortexLogLevel.ERROR, throwable);
    }



    /**
     * Send a debug message to the console.
     * <br>Will only send if debug mode is enabled in the config.yml
     * @param level {@link CortexLogLevel} the level to send the message as
     * @param input The {@link String} {@link List} message to send to the console
     */
    public static void debug(final @NotNull CortexLogLevel level, final @NotNull List<String> input) {
        send(level, true, input);
    }

    /**
     * Send a debug message to the console
     * <br>Will only send if debug mode is enabled in the config.yml
     * @param level {@link CortexLogLevel} the level to send the message as
     * @param input The {@link String}(s) to send to the console
     */
    public static void debug(final @NotNull CortexLogLevel level, final @NotNull String... input) {
        debug(level, List.of(input));
    }

    /**
     * Send a debug message to the console
     * <br>Will only send if debug mode is enabled in the config.yml
     * <br>Will send at the {@link CortexLogLevel#INFO} level
     * @param input The {@link String} {@link List} message to send to the console
     * @see CortexLog#debug(CortexLogLevel, List)
     */
    public static void debug(final @NotNull List<String> input) {
        debug(CortexLogLevel.INFO, input);
    }

    /**
     * Send a debug message to the console
     * <br>Will only send if debug mode is enabled in the config.yml
     * <br>Will send at the {@link CortexLogLevel#INFO} level
     * @param input The {@link String}(s) to send to the console
     * @see CortexLog#debug(CortexLogLevel, List)
     */
    public static void debug(final @NotNull String... input) {
        debug(CortexLogLevel.INFO, List.of(input));
    }

    /**
     * Send a stacktrace to the console
     * <br>Will only send if debug mode is enabled in the config.yml
     * @param level The {@link CortexLogLevel} level to send the stacktrace as
     * @param throwable The {@link Throwable} to send to the console
     */
    public static void debug(final @NotNull CortexLogLevel level, final @NotNull Throwable throwable) {
        debug(level, ThrowableUtils.getStackTrace(throwable));
    }

    /**
     * Send a stacktrace to the console
     * <br>Will only send if debug mode is enabled in the config.yml
     * <br>Will send at the {@link CortexLogLevel#ERROR} level
     * @param throwable The {@link Throwable} to send to the console
     * @see CortexLog#debug(CortexLogLevel, Throwable)
     */
    public static void debug(final @NotNull Throwable throwable) {
        debug(CortexLogLevel.ERROR, throwable);
    }



    /**
     * Send a divider to the console
     * @param level The {@link CortexLogLevel} to send the divider as
     * @param debug Whether it should be sent as a debug message
     */
    public static void divider(final @NotNull CortexLogLevel level, final boolean debug) {
        send(level, debug, DIVIDER);
    }

    /**
     * Send a divider to the console
     * <br>Will not be sent as a debug message
     * @param level The {@link CortexLogLevel} to send the divider as
     * @see CortexLog#divider(CortexLogLevel, boolean)
     */
    public static void divider(final @NotNull CortexLogLevel level) {
        divider(level, false);
    }

    /**
     * Send a divider to the console
     * <br>Will be sent at the {@link CortexLogLevel#INFO} level
     * @param debug Whether it should be sent as a debug message
     * @see CortexLog#divider(CortexLogLevel, boolean)
     */
    public static void divider(final boolean debug) {
        divider(CortexLogLevel.INFO, debug);
    }

    /**
     * Send a divider to the console
     * <br>Will be sent at the {@link CortexLogLevel#INFO} level
     * <br>Will not be sent as a debug message
     * @see CortexLog#divider(CortexLogLevel, boolean)
     */
    public static void divider() {
        divider(CortexLogLevel.INFO, false);
    }



    /**
     * Send a blank line to the console
     * @param debug Whether it should be sent as a debug message
     */
    public static void blank(final boolean debug) {
        send(CortexLogLevel.INFO, debug, "");
    }

    /**
     * Send a blank line to the console
     * <br>Will not be sent as a debug message
     * @see CortexLog#blank(boolean)
     */
    public static void blank() {
        blank(false);
    }

}
