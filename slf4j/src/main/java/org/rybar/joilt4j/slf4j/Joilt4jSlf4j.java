package org.rybar.joilt4j.slf4j;

import lombok.Getter;
import lombok.Setter;
import org.rybar.joilt4j.Logger;

import java.util.function.Function;

public final class Joilt4jSlf4j {
    @Getter
    @Setter
    public static Function<String, Logger> loggerFactory = name ->
            Logger.builder().name(name).build();
}
