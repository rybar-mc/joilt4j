package org.rybar.joilt4j.format;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogEvent;
import org.rybar.joilt4j.LogLevel;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

final class PrettyFormatter implements LogFormatter {
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String DIM = "\u001B[2m";

    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String GRAY = "\u001B[90m";

    @Getter
    @Setter
    private static PrettyFormatter instance;

    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                    .withZone(ZoneId.systemDefault());

    private final FormatConfig config;

    PrettyFormatter(FormatConfig config) {
        this.config = config;

        instance(this);
    }

    PrettyFormatter() {
        this(FormatConfig.standard());
    }

    public @NotNull String format(@NotNull LogEvent event) {
        StringBuilder sb = new StringBuilder();

        if (config.showTimestamp()) {
            sb.append(DIM).append(GRAY)
                    .append(TIMESTAMP_FORMATTER.format(event.timestamp()))
                    .append(RESET).append(' ');
        }

        if (config.showLevel()) {
            sb.append(colorizeLevel(event.level()))
                    .append(BOLD)
                    .append(padLevel(event.level()))
                    .append(RESET).append(' ');
        }

        if (config.showThread() || config.showThreadId()) {
            sb.append(CYAN);
            if (config.showThread()) {
                sb.append(event.threadName());
                if (config.showThreadId()) {
                    sb.append(':').append(event.threadId());
                }
            } else if (config.showThreadId()) {
                sb.append(event.threadId());
            }
            sb.append(RESET).append(' ');
        }

        if (config.showClassName() || config.showMethodName() || config.showLineNumber()) {
            sb.append(MAGENTA);
            if (config.showClassName()) {
                String className = event.className();
                sb.append(className.substring(className.lastIndexOf('.') + 1));
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
            sb.append(RESET).append(' ');
        }

        if (config.showLoggerName()) {
            sb.append(BLUE)
                    .append(event.loggerName())
                    .append(RESET)
                    .append(": ");
        }

        sb.append(event.message());

        if (event.throwable() != null) {
            sb.append('\n');
            sb.append(formatThrowable(event.throwable()));
        }

        return sb.toString();
    }

    private String padLevel(LogLevel level) {
        return String.format("%-5s", level.name());
    }

    private String colorizeLevel(LogLevel level) {
        return switch (level) {
            case TRACE -> GRAY;
            case DEBUG -> GREEN;
            case INFO -> BLUE;
            case WARN -> YELLOW;
            case ERROR -> RED;
        };
    }

    private String formatThrowable(Throwable throwable) {
        StringBuilder sb = new StringBuilder();

        Throwable current = throwable;
        while (current != null) {
            if (current != throwable) {
                sb.append(RED).append("Caused by: ").append(RESET);
            }
            sb.append(RED).append(BOLD)
                    .append(current.getClass().getName());
            if (current.getMessage() != null) {
                sb.append(": ").append(current.getMessage());
            }
            sb.append(RESET).append('\n');

            for (StackTraceElement element : current.getStackTrace()) {
                sb.append(DIM).append(GRAY)
                        .append("\tat ")
                        .append(element)
                        .append(RESET).append('\n');
            }

            current = current.getCause();
        }

        return sb.toString();
    }
}
