package org.rybar.joilt4j;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.sink.LogSink;

public interface Logger {
    void trace(Object... args);

    void trace(String message, Object... args);

    void debug(Object... args);

    void debug(String message, Object... args);

    void info(Object... args);

    void info(String message, Object... args);

    void warn(Object... args);

    void warn(String message, Object... args);

    void error(Object... args);

    void error(Throwable throwable);

    void error(String message, Object... args);

    void error(String message, Throwable throwable);

    void error(String message, Throwable throwable, Object... args);

    @NotNull String name();

    @NotNull LogLevel level();

    static Logger standard() {
        return LoggerImpl.standard();
    }

    static Builder builder() {
        return LoggerImpl.builder();
    }

    interface Builder {
        @NotNull Builder name(final @NotNull String name);

        @NotNull Builder level(final @NotNull LogLevel level);

        @NotNull Builder sink(final @NotNull LogSink sink);

        @NotNull Logger build();
    }
}
