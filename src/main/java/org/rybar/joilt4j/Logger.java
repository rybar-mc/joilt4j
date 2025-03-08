package org.rybar.joilt4j;

import lombok.*;
import org.jetbrains.annotations.NotNull;

public interface Logger {
    void trace(String message, Object... args);
    void debug(String message, Object... args);
    void info(String message, Object... args);
    void warn(String message, Object... args);

    void error(String message, Object... args);
    void error(String message, Throwable throwable);
    void error(String message, Throwable throwable, Object... args);

    @NotNull String name();
    @NotNull LogLevel level();

    static Logger standard() {
        return LoggerImpl.standard();
    }

    static LoggerBuilder builder() {
        return LoggerImpl.builder();
    }
}