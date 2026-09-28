package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.FacePosition9;
import org.jetbrains.annotations.Contract;

public abstract class FacePosition9Mirrorer {
    @Contract("-> fail")
    private FacePosition9Mirrorer() {
        throw new UnsupportedOperationException();
    }

    public static FacePosition9 mirrorX(FacePosition9 v) {
        switch (v.id / 9) {
            case 0: return FacePosition9.byID(v.id + 9);
            case 1: return FacePosition9.byID(v.id - 9);
            case 2: return v.id < 20 ? v : FacePosition9.byID(46 - v.id);
            case 3: return v.id < 29 ? v : FacePosition9.byID(64 - v.id);
            case 4: return v.id < 38 ? v : FacePosition9.byID(82 - v.id);
            default: return v.id < 47 ? v : FacePosition9.byID(100 - v.id);
        }
    }

    public static FacePosition9 mirrorZ(FacePosition9 v) {
        switch (v.id / 9) {
            case 0: return v.id < 2 ? v : FacePosition9.byID(10 - v.id); // WEST
            case 1: return v.id < 11 ? v : FacePosition9.byID(28 - v.id); // EAST
            case 2:
                switch (v.id) {
                    case 19: return FacePosition9.DOWN_SOUTH;
                    case 20: return FacePosition9.DOWN_SOUTHEAST;
                    case 22: return FacePosition9.DOWN_NORTHEAST;
                    case 23: return FacePosition9.DOWN_NORTH;
                    case 24: return FacePosition9.DOWN_NORTHWEST;
                    case 26: return FacePosition9.DOWN_SOUTHWEST;
                }
                return v;
            case 3:
                switch (v.id) {
                    case 28: return FacePosition9.UP_SOUTH;
                    case 29: return FacePosition9.UP_SOUTHEAST;
                    case 31: return FacePosition9.UP_NORTHEAST;
                    case 32: return FacePosition9.UP_NORTH;
                    case 33: return FacePosition9.UP_NORTHWEST;
                    case 35: return FacePosition9.UP_SOUTHWEST;
                }
                return v;
            case 4: return FacePosition9.byID(v.id + 9);
            default: return FacePosition9.byID(v.id - 9);
        }
    }
}
