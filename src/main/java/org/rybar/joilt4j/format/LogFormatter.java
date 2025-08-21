package org.rybar.joilt4j.format;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;

@FunctionalInterface
public interface LogFormatter {
    @NotNull String format(final @NotNull LogEvent event);

    static LogFormatter simple() {
        return SimpleFormatter.instance() == null ? new SimpleFormatter() : SimpleFormatter.instance();
    }

    static LogFormatter simple(final @NotNull FormatConfig config) {
        return SimpleFormatter.instance() == null ? new SimpleFormatter(config) : SimpleFormatter.instance();
    }

    static LogFormatter pretty() {
        return PrettyFormatter.instance() == null ? new PrettyFormatter() : PrettyFormatter.instance();
    }

    static LogFormatter pretty(final @NotNull FormatConfig config) {
        return PrettyFormatter.instance() == null ? new PrettyFormatter(config) : PrettyFormatter.instance();
    }
}
