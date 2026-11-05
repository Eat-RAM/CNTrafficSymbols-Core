package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing90;
import org.jetbrains.annotations.Contract;

public abstract class DoubleFaceFacing90Rotator {
    public static DoubleFaceFacing90 rotate(DoubleFaceFacing90 v, int t) {
        return DoubleFaceFacing90.byID(v.isWall() ? (v.id + t) % 4 + 6 : (
            v.id < 4 ? (t + v.id) % 4 : ((t + v.id) % 2 + 4)
        ));
    }

    @Contract(value = "-> fail", pure = true)
    private DoubleFaceFacing90Rotator() {
        throw new UnsupportedOperationException("Utility class");
    }
}
