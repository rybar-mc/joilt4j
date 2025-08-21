package org.rybar.joilt4j.sink;

import java.io.PrintStream;
import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;
import org.rybar.joilt4j.format.LogFormatter;

public interface LogSink {
    void write(final @NotNull LogEvent event);

    static LogSink console(final @NotNull LogFormatter formatter) {
        return new ConsoleSink(formatter);
    }

    static LogSink console(final @NotNull LogFormatter formatter, final @NotNull PrintStream out) {
        return new ConsoleSink(formatter, out);
    }
}
