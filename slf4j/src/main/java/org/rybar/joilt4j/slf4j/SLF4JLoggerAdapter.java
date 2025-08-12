package org.rybar.joilt4j.slf4j;

import org.jetbrains.annotations.NotNull;
import org.rybar.joilt4j.LogLevel;
import org.rybar.joilt4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

public class SLF4JLoggerAdapter implements org.slf4j.Logger {
    private final Logger delegate;

    SLF4JLoggerAdapter(@NotNull Logger delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.name();
    }

    @Override
    public boolean isTraceEnabled() {
        return delegate.level().ordinal() <= LogLevel.TRACE.ordinal();
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        delegate.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg);
            delegate.trace(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
            delegate.trace(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()) {
            FormattingTuple tuple = MessageFormatter.arrayFormat(format, arguments);
            delegate.trace(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (isTraceEnabled()) {
            delegate.trace(msg + " - " + t.getMessage());
        }
    }

    @Override
    public void trace(Marker marker, String msg) {
        trace(msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        trace(format, arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        trace(format, arg1, arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... arguments) {
        trace(format, arguments);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        trace(msg, t);
    }

    // Debug level methods
    @Override
    public boolean isDebugEnabled() {
        return delegate.level().ordinal() <= LogLevel.DEBUG.ordinal();
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        delegate.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        if (isDebugEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg);
            delegate.debug(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (isDebugEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
            delegate.debug(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (isDebugEnabled()) {
            FormattingTuple tuple = MessageFormatter.arrayFormat(format, arguments);
            delegate.debug(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (isDebugEnabled()) {
            delegate.debug(msg + " - " + t.getMessage());
        }
    }

    @Override
    public void debug(Marker marker, String msg) {
        debug(msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        debug(format, arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        debug(format, arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        debug(format, arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        debug(msg, t);
    }

    // Info level methods
    @Override
    public boolean isInfoEnabled() {
        return delegate.level().ordinal() <= LogLevel.INFO.ordinal();
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        delegate.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        if (isInfoEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg);
            delegate.info(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
            delegate.info(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnabled()) {
            FormattingTuple tuple = MessageFormatter.arrayFormat(format, arguments);
            delegate.info(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            delegate.info(msg + " - " + t.getMessage());
        }
    }

    @Override
    public void info(Marker marker, String msg) {
        info(msg);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        info(format, arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        info(format, arg1, arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        info(format, arguments);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        info(msg, t);
    }

    // Warn level methods
    @Override
    public boolean isWarnEnabled() {
        return delegate.level().ordinal() <= LogLevel.WARN.ordinal();
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        delegate.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        if (isWarnEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg);
            delegate.warn(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
            delegate.warn(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnabled()) {
            FormattingTuple tuple = MessageFormatter.arrayFormat(format, arguments);
            delegate.warn(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (isWarnEnabled()) {
            delegate.warn(msg, t);
        }
    }

    @Override
    public void warn(Marker marker, String msg) {
        warn(msg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        warn(format, arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        warn(format, arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        warn(format, arguments);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        warn(msg, t);
    }

    // Error level methods
    @Override
    public boolean isErrorEnabled() {
        return delegate.level().ordinal() <= LogLevel.ERROR.ordinal();
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        delegate.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        if (isErrorEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg);
            delegate.error(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (isErrorEnabled()) {
            FormattingTuple tuple = MessageFormatter.format(format, arg1, arg2);
            delegate.error(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnabled()) {
            FormattingTuple tuple = MessageFormatter.arrayFormat(format, arguments);
            delegate.error(tuple.getMessage(), tuple.getThrowable());
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            delegate.error(msg, t);
        }
    }

    @Override
    public void error(Marker marker, String msg) {
        error(msg);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        error(format, arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        error(format, arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        error(format, arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        error(msg, t);
    }
}
