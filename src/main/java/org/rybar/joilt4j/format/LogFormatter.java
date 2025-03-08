package org.rybar.joilt4j.format;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;

@FunctionalInterface
public interface LogFormatter {
    @NotNull String format(final @NotNull LogEvent event);

    static LogFormatter simple() {
        return new SimpleFormatter();
    }

    static LogFormatter simple(final @NotNull FormatConfig config) {
        return new SimpleFormatter(config);
    }
}
