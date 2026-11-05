package io.github.eat_ram.cntrafficsymbols.core.block;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import io.github.eat_ram.cntrafficsymbols.core.struct.Attachment3;
import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2d;
import oshi.util.tuples.Quartet;

import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static net.minecraft.util.shape.VoxelShapes.union;

public class CircleStandingSymbolBlock
extends Attachment3DoubleFaceFacingBlock {
    public static final VoxelShape SHAPE0 = cuboid(
        .3125, .3125, .3125, .6875, .6875, .6875
    );
    public static final VoxelShape SHAPE1 = cuboid(
        .3125, 0, .3125, .6875, .375, .6875
    );
    public static final VoxelShape SHAPE2 = cuboid(
        .3125, .625, .3125, .6875, 1, .6875
    );
    private static final ConcurrentHashMap<@NotNull Pair<
        @NotNull Double, @NotNull Double
    >, @NotNull Quartet<
        @NotNull Triple<
            @NotNull VoxelShape, @NotNull VoxelShape, @NotNull VoxelShape
        >, @NotNull Triple<
            @NotNull VoxelShape, @NotNull VoxelShape, @NotNull VoxelShape
        >, @NotNull Triple<
            @NotNull VoxelShape, @NotNull VoxelShape, @NotNull VoxelShape
        >, @NotNull Triple<
            @NotNull VoxelShape, @NotNull VoxelShape, @NotNull VoxelShape
        >
    >> CACHED_SHAPES = new ConcurrentHashMap<>();

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Vector2d @NotNull[]
    getCircleCovers(double diameter) {
        Vector2d[] vectors = new Vector2d[]{
            new Vector2d(0., 0.375),
            new Vector2d(0.03125, 0.28125),
            new Vector2d(0.0625, 0.21875),
            new Vector2d(0.09375, 0.1875),
            new Vector2d(0.125, 0.15625),
            new Vector2d(0.15625, 0.125),
            new Vector2d(0.1875, 0.09375),
            new Vector2d(0.21875, 0.0625),
            new Vector2d(0.28125, 0.03125),
            new Vector2d(0.375, 0.)
        };
        double margin = (1. - diameter) / 2;
        for (Vector2d vector : vectors) {
            vector.mul(diameter);
            vector.x += margin;
        }
        return vectors;
    }

    public final double diameter;
    public final @NotNull VoxelShape shape0N;
    public final @NotNull VoxelShape shape1N;
    public final @NotNull VoxelShape shape2N;
    public final @NotNull VoxelShape shape0E;
    public final @NotNull VoxelShape shape1E;
    public final @NotNull VoxelShape shape2E;
    public final @NotNull VoxelShape shape0S;
    public final @NotNull VoxelShape shape1S;
    public final @NotNull VoxelShape shape2S;
    public final @NotNull VoxelShape shape0W;
    public final @NotNull VoxelShape shape1W;
    public final @NotNull VoxelShape shape2W;

    public CircleStandingSymbolBlock(
        double diameter, double thickness, Settings settings
    ) {
        super(settings);
        this.diameter = diameter;
        Pair<Double, Double> pair = Pair.of(this.diameter, thickness);
        Quartet<Triple<VoxelShape, VoxelShape, VoxelShape>,
                Triple<VoxelShape, VoxelShape, VoxelShape>,
                Triple<VoxelShape, VoxelShape, VoxelShape>,
                Triple<VoxelShape, VoxelShape, VoxelShape>> quartet =
        CACHED_SHAPES.get(pair);
        if (quartet != null) {
            Triple<VoxelShape, VoxelShape, VoxelShape> triple =
            quartet.getA();
            this.shape0N = triple.getLeft();
            this.shape1N = triple.getMiddle();
            this.shape2N = triple.getRight();
            triple = quartet.getB();
            this.shape0E = triple.getLeft();
            this.shape1E = triple.getMiddle();
            this.shape2E = triple.getRight();
            triple = quartet.getC();
            this.shape0S = triple.getLeft();
            this.shape1S = triple.getMiddle();
            this.shape2S = triple.getRight();
            triple = quartet.getD();
            this.shape0W = triple.getLeft();
            this.shape1W = triple.getMiddle();
            this.shape2W = triple.getRight();
        } else {
            Vector2d[] vectors = getCircleCovers(this.diameter);
            double oneMinusThickness = 1. - thickness;
            double sx = vectors[0].x;
            double oneMinusSx = 1. - sx;
            double sy = vectors[0].y;
            double dMinusSy = this.diameter - sy;
            double oneMinusD = 1. - this.diameter;
            double oneMinusDHalf = oneMinusD / 2.;
            VoxelShape shape0N = cuboid(
                sx, sy + oneMinusDHalf, oneMinusThickness,
                oneMinusSx, dMinusSy + oneMinusDHalf, 1.
            );
            VoxelShape shape1N =
            cuboid(sx, sy, oneMinusThickness, oneMinusSx, dMinusSy, 1.);
            VoxelShape shape2N = cuboid(
                sx, 1. - dMinusSy, oneMinusThickness, oneMinusSx, 1. - sy, 1.
            );
            VoxelShape shape0E = cuboid(
                0., sy + oneMinusDHalf, sx,
                thickness, dMinusSy + oneMinusDHalf, oneMinusSx
            );
            VoxelShape shape1E =
            cuboid(0., sy, sx, thickness, dMinusSy, oneMinusSx);
            VoxelShape shape2E =
            cuboid(0., 1. - dMinusSy, sx, thickness, 1. - sy, oneMinusSx);
            VoxelShape shape0S = cuboid(
                sx, sy + oneMinusDHalf, 0.,
                oneMinusSx, dMinusSy + oneMinusDHalf, thickness
            );
            VoxelShape shape1S =
            cuboid(sx, sy, 0., oneMinusSx, dMinusSy, thickness);
            VoxelShape shape2S = cuboid(
                sx, 1. - dMinusSy, 0., oneMinusSx, 1. - sy, thickness
            );
            VoxelShape shape0W = cuboid(
                oneMinusThickness, sy + oneMinusDHalf, sx,
                1., dMinusSy + oneMinusDHalf, oneMinusSx
            );
            VoxelShape shape1W =
            cuboid(oneMinusThickness, sy, sx, 1., dMinusSy, oneMinusSx);
            VoxelShape shape2W = cuboid(
                oneMinusThickness, 1. - dMinusSy, sx, 1., 1. - sy, oneMinusSx
            );
            for (int i = 1; i < vectors.length; i++) {
                sx = vectors[i].x;
                oneMinusSx = 1. - sx;
                sy = vectors[i].y;
                dMinusSy = this.diameter - sy;
                oneMinusD = 1. - this.diameter;
                oneMinusDHalf = oneMinusD / 2.;
                shape0N = union(shape0N, cuboid(
                    sx, sy + oneMinusDHalf, oneMinusThickness,
                    oneMinusSx, dMinusSy + oneMinusDHalf, 1.
                ));
                shape1N = union(shape1N, cuboid(
                    sx, sy, oneMinusThickness, oneMinusSx, dMinusSy, 1.
                ));
                shape2N = union(shape2N, cuboid(
                    sx, 1. - dMinusSy, oneMinusThickness, oneMinusSx, 1. - sy, 1.
                ));
                shape0E = union(shape0E, cuboid(
                    0., sy + oneMinusDHalf, sx,
                    thickness, dMinusSy + oneMinusDHalf, oneMinusSx
                ));
                shape1E = union(shape1E, cuboid(
                    0., sy, sx, thickness, dMinusSy, oneMinusSx
                ));
                shape2E = union(shape2E, cuboid(
                    0., 1. - dMinusSy, sx, thickness, 1. - sy, oneMinusSx
                ));
                shape0S = union(shape0S, cuboid(
                    sx, sy + oneMinusDHalf, 0.,
                    oneMinusSx, dMinusSy + oneMinusDHalf, thickness
                ));
                shape1S = union(shape1S, cuboid(
                    sx, sy, 0., oneMinusSx, dMinusSy, thickness
                ));
                shape2S = union(shape2S, cuboid(
                    sx, 1. - dMinusSy, 0., oneMinusSx, 1. - sy, thickness
                ));
                shape0W = union(shape0W, cuboid(
                    oneMinusThickness, sy + oneMinusDHalf, sx,
                    1., dMinusSy + oneMinusDHalf, oneMinusSx
                ));
                shape1W = union(shape1W, cuboid(
                    oneMinusThickness, sy, sx, 1., dMinusSy, oneMinusSx
                ));
                shape2W = union(shape2W, cuboid(
                    oneMinusThickness, 1. - dMinusSy, sx, 1., 1. - sy, oneMinusSx
                ));
            }
            CACHED_SHAPES.put(pair, new Quartet<>(
                Triple.of(shape0N, shape1N, shape2N),
                Triple.of(shape0E, shape1E, shape2E),
                Triple.of(shape0S, shape1S, shape2S),
                Triple.of(shape0W, shape1W, shape2W)
            ));
            this.shape0N = shape0N;
            this.shape1N = shape1N;
            this.shape2N = shape2N;
            this.shape0E = shape0E;
            this.shape1E = shape1E;
            this.shape2E = shape2E;
            this.shape0S = shape0S;
            this.shape1S = shape1S;
            this.shape2S = shape2S;
            this.shape0W = shape0W;
            this.shape1W = shape1W;
            this.shape2W = shape2W;
        }
    }

    public CircleStandingSymbolBlock(Settings settings) {
        this(0.8, 0.025, settings);
    }

    @Override
    public VoxelShape getOutlineShape(
        BlockState state, BlockView view, BlockPos pos, ShapeContext context
    ) {
        int f = state.get(DoubleFaceFacing.FACING).id;
        int i = state.get(Attachment3.ATTACHMENT).id;
        switch (f) {
            case 24:
                switch (i) {
                    case 1: return this.shape1N;
                    case 2: return this.shape2N;
                    default: return this.shape0N;
                }
            case 25:
                switch (i) {
                    case 1: return this.shape1E;
                    case 2: return this.shape2E;
                    default: return this.shape0E;
                }
            case 26:
                switch (i) {
                    case 1: return this.shape1S;
                    case 2: return this.shape2S;
                    default: return this.shape0S;
                }
            case 27:
                switch (i) {
                    case 1: return this.shape1W;
                    case 2: return this.shape2W;
                    default: return this.shape0W;
                }
        }
        switch (i) {
            case 1: return SHAPE1;
            case 2: return SHAPE2;
            default: return SHAPE0;
        }
    }
}
