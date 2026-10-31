package io.github.eat_ram.cntrafficsymbols.core.struct;

import net.minecraft.state.property.EnumProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum Attachment3 implements net.minecraft.util.StringIdentifiable {
    NONE("none", 0), DOWN("down", 1), UP("up", 2);

    public final @NotNull String str;
    public final @Range(from = 0, to = 2) int id;

    Attachment3(@NotNull String n, @Range(from = 0, to = 2) int i) {
        this.str = n;
        this.id = i;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String asString() {
        return this.str;
    }

    private static final Attachment3[] VARS = {NONE, DOWN, UP};

    @Contract(pure = true)
    public static Attachment3 byID(int id) {
        return VARS[id];
    }

    public static final EnumProperty<Attachment3> ATTACHMENT =
    EnumProperty.of("attachment", Attachment3.class);
}
