package io.github.eat_ram.cntrafficsymbols.core.struct;

import net.minecraft.state.property.EnumProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum QuadrantsBisector
implements net.minecraft.util.StringIdentifiable {
    Q13("q13", 0), Q24("q24", 1);

    public final @NotNull String str;
    public final @Range(from = 0, to = 1) int id;

    QuadrantsBisector(@NotNull String str, @Range(from = 0, to = 1) int id) {
        this.str = str;
        this.id = id;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String asString() {
        return this.str;
    }

    @Contract(pure = true)
    public @NotNull QuadrantsBisector flip() {
        return this == Q24 ? Q13 : Q24;
    }

    private static final QuadrantsBisector[] VARS = {Q13, Q24};

    @Contract(pure = true)
    public static QuadrantsBisector byID(int id) {
        return VARS[id];
    }

    public static final EnumProperty<QuadrantsBisector> ROTATION =
    EnumProperty.of("rotation", QuadrantsBisector.class);
}
