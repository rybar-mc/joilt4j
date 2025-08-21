package org.rybar.joilt4j.format;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FormatConfig {
    @Builder.Default
    private boolean showTimestamp = true;

    @Builder.Default
    private boolean showLevel = true;

    @Builder.Default
    private boolean showLoggerName = true;

    @Builder.Default
    private boolean showThread = true;

    @Builder.Default
    private boolean showThreadId = true;

    @Builder.Default
    private boolean showClassName = true;

    @Builder.Default
    private boolean showMethodName = true;

    @Builder.Default
    private boolean showLineNumber = true;

    public static FormatConfig standard() {
        return builder().build();
    }
}
