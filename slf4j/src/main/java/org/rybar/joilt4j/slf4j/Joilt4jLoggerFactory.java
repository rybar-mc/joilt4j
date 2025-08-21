package org.rybar.joilt4j.slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jetbrains.annotations.NotNull;
import org.slf4j.ILoggerFactory;

public class Joilt4jLoggerFactory implements ILoggerFactory {
    private final ConcurrentMap<String, org.slf4j.Logger> loggerCache;

    public Joilt4jLoggerFactory() {
        this.loggerCache = new ConcurrentHashMap<>();
    }

    @Override
    public org.slf4j.Logger getLogger(String name) {
        return loggerCache.computeIfAbsent(name, this::createLogger);
    }

    private org.slf4j.Logger createLogger(@NotNull String name) {
        return new SLF4JLoggerAdapter(Joilt4jSlf4j.loggerFactory().apply(name));
    }
}
