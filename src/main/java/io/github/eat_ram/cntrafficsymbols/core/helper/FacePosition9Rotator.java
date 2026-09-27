package io.github.eat_ram.cntrafficsymbols.core.helper;

import io.github.eat_ram.cntrafficsymbols.core.struct.FacePosition9;
import org.jetbrains.annotations.Contract;

public abstract class FacePosition9Rotator {
    @Contract("-> fail")
    private FacePosition9Rotator() {
        throw new UnsupportedOperationException();
    }

    public static FacePosition9 rotate(FacePosition9 v, int t) {
        switch (v.id / 9) {
            case 0: {
                switch (t & 3) {
                    case 1: return FacePosition9.byID(
                        v.id < 2 ? v.id + 36 : 46 - v.id
                    );
                    case 2: return FacePosition9.byID(
                        v.id < 2 ? v.id + 9 : 19 - v.id
                    );
                    case 3: return FacePosition9.byID(v.id + 45);
                    default: return v;
                }
            }
            case 1: {
                switch (t & 3) {
                    case 1: return FacePosition9.byID(
                        v.id < 11 ? v.id + 36 : 64 - v.id
                    );
                    case 2: return FacePosition9.byID(
                        v.id < 11 ? v.id - 9 : 19 - v.id
                    );
                    case 3: return FacePosition9.byID(v.id + 27);
                    default: return v;
                }
            }
            case 2: return v.id == 18 ? v : FacePosition9.byID(
                ((v.id - 19 + (t * 2)) & 7) + 19
            );
            case 3: return v.id == 27 ? v : FacePosition9.byID(
                ((v.id - 28 + (t * 2)) & 7) + 28
            );
            case 4: {
                switch (t & 3) {
                    case 1: return FacePosition9.byID(v.id - 27);
                    case 2: return FacePosition9.byID(
                        v.id < 38 ? v.id + 9 : 91 - v.id
                    );
                    case 3: return FacePosition9.byID(
                        v.id < 38 ? v.id - 36 : 46 - v.id
                    );
                    default: return v;
                }
            }
            default: {
                switch (t & 3) {
                    case 1: return FacePosition9.byID(v.id - 45);
                    case 2: return FacePosition9.byID(
                        v.id < 47 ? v.id - 9 : 91 - v.id
                    );
                    case 3: return FacePosition9.byID(
                        v.id < 47 ? v.id - 36 : 64 - v.id
                    );
                    default: return v;
                }
            }
        }
    }
}
