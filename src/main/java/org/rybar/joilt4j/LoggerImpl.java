package org.rybar.joilt4j;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rybar.joilt4j.sink.LogSink;
import org.rybar.joilt4j.format.LogFormatter;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Builder
class LoggerImpl implements Logger {
    private static final @NotNull Logger STANDARD = LoggerImpl.builder()
            .build();

    @lombok.Builder.Default private final @NotNull String name = "standard";
    @lombok.Builder.Default private final @NotNull LogLevel level = LogLevel.INFO;
    @lombok.Builder.Default private final @NotNull LogSink sink = LogSink.console(LogFormatter.simple());

    private LoggerImpl(
            final @NotNull String name,
            final @NotNull LogLevel level,
            final @NotNull LogSink sink
    ) {
        this.name = name;
        this.level = level;
        this.sink = sink;
    }

    static Logger standard() {
        return STANDARD;
    }

    @Override
    public void trace(Object... args) {
        log(LogLevel.TRACE, null, args);
    }

    @Override
    public void trace(final String message, Object... args) {
        log(LogLevel.TRACE, message, args);
    }

    @Override
    public void debug(Object... args) {
        log(LogLevel.DEBUG, null, args);
    }

    @Override
    public void debug(final String message, Object... args) {
        log(LogLevel.DEBUG, message, args);
    }

    @Override
    public void info(Object... args) {
        log(LogLevel.INFO, null, args);
    }

    @Override
    public void info(final String message, Object... args) {
        log(LogLevel.INFO, message, args);
    }

    @Override
    public void warn(Object... args) {
        log(LogLevel.WARN, null, args);
    }

    @Override
    public void warn(final String message, Object... args) {
        log(LogLevel.WARN, message, args);
    }

    @Override
    public void error(Object... args) {
        log(LogLevel.ERROR, null, args);
    }

    @Override
    public void error(Throwable throwable) {
        logWithThrowable(LogLevel.ERROR, null, throwable);
    }

    @Override
    public void error(final String message, Object... args) {
        log(LogLevel.ERROR, message, args);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logWithThrowable(LogLevel.ERROR, message, throwable);
    }

    @Override
    public void error(String message, Throwable throwable, Object... args) {
        logWithThrowable(LogLevel.ERROR, message, throwable, args);
    }

    private void log(final LogLevel level, final @Nullable String message, Object... args) {
        if (levelEnabled(level)) {
            sink.write(LogEvent.create(
                    Instant.now(),
                    level,
                    formatMessage(message, args),
                    name
            ));
        }
    }

    private void logWithThrowable(final LogLevel level, final @Nullable String message,
                                  final @NotNull Throwable throwable, Object... args) {
        if (this.level.ordinal() <= level.ordinal()) {
            sink.write(LogEvent.create(
                    Instant.now(),
                    level,
                    formatMessage(message, args),
                    name,
                    throwable
            ));
        }
    }

    private String formatMessage(final @Nullable String format, final Object... args) {
        if (args.length == 0 || (format != null && !format.contains("{}"))) {
            return format == null ? "" : format;
        }

        final StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        int lastPosition = 0;

        if (format == null) {
            for (Object arg : args) {
                sb.append(arg);
                sb.append(" ");
            }

            return sb.toString().trim();
        }

        Matcher matcher = Pattern.compile("\\{\\}").matcher(format);
        while (matcher.find() && argIndex < args.length) {
            sb.append(format, lastPosition, matcher.start())
                    .append(args[argIndex++]);
            lastPosition = matcher.end();
        }
        sb.append(format.substring(lastPosition));

        return sb.toString();
    }

    private boolean levelEnabled(LogLevel level) {
        return this.level.ordinal() <= level.ordinal();
    }

    public static class LoggerImplBuilder implements Builder {}
}
