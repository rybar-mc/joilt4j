package org.rybar.joilt4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.rybar.joilt4j.format.LogFormatter;
import org.rybar.joilt4j.sink.LogSink;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public final class LoggerTest {
    @Test
    @DisplayName("Standard logger should be initialized with default values")
    void standardLoggerDefaults() {
        Logger logger = Logger.standard();

        assertThat(logger.name()).isEqualTo("standard");
        assertThat(logger.level()).isEqualTo(LogLevel.INFO);
    }

    @Test
    @DisplayName("Should log simple message")
    void shouldLogSimpleMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder().sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream))).build();

        logger.info("Test message");

        String output = outputStream.toString();
        assertThat(output)
                .contains("INFO")
                .contains("standard")
                .contains("Test message");
    }

    @Test
    @DisplayName("Should format message with parameters")
    void shouldFormatMessageWithParameters() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder().sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream))).build();

        logger.info("Hello {}, how are {}?", "world", "you");

        String output = outputStream.toString();
        assertThat(output).contains("Hello world, how are you?");
    }

    @ParameterizedTest
    @MethodSource("provideLevelsAndMessages")
    @DisplayName("Should respect log levels")
    void shouldRespectLogLevels(LogLevel level, String message, boolean shouldBeLogged) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder().sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream))).build();

        switch (level) {
            case TRACE -> logger.trace(message);
            case DEBUG -> logger.debug(message);
            case INFO -> logger.info(message);
            case WARN -> logger.warn(message);
            case ERROR -> logger.error(message);
        }

        String output = outputStream.toString();
        if (shouldBeLogged) {
            assertThat(output).contains(message);
        } else {
            assertThat(output).isEmpty();
        }
    }

    private static Stream<Arguments> provideLevelsAndMessages() {
        return Stream.of(
                Arguments.of(LogLevel.TRACE, "trace message", false),
                Arguments.of(LogLevel.DEBUG, "debug message", false),
                Arguments.of(LogLevel.INFO, "info message", true),
                Arguments.of(LogLevel.WARN, "warn message", true),
                Arguments.of(LogLevel.ERROR, "error message", true)
        );
    }

    @Test
    @DisplayName("Should handle null parameter values")
    void shouldHandleNullParameters() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder().sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream))).build();

        logger.info("Test {} message", (Object) null);

        String output = outputStream.toString();
        assertThat(output).contains("Test null message");
    }

    @Test
    @DisplayName("Should log exception with stack trace")
    void shouldLogExceptionWithStackTrace() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder()
                .sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream)))
                .build();
        Exception exception = new RuntimeException("Test exception");

        logger.error("Error occurred", exception);

        String output = outputStream.toString();
        assertThat(output)
                .contains("ERROR")
                .contains("Error occurred")
                .contains("RuntimeException: Test exception")
                .contains("at org.rybar.joilt4j.LoggerTest");
    }

    @Test
    @DisplayName("Should log exception with formatted message")
    void shouldLogExceptionWithFormattedMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder()
                .sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream)))
                .build();
        Exception exception = new RuntimeException("Test exception");

        logger.error("Failed to process {}", exception, "item-123");

        String output = outputStream.toString();
        assertThat(output)
                .contains("ERROR")
                .contains("Failed to process item-123")
                .contains("RuntimeException: Test exception");
    }

    @Test
    @DisplayName("Should log objects without message")
    void shouldLogArgsWithoutMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger logger = Logger.builder()
                .sink(LogSink.console(LogFormatter.simple(), new PrintStream(outputStream)))
                .build();

        Color color = new Color(255, 0, 0);
        Color[] colors = {color, color, color};

        logger.info(color, Arrays.toString(colors));

        String output = outputStream.toString();
        assertThat(output).contains("java.awt.Color[r=255,g=0,b=0] [java.awt.Color[r=255,g=0,b=0], java.awt.Color[r=255,g=0,b=0], java.awt.Color[r=255,g=0,b=0]]");
    }
}
