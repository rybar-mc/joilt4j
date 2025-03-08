package org.rybar.joilt4j;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.sink.LogSink;

public interface LoggerBuilder {
    @NotNull LoggerBuilder name(final @NotNull String name);
    @NotNull LoggerBuilder level(final @NotNull LogLevel level);
    @NotNull LoggerBuilder sink(final @NotNull LogSink sink);

    @NotNull Logger build();
}
