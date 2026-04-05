package io.github.eat_ram.cntrafficsymbols.core.helper;

import org.jetbrains.annotations.Range;

public abstract class RangeUtil {
    private RangeUtil() {
        throw new UnsupportedOperationException();
    }

    public static @Range(from = -1, to = 1) byte
    closedRangeTo(double start, double end, double v) {
        return (byte)((v >= start) ? ((v <= end) ? 0 : 1) : -1);
    }
}
