package dev.cortex.cortexcore.util.logging;

import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ThrowableUtils {

    public static @NotNull String getStackTrace(final @NotNull Throwable throwable) {
        final Writer writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);

        throwable.printStackTrace(printWriter);
        printWriter.flush();

        return writer.toString();
    }

}
