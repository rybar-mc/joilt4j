package org.rybar.joilt4j.sink;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;
import org.rybar.joilt4j.format.LogFormatter;

import java.io.PrintStream;

public interface LogSink {
    void write(final @NotNull LogEvent event);

    static LogSink console(final @NotNull LogFormatter formatter) {
        return new ConsoleSink(formatter);
    }

    static LogSink console(final @NotNull LogFormatter formatter, final @NotNull PrintStream out) {
        return new ConsoleSink(formatter, out);
    }
}
