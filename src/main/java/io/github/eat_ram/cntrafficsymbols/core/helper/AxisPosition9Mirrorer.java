package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.AxisPosition9;
import org.jetbrains.annotations.Contract;

public abstract class AxisPosition9Mirrorer {
    @Contract("-> fail")
    private AxisPosition9Mirrorer() {
        throw new UnsupportedOperationException();
    }

    public static AxisPosition9 mirrorX(AxisPosition9 v) {
        switch (v.id / 9) {
            case 1: return v.id < 11 ? v : AxisPosition9.byID(28 - v.id);
            case 2: return v.id < 20 ? v : AxisPosition9.byID(46 - v.id);
            default: return v;
        }
    }

    public static AxisPosition9 mirrorZ(AxisPosition9 v) {
        switch (v.id / 9) {
            case 0: return v.id < 2 ? v : AxisPosition9.byID(10 - v.id);
            case 1:
                switch (v.id) {
                    case 10: return AxisPosition9.Y_SOUTH;
                    case 11: return AxisPosition9.Y_SOUTHEAST;
                    case 13: return AxisPosition9.Y_NORTHEAST;
                    case 14: return AxisPosition9.Y_NORTH;
                    case 15: return AxisPosition9.Y_NORTHWEST;
                    case 17: return AxisPosition9.Y_SOUTHWEST;
                }
            default: return v;
        }
    }
}
