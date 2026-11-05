package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing;
import org.jetbrains.annotations.Contract;

public abstract class DoubleFaceFacingRotator {
    public static DoubleFaceFacing rotate(DoubleFaceFacing v, int t) {
        return DoubleFaceFacing.byID(v.isWall() ? (v.id + t) % 4 + 24 : (
            v.id < 16 ? (t * 4 + v.id) % 16 : ((t * 4 + v.id) % 8 + 16)
        ));
    }

    @Contract(value = "-> fail", pure = true)
    private DoubleFaceFacingRotator() {
        throw new UnsupportedOperationException("Utility class");
    }
}
