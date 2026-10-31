package io.github.eat_ram.cntrafficsymbols.core.struct;

import net.minecraft.state.property.EnumProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum QuadrantsQuadrisector
implements net.minecraft.util.StringIdentifiable {
    Q13A("q13a", 0), Q13O("q13o", 1), Q24O("q24o", 2), Q24A("q24a", 3);

    public final @NotNull String str;
    public final @Range(from = 0, to = 3) int id;

    QuadrantsQuadrisector(@NotNull String str,
                          @Range(from = 0, to = 3) int id) {
        this.str = str;
        this.id = id;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String asString() {
        return this.str;
    }

    @Contract(pure = true)
    public @NotNull QuadrantsQuadrisector turn() {
        switch (this.id) {
            case 1: return Q24A;
            case 2: return Q13A;
            case 3: return Q13O;
            default: return Q24O;
        }
    }

    @Contract(pure = true)
    public @NotNull QuadrantsQuadrisector flip() {
        switch (this.id) {
            case 1: return Q24O;
            case 2: return Q13O;
            case 3: return Q13A;
            default: return Q24A;
        }
    }

    private static final QuadrantsQuadrisector[] VARS =
    {Q13A, Q13O, Q24O, Q24A};

    @Contract(pure = true)
    public static QuadrantsQuadrisector byID(int id) {
        return VARS[id];
    }

    public static final EnumProperty<QuadrantsQuadrisector> ROTATION =
    EnumProperty.of("rotation", QuadrantsQuadrisector.class);
}
