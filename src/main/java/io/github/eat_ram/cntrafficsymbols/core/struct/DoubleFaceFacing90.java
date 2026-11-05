package io.github.eat_ram.cntrafficsymbols.core.struct;

import net.minecraft.state.property.EnumProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum DoubleFaceFacing90
    implements net.minecraft.util.StringIdentifiable {
    SOUTH("south", 0), WEST("west", 1), NORTH("north", 2), EAST("east", 3),
    NORTH_SOUTH("north_south", 4), EAST_WEST("east_west", 5),
    WALL_NORTH("wall_north", 6), WALL_EAST("wall_east", 7),
    WALL_SOUTH("wall_south", 8), WALL_WEST("wall_west", 9);

    public final @NotNull String str;
    public final @Range(from = 0, to = 9) int id;

    DoubleFaceFacing90(@NotNull String n, @Range(from = 0, to = 9) int i) {
        this.str = n;
        this.id = i;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String asString() {
        return this.str;
    }

    private static final DoubleFaceFacing90[] VARS = {
        SOUTH, WEST, NORTH, EAST, NORTH_SOUTH, EAST_WEST,
        WALL_NORTH, WALL_EAST, WALL_SOUTH, WALL_WEST
    };

    @Contract(pure = true)
    public static DoubleFaceFacing90 byID(int id) {
        return VARS[id];
    }

    public static final EnumProperty<DoubleFaceFacing90> FACING =
        EnumProperty.of("facing", DoubleFaceFacing90.class);

    @Contract(pure = true)
    public boolean isSingle() {
        return this.id < 4 || this.id > 5;
    }

    @Contract(pure = true)
    public boolean isWall() {
        return this.id > 5;
    }
}
