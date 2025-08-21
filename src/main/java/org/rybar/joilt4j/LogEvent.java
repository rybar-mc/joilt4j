package org.rybar.joilt4j;

import java.time.Instant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record LogEvent(
        @NotNull Instant timestamp,
        @NotNull LogLevel level,
        @Nullable String message,
        @NotNull String loggerName,
        @NotNull String threadName,
        long threadId,
        @Nullable String className,
        @Nullable String methodName,
        int lineNumber,
        @Nullable Throwable throwable) {
    public static LogEvent create(
            Instant timestamp, LogLevel level, String message, String loggerName, Throwable throwable) {
        String threadName = Thread.currentThread().getName();
        long threadId = Thread.currentThread().getId();

        StackTraceElement caller = findCaller();

        return new LogEvent(
                timestamp,
                level,
                message,
                loggerName,
                threadName,
                threadId,
                caller.getClassName(),
                caller.getMethodName(),
                caller.getLineNumber(),
                throwable);
    }

    public static LogEvent create(Instant timestamp, LogLevel level, String message, String loggerName) {
        return create(timestamp, level, message, loggerName, null);
    }

    private static StackTraceElement findCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = 1; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (!className.contains("Logger")
                    && !className.equals(LogEvent.class.getName())
                    && !className.equals(Thread.class.getName())) {
                return stackTrace[i];
            }
        }

        return stackTrace[1];
    }
}
