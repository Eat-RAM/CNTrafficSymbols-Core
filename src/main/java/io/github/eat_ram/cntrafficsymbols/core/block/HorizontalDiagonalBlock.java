package io.github.eat_ram.cntrafficsymbols.core.block;

import java.util.Map.Entry;

import io.github.eat_ram.cntrafficsymbols.core.struct.CoordCorner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3dc;
import org.joml.Vector4dc;

import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static net.minecraft.util.shape.VoxelShapes.empty;
import static net.minecraft.util.shape.VoxelShapes.union;

public class HorizontalDiagonalBlock extends Block {
    private final VoxelShape[] shapes;
    public final float shiftDegrees;

    public HorizontalDiagonalBlock(@NotNull Iterable<
        ? extends @NotNull Entry<@NotNull Vector3dc, @NotNull Vector3dc>
    > boxes, float shiftDegrees, Settings settings) {
        super(settings);
        this.shiftDegrees = shiftDegrees;
        this.shapes = new VoxelShape[]{null, null, null, null};
        for (Entry<Vector3dc, Vector3dc> box : boxes) {
            Vector3dc min = box.getKey();
            Vector3dc max = box.getValue();
            double sx = min.x();
            double sy = min.y();
            double sz = min.z();
            double ex = max.x();
            double ey = max.y();
            double ez = max.z();
            VoxelShape shape = cuboid(sx, sy, sz, ex, ey, ez);
            this.shapes[0] =
            this.shapes[0] == null ? shape : union(this.shapes[0], shape);
            shape = cuboid(1. - ez, sy, sx, 1. - sz, ey, ex);
            this.shapes[1] =
            this.shapes[1] == null ? shape : union(this.shapes[1], shape);
            shape = cuboid(1. - ex, sy, 1. - ez, 1. - sx, ey, 1. - sz);
            this.shapes[2] =
            this.shapes[2] == null ? shape : union(this.shapes[2], shape);
            shape = cuboid(sz, sy, 1. - ex, ez, ey, 1. - sx);
            this.shapes[3] =
            this.shapes[3] == null ? shape : union(this.shapes[3], shape);
        }
        for (int i = 0; i < this.shapes.length; i++) {
            if (this.shapes[i] == null) {
                this.shapes[i] = empty();
            }
        }
        this.setDefaultState(this.getDefaultState().with(
            CoordCorner.CORNER, CoordCorner.SOUTHEAST
        ));
    }

    public HorizontalDiagonalBlock(
        @NotNull Iterable<? extends @NotNull Vector4dc> boxes, double minY,
        double maxY, float shiftDegrees, Settings settings
    ) {
        super(settings);
        this.shiftDegrees = shiftDegrees;
        this.shapes = new VoxelShape[]{null, null, null, null};
        for (Vector4dc box : boxes) {
            double sx = box.x();
            double sz = box.y();
            double ex = box.z();
            double ez = box.w();
            VoxelShape shape = cuboid(sx, minY, sz, ex, maxY, ez);
            this.shapes[0] =
            this.shapes[0] == null ? shape : union(this.shapes[0], shape);
            shape = cuboid(1. - ez, minY, sx, 1. - sz, maxY, ex);
            this.shapes[1] =
            this.shapes[1] == null ? shape : union(this.shapes[1], shape);
            shape = cuboid(1. - ex, minY, 1. - ez, 1. - sx, maxY, 1. - sz);
            this.shapes[2] =
            this.shapes[2] == null ? shape : union(this.shapes[2], shape);
            shape = cuboid(sz, minY, 1. - ex, ez, maxY, 1. - sx);
            this.shapes[3] =
            this.shapes[3] == null ? shape : union(this.shapes[3], shape);
        }
        for (int i = 0; i < this.shapes.length; i++) {
            if (this.shapes[i] == null) {
                this.shapes[i] = empty();
            }
        }
        this.setDefaultState(this.getDefaultState().with(
            CoordCorner.CORNER, CoordCorner.SOUTHEAST
        ));
    }

    @Override
    public VoxelShape getOutlineShape(
        BlockState state, BlockView world, BlockPos pos, ShapeContext context
    ) {
        return this.shapes[state.get(CoordCorner.CORNER).id];
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
               .with(CoordCorner.CORNER, CoordCorner.byID(Math.floorMod(
            (int)(ctx.getPlayerYaw() + 315f + this.shiftDegrees), 360
        ) / 90));
    }

    @Override
    protected void
    appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CoordCorner.CORNER);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_90: return state.with(
                CoordCorner.CORNER,
                CoordCorner.byID((state.get(CoordCorner.CORNER).id + 1) & 3)
            );
            case CLOCKWISE_180: return state.with(
                CoordCorner.CORNER,
                CoordCorner.byID((state.get(CoordCorner.CORNER).id + 2) & 3)
            );
            case COUNTERCLOCKWISE_90: return state.with(
                CoordCorner.CORNER,
                CoordCorner.byID((state.get(CoordCorner.CORNER).id + 3) & 3)
            );
            default: return state;
        }
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        switch (mirror) {
            case FRONT_BACK: return state.with(
                CoordCorner.CORNER,
                CoordCorner.byID(state.get(CoordCorner.CORNER).id ^ 1)
            );
            case LEFT_RIGHT: return state.with(
                CoordCorner.CORNER,
                CoordCorner.byID(state.get(CoordCorner.CORNER).id ^ 3)
            );
            default: return state;
        }
    }
}
