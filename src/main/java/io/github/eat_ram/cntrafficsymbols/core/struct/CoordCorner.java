package io.github.eat_ram.cntrafficsymbols.core.struct;

import net.minecraft.state.property.EnumProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum CoordCorner implements net.minecraft.util.StringIdentifiable {
    SOUTHEAST("southeast", 0),
    SOUTHWEST("southwest", 1),
    NORTHWEST("northwest", 2),
    NORTHEAST("northeast", 3);

    public final @NotNull String str;
    public final @Range(from = 0, to = 3) int id;

    CoordCorner(@NotNull String n, @Range(from = 0, to = 3) int i) {
        this.str = n;
        this.id = i;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String asString() {
        return this.str;
    }

    private static final CoordCorner[] VARS =
    new CoordCorner[]{SOUTHEAST, SOUTHWEST, NORTHWEST, NORTHEAST};

    @Contract(pure = true)
    public static CoordCorner byID(int id) {
        return VARS[id];
    }

    public static final EnumProperty<CoordCorner> CORNER =
    EnumProperty.of("corner", CoordCorner.class);
}
