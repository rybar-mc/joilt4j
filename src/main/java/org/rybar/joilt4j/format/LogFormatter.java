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

    static LogFormatter pretty() {
        return new PrettyFormatter();
    }

    static LogFormatter pretty(final @NotNull FormatConfig config) {
        return new PrettyFormatter(config);
    }
}
