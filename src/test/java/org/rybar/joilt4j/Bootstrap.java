package org.rybar.joilt4j;

import org.rybar.joilt4j.format.LogFormatter;
import org.rybar.joilt4j.sink.LogSink;

public class Bootstrap {
    public static void main(String[] args) {
        var logger = Logger.builder()
                .sink(LogSink.console(LogFormatter.pretty()))
                .level(LogLevel.TRACE)
                .build();

        logger.trace("Hello, world!");
        logger.debug("Hello, world!");
        logger.info("Hello, world!");
        logger.warn("Hello, world!");
        logger.error("Goodbye, world!");

        lol();

        try {
            throw new RuntimeException("Goodbye Exception!");
        } catch (RuntimeException e) {
            Logger.standard()
                    .error("Goodbye, world!", e);
        }
    }

    public static void lol() {
        Logger.standard()
                .info("Hello, world!");
    }
}
