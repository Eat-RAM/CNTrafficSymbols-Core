package io.github.eat_ram.cntrafficsymbols.core.block;

import java.util.Iterator;
import java.util.List;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static net.minecraft.util.shape.VoxelShapes.empty;
import static net.minecraft.util.shape.VoxelShapes.union;

public abstract class AbstractCylindricalRodBlock extends Block {
    public static final List<Entry<Vector3d, Vector3d>> DOWN_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, 0., .4375), new Vector3d(.625, .5, .5625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, 0., .375), new Vector3d(.5625, .5, .625)
        )
    );
    public static final VoxelShape DOWN_CENTER =
    createOffsetShape(DOWN_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape DOWN_NORTH =
    createOffsetShape(DOWN_CENTER_BASE, 0, 0, -.375);
    public static final VoxelShape DOWN_NORTHEAST =
    createOffsetShape(DOWN_CENTER_BASE, .375, 0, -.375);
    public static final VoxelShape DOWN_EAST =
    createOffsetShape(DOWN_CENTER_BASE, .375, 0, 0);
    public static final VoxelShape DOWN_SOUTHEAST =
    createOffsetShape(DOWN_CENTER_BASE, .375, 0, .375);
    public static final VoxelShape DOWN_SOUTH =
    createOffsetShape(DOWN_CENTER_BASE, 0, 0, .375);
    public static final VoxelShape DOWN_SOUTHWEST =
    createOffsetShape(DOWN_CENTER_BASE, -.375, 0, .375);
    public static final VoxelShape DOWN_WEST =
    createOffsetShape(DOWN_CENTER_BASE, -.375, 0, 0);
    public static final VoxelShape DOWN_NORTHWEST =
    createOffsetShape(DOWN_CENTER_BASE, -.375, 0, -.375);
    public static final List<Entry<Vector3d, Vector3d>> UP_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, .5, .4375), new Vector3d(.625, 1., .5625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, .5, .375), new Vector3d(.5625, 1., .625)
        )
    );
    public static final VoxelShape UP_CENTER =
    createOffsetShape(UP_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape UP_NORTH =
    createOffsetShape(UP_CENTER_BASE, 0, 0, -.375);
    public static final VoxelShape UP_NORTHEAST =
    createOffsetShape(UP_CENTER_BASE, .375, 0, -.375);
    public static final VoxelShape UP_EAST =
    createOffsetShape(UP_CENTER_BASE, .375, 0, 0);
    public static final VoxelShape UP_SOUTHEAST =
    createOffsetShape(UP_CENTER_BASE, .375, 0, .375);
    public static final VoxelShape UP_SOUTH =
    createOffsetShape(UP_CENTER_BASE, 0, 0, .375);
    public static final VoxelShape UP_SOUTHWEST =
    createOffsetShape(UP_CENTER_BASE, -.375, 0, .375);
    public static final VoxelShape UP_WEST =
    createOffsetShape(UP_CENTER_BASE, -.375, 0, 0);
    public static final VoxelShape UP_NORTHWEST =
    createOffsetShape(UP_CENTER_BASE, -.375, 0, -.375);
    public static final List<Entry<
        Vector3d, Vector3d
    >> NORTH_CENTER_BASE = List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, .4375, 0.), new Vector3d(.625, .5625, .5)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, .375, 0.), new Vector3d(.5625, .625, .5)
        )
    );
    public static final VoxelShape NORTH_CENTER =
    createOffsetShape(NORTH_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape NORTH_UPPER =
    createOffsetShape(NORTH_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape NORTH_UPPEREAST =
    createOffsetShape(NORTH_CENTER_BASE, .375, .375, 0);
    public static final VoxelShape NORTH_EAST =
    createOffsetShape(NORTH_CENTER_BASE, .375, 0, 0);
    public static final VoxelShape NORTH_LOWEREAST =
    createOffsetShape(NORTH_CENTER_BASE, .375, -.375, 0);
    public static final VoxelShape NORTH_LOWER =
    createOffsetShape(NORTH_CENTER_BASE, 0, -.375, 0);
    public static final VoxelShape NORTH_LOWERWEST =
    createOffsetShape(NORTH_CENTER_BASE, -.375, -.375, 0);
    public static final VoxelShape NORTH_WEST =
    createOffsetShape(NORTH_CENTER_BASE, -.375, 0, 0);
    public static final VoxelShape NORTH_UPPERWEST =
    createOffsetShape(NORTH_CENTER_BASE, -.375, .375, 0);
    public static final List<Entry<Vector3d, Vector3d>> SOUTH_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, .4375, .5), new Vector3d(.625, .5625, 1.)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, .375, .5), new Vector3d(.5625, .625, 1.)
        )
    );
    public static final VoxelShape SOUTH_CENTER =
    createOffsetShape(SOUTH_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape SOUTH_UPPER =
    createOffsetShape(SOUTH_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape SOUTH_UPPEREAST =
    createOffsetShape(SOUTH_CENTER_BASE, .375, .375, 0);
    public static final VoxelShape SOUTH_EAST =
    createOffsetShape(SOUTH_CENTER_BASE, .375, 0, 0);
    public static final VoxelShape SOUTH_LOWEREAST =
    createOffsetShape(SOUTH_CENTER_BASE, .375, -.375, 0);
    public static final VoxelShape SOUTH_LOWER =
    createOffsetShape(SOUTH_CENTER_BASE, 0, -.375, 0);
    public static final VoxelShape SOUTH_LOWERWEST =
    createOffsetShape(SOUTH_CENTER_BASE, -.375, -.375, 0);
    public static final VoxelShape SOUTH_WEST =
    createOffsetShape(SOUTH_CENTER_BASE, -.375, 0, 0);
    public static final VoxelShape SOUTH_UPPERWEST =
    createOffsetShape(SOUTH_CENTER_BASE, -.375, .375, 0);
    public static final List<Entry<Vector3d, Vector3d>> WEST_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(0., .4375, .375), new Vector3d(.5, .5625, .625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(0., .375, .4375), new Vector3d(.5, .625, .5625)
        )
    );
    public static final VoxelShape WEST_CENTER =
    createOffsetShape(WEST_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape WEST_UPPER =
    createOffsetShape(WEST_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape WEST_UPPERSOUTH =
    createOffsetShape(WEST_CENTER_BASE, 0, .375, .375);
    public static final VoxelShape WEST_SOUTH =
    createOffsetShape(WEST_CENTER_BASE, 0, 0, .375);
    public static final VoxelShape WEST_LOWERSOUTH =
    createOffsetShape(WEST_CENTER_BASE, 0, -.375, .375);
    public static final VoxelShape WEST_LOWER =
    createOffsetShape(WEST_CENTER_BASE, 0, -.375, 0);
    public static final VoxelShape WEST_LOWERNORTH =
    createOffsetShape(WEST_CENTER_BASE, 0, -.375, -.375);
    public static final VoxelShape WEST_NORTH =
    createOffsetShape(WEST_CENTER_BASE, 0, 0, -.375);
    public static final VoxelShape WEST_UPPERNORTH =
    createOffsetShape(WEST_CENTER_BASE, 0, .375, -.375);
    public static final List<Entry<Vector3d, Vector3d>> EAST_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.5, .4375, .375), new Vector3d(1., .5625, .625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.5, .375, .4375), new Vector3d(1., .625, .5625)
        )
    );
    public static final VoxelShape EAST_CENTER =
    createOffsetShape(EAST_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape EAST_UPPER =
    createOffsetShape(EAST_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape EAST_UPPERSOUTH =
    createOffsetShape(EAST_CENTER_BASE, 0, .375, .375);
    public static final VoxelShape EAST_SOUTH =
    createOffsetShape(EAST_CENTER_BASE, 0, 0, .375);
    public static final VoxelShape EAST_LOWERSOUTH =
    createOffsetShape(EAST_CENTER_BASE, 0, -.375, .375);
    public static final VoxelShape EAST_LOWER =
    createOffsetShape(EAST_CENTER_BASE, 0, -.375, 0);
    public static final VoxelShape EAST_LOWERNORTH =
    createOffsetShape(EAST_CENTER_BASE, 0, -.375, -.375);
    public static final VoxelShape EAST_NORTH =
    createOffsetShape(EAST_CENTER_BASE, 0, 0, -.375);
    public static final VoxelShape EAST_UPPERNORTH =
    createOffsetShape(EAST_CENTER_BASE, 0, .375, -.375);
    public static final List<Entry<Vector3d, Vector3d>> X_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(0., .4375, .375), new Vector3d(1., .5625, .625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(0., .375, .4375), new Vector3d(1., .625, .5625)
        )
    );
    public static final VoxelShape X_CENTER =
    createOffsetShape(X_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape X_UPPER =
    createOffsetShape(X_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape X_UPPERSOUTH =
    createOffsetShape(X_CENTER_BASE, 0, .375, .375);
    public static final VoxelShape X_SOUTH =
    createOffsetShape(X_CENTER_BASE, 0, 0, .375);
    public static final VoxelShape X_LOWERSOUTH =
    createOffsetShape(X_CENTER_BASE, 0, -.375, .375);
    public static final VoxelShape X_LOWER =
    createOffsetShape(X_CENTER_BASE, 0, -.375, 0);
    public static final VoxelShape X_LOWERNORTH =
    createOffsetShape(X_CENTER_BASE, 0, -.375, -.375);
    public static final VoxelShape X_NORTH =
    createOffsetShape(X_CENTER_BASE, 0, 0, -.375);
    public static final VoxelShape X_UPPERNORTH =
    createOffsetShape(X_CENTER_BASE, 0, .375, -.375);
    public static final List<Entry<Vector3d, Vector3d>> Y_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, 0., .4375), new Vector3d(.625, 1., .5625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, 0., .375), new Vector3d(.5625, 1., .625)
        )
    );
    public static final VoxelShape Y_CENTER =
    createOffsetShape(Y_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape Y_NORTH =
    createOffsetShape(Y_CENTER_BASE, 0, 0, -.375);
    public static final VoxelShape Y_NORTHEAST =
    createOffsetShape(Y_CENTER_BASE, .375, 0, -.375);
    public static final VoxelShape Y_EAST =
    createOffsetShape(Y_CENTER_BASE, .375, 0, 0);
    public static final VoxelShape Y_SOUTHEAST =
    createOffsetShape(Y_CENTER_BASE, .375, 0, .375);
    public static final VoxelShape Y_SOUTH =
    createOffsetShape(Y_CENTER_BASE, 0, 0, .375);
    public static final VoxelShape Y_SOUTHWEST =
    createOffsetShape(Y_CENTER_BASE, -.375, 0, .375);
    public static final VoxelShape Y_WEST =
    createOffsetShape(Y_CENTER_BASE, -.375, 0, 0);
    public static final VoxelShape Y_NORTHWEST =
    createOffsetShape(Y_CENTER_BASE, -.375, 0, -.375);
    public static final List<Entry<Vector3d, Vector3d>> Z_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, .4375, 0.), new Vector3d(.625, .5625, 1.)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, .375, 0.), new Vector3d(.5625, .625, 1.)
        )
    );
    public static final VoxelShape Z_CENTER =
    createOffsetShape(Z_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape Z_UPPER =
    createOffsetShape(Z_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape Z_UPPEREAST =
    createOffsetShape(Z_CENTER_BASE, .375, .375, 0);
    public static final VoxelShape Z_EAST =
    createOffsetShape(Z_CENTER_BASE, .375, 0, 0);
    public static final VoxelShape Z_LOWEREAST =
    createOffsetShape(Z_CENTER_BASE, .375, -.375, 0);
    public static final VoxelShape Z_LOWER =
    createOffsetShape(Z_CENTER_BASE, 0, -.375, 0);
    public static final VoxelShape Z_LOWERWEST =
    createOffsetShape(Z_CENTER_BASE, -.375, -.375, 0);
    public static final VoxelShape Z_WEST =
    createOffsetShape(Z_CENTER_BASE, -.375, 0, 0);
    public static final VoxelShape Z_UPPERWEST =
    createOffsetShape(Z_CENTER_BASE, -.375, .375, 0);
    public static final List<Entry<Vector3d, Vector3d>> INTERSECT_CENTER_BASE =
    List.of(
        new SimpleImmutableEntry<>(
            new Vector3d(.375, .4375, .4375), new Vector3d(.625, .5625, .5625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, .375, .4375), new Vector3d(.5625, .625, .5625)
        ),
        new SimpleImmutableEntry<>(
            new Vector3d(.4375, .4375, .375), new Vector3d(.5625, .5625, .625)
        )
    );
    public static final VoxelShape INTERSECT_CENTER =
    createOffsetShape(INTERSECT_CENTER_BASE, 0, 0, 0);
    public static final VoxelShape INTERSECT_UPPER =
    createOffsetShape(INTERSECT_CENTER_BASE, 0, .375, 0);
    public static final VoxelShape INTERSECT_UPPERNORTH =
    createOffsetShape(INTERSECT_CENTER_BASE, 0, .375, -.375);
    public static final VoxelShape INTERSECT_UPPERNORTHEAST =
    createOffsetShape(INTERSECT_CENTER_BASE, .375, .375, -.375);

    public static VoxelShape createOffsetShape(@NotNull Iterable<
        ? extends @NotNull Entry<
            ? extends @NotNull Vector3d, ? extends @NotNull Vector3d
        >
    > shape, double offX, double offY, double offZ) {
        Iterator<? extends Entry<? extends Vector3d, ? extends Vector3d>> itt =
        shape.iterator();
        if (!(itt.hasNext())) {
            return empty();
        }
        Entry<? extends Vector3d, ? extends Vector3d> e = itt.next();
        Vector3d min = e.getKey();
        Vector3d max = e.getValue();
        VoxelShape r = cuboid(
            min.x + offX, min.y + offY, min.z + offZ,
            max.x + offX, max.y + offY, max.z + offZ
        );
        while (itt.hasNext()) {
            e = itt.next();
            min = e.getKey();
            max = e.getValue();
            r = union(r, cuboid(
                min.x + offX, min.y + offY, min.z + offZ,
                max.x + offX, max.y + offY, max.z + offZ
            ));
        }
        return r;
    }

    protected AbstractCylindricalRodBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(
        BlockState state, WorldView world, ScheduledTickView tickView,
        BlockPos pos, Direction direction, BlockPos neighborPos,
        BlockState neighborState, Random random
    ) {
        FluidState fs = state.getFluidState();
        if (!fs.isEmpty()) {
            Fluid fluid = fs.getFluid();
            tickView.scheduleFluidTick(pos, fluid, fluid.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(
            state, world, tickView, pos, direction, neighborPos, neighborState,
            random
        );
    }
}
