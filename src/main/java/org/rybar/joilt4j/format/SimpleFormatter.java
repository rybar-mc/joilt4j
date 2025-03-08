package org.rybar.joilt4j.format;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;

final class SimpleFormatter implements LogFormatter {
    private final FormatConfig config;

    SimpleFormatter(FormatConfig config) {
        this.config = config;
    }

    SimpleFormatter() {
        this(FormatConfig.standard());
    }

    public @NotNull String format(@NotNull LogEvent event) {
        StringBuilder sb = new StringBuilder();

        if (config.showTimestamp()) {
            sb.append('[').append(event.timestamp()).append("] ");
        }

        if (config.showLevel()) {
            sb.append(event.level().name()).append(' ');
        }

        if (config.showThread() || config.showThreadId()) {
            sb.append('[');
            if (config.showThread()) {
                sb.append(event.threadName());
                if (config.showThreadId()) {
                    sb.append(':').append(event.threadId());
                }
            } else if (config.showThreadId()) {
                sb.append(event.threadId());
            }
            sb.append("] ");
        }

        if (config.showClassName() || config.showMethodName() || config.showLineNumber()) {
            sb.append('[');
            if (config.showClassName()) {
                sb.append(event.className());
                if (config.showMethodName()) {
                    sb.append('.');
                }
            }
            if (config.showMethodName()) {
                sb.append(event.methodName());
            }
            if (config.showLineNumber()) {
                sb.append(':').append(event.lineNumber());
            }
            sb.append("] ");
        }

        if (config.showLoggerName()) {
            sb.append(event.loggerName()).append(": ");
        }

        sb.append(event.message());

        if (event.throwable() != null) {
            sb.append('\n');
            sb.append(formatThrowable(event.throwable()));
        }

        return sb.toString();
    }

    private String formatThrowable(Throwable throwable) {
        StringBuilder sb = new StringBuilder();

        Throwable current = throwable;
        while (current != null) {
            if (current != throwable) {
                sb.append("Caused by: ");
            }
            sb.append(current.getClass().getName());
            if (current.getMessage() != null) {
                sb.append(": ").append(current.getMessage());
            }
            sb.append('\n');

            for (StackTraceElement element : current.getStackTrace()) {
                sb.append("\tat ").append(element).append('\n');
            }

            current = current.getCause();
        }

        return sb.toString();
    }
}
