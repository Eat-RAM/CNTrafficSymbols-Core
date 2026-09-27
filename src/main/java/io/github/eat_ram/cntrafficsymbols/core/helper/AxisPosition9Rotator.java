package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.AxisPosition9;
import org.jetbrains.annotations.Contract;

public abstract class AxisPosition9Rotator {
    @Contract("-> fail")
    private AxisPosition9Rotator() {
        throw new UnsupportedOperationException();
    }

    public static AxisPosition9 rotate(AxisPosition9 v, int t) {
        switch (v.id / 9) {
            case 0: {
                switch (t & 3) {
                    case 1: return AxisPosition9.byID(
                        v.id < 2 ? v.id + 18 : 28 - v.id
                    );
                    case 2: return v.id < 2 ? v : AxisPosition9.byID(
                        10 - v.id
                    );
                    case 3: return AxisPosition9.byID(v.id + 18);
                    default: return v;
                }
            }
            case 2: {
                switch (t & 3) {
                    case 1: return AxisPosition9.byID(v.id - 18);
                    case 2: return v.id < 20 ? v : AxisPosition9.byID(
                        46 - v.id
                    );
                    case 3: return AxisPosition9.byID(
                        v.id < 20 ? v.id - 18 : 28 - v.id
                    );
                    default: return v;
                }
            }
            default: return v.id == 9 ? v : AxisPosition9.byID(
                ((v.id - 10 + (t * 2)) & 7) + 10
            );
        }
    }
}
