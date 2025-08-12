package org.rybar.joilt4j.slf4j;

import org.rybar.joilt4j.LogLevel;
import org.rybar.joilt4j.Logger;
import org.rybar.joilt4j.format.LogFormatter;
import org.rybar.joilt4j.sink.LogSink;
import org.slf4j.LoggerFactory;

public class Bootstrap {
    public static void main(String[] args) {
        Joilt4jSlf4j.loggerFactory(name -> Logger.builder()
                .name(name)
                .level(LogLevel.DEBUG)
                .sink(LogSink.console(LogFormatter.pretty()))
                .build());

        org.slf4j.Logger logger = LoggerFactory.getLogger("TestLogger");
        System.out.println(logger);

        logger.info("Hello from SLF4J!");
    }
}
