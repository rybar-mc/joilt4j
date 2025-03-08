package org.rybar.joilt4j;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
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

    @Builder.Default private final @NotNull String name = "standard";
    @Builder.Default private final @NotNull LogLevel level = LogLevel.INFO;
    @Builder.Default private final @NotNull LogSink sink = LogSink.console(LogFormatter.simple());

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
    public void trace(final String message, Object... args) {
        log(LogLevel.TRACE, message, args);
    }

    @Override
    public void debug(final String message, Object... args) {
        log(LogLevel.DEBUG, message, args);
    }

    @Override
    public void info(final String message, Object... args) {
        log(LogLevel.INFO, message, args);
    }

    @Override
    public void warn(final String message, Object... args) {
        log(LogLevel.WARN, message, args);
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

    private void log(final LogLevel level, final String message, Object... args) {
        if (levelEnabled(level)) {
            sink.write(LogEvent.create(
                    Instant.now(),
                    level,
                    formatMessage(message, args),
                    name
            ));
        }
    }

    private void logWithThrowable(LogLevel level, String message, Throwable throwable, Object... args) {
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

    private String formatMessage(String format, Object... args) {
        if (args.length == 0 || !format.contains("{}")) {
            return format;
        }

        final StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        int lastPosition = 0;

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

    public static class LoggerImplBuilder implements LoggerBuilder {}
}
