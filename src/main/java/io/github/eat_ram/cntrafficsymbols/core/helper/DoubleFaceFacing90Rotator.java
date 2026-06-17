package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing90;
import org.jetbrains.annotations.Contract;

public abstract class DoubleFaceFacing90Rotator {
    @Contract("-> fail")
    private DoubleFaceFacing90Rotator() {
        throw new UnsupportedOperationException();
    }

    public static DoubleFaceFacing90 rotate(DoubleFaceFacing90 v, int t) {
        if (v.isWall()) {
            return DoubleFaceFacing90.byID((v.id + t) % 4 + 6);
        }
        return DoubleFaceFacing90.byID(
            (v.id < 4) ? (t + v.id) % 4 : ((t + v.id) % 2 + 4)
        );
    }
}
