package org.rybar.joilt4j.sink;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;
import org.rybar.joilt4j.format.LogFormatter;

import java.io.PrintStream;

@RequiredArgsConstructor
public final class ConsoleSink implements LogSink {
    private final @NotNull LogFormatter formatter;
    private final @NotNull PrintStream out;

    public ConsoleSink(final @NotNull LogFormatter formatter) {
        this(formatter, System.out);
    }

    @Override
    public void write(final @NotNull LogEvent event) {
        out.println(formatter.format(event));
    }
}

