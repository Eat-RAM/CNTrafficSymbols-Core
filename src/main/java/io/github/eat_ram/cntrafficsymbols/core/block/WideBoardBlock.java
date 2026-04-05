package io.github.eat_ram.cntrafficsymbols.core.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import io.github.eat_ram.cntrafficsymbols.core.helper.DoubleFaceFacing90Mirrorer;
import io.github.eat_ram.cntrafficsymbols.core.helper.DoubleFaceFacing90Rotator;
import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing90;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;
import static net.minecraft.util.shape.VoxelShapes.cuboid;

public class WideBoardBlock extends Block implements ManagedWaterloggable {
    public static final VoxelShape SHAPE0 = cuboid(0, 0, .5, 1, 1, .5625);
    public static final VoxelShape SHAPE1 = cuboid(.4375, 0, 0, .5, 1, 1);
    public static final VoxelShape SHAPE2 = cuboid(0, 0, .4375, 1, 1, .5);
    public static final VoxelShape SHAPE3 = cuboid(.5, 0, 0, .5625, 1, 1);
    public static final VoxelShape SHAPE4 = cuboid(0, 0, .4375, 1, 1, .5625);
    public static final VoxelShape SHAPE5 = cuboid(.4375, 0, 0, .5625, 1, 1);
    public static final VoxelShape SHAPE6 = cuboid(0, 0, .9375, 1, 1, 1);
    public static final VoxelShape SHAPE7 = cuboid(0, 0, 0, .0625, 1, 1);
    public static final VoxelShape SHAPE8 = cuboid(0, 0, 0, 1, 1, .0625);
    public static final VoxelShape SHAPE9 = cuboid(.9375, 0, 0, 1, 1, 1);
    private List<WideBoardBlock> arr;
    private int arri = -1;

    public WideBoardBlock(Settings settings) {
        super(settings);
        BlockState st = this.getDefaultState().with(DoubleFaceFacing90.FACING,
                                                    DoubleFaceFacing90.SOUTH);
        this.arr = new ArrayList<>();
        if (this.getWaterloggedProperty()) {
            st = st.with(WATERLOGGED, false);
        }
        this.setDefaultState(st);
    }

    public WideBoardBlock(WideBoardBlock cb, Settings settings) {
        super(settings);
        BlockState st = this.getDefaultState().with(DoubleFaceFacing90.FACING,
                                                    DoubleFaceFacing90.SOUTH);
        this.arr = cb.arr;
        if (this.getWaterloggedProperty()) {
            st = st.with(WATERLOGGED, false);
        }
        this.setDefaultState(st);
    }

    public boolean addToArr(WideBoardBlock b)
    throws UnsupportedOperationException {
        return this.arr.add(b);
    }

    public void addToArr(int i, WideBoardBlock b)
    throws UnsupportedOperationException {
        this.arr.add(i, b);
    }

    public WideBoardBlock getInArr(int i)
    throws IndexOutOfBoundsException {
        return this.arr.get(i);
    }

    public WideBoardBlock removeInArr(int i)
    throws UnsupportedOperationException, IndexOutOfBoundsException {
        return this.arr.remove(i);
    }

    public List<WideBoardBlock> freezeArr() throws IllegalArgumentException {
        int s = this.arr.size();
        for (int i = 0; i < s; i++) {
            if (this.arr.get(i) == this) {
                if (this.arri != -1) {
                    throw new IllegalArgumentException();
                }
                this.arri = i;
            }
        }
        if (this.arri == -1) {
            throw new IllegalArgumentException();
        }
        return this.arr = List.copyOf(this.arr);
    }

    @Override
    public VoxelShape getOutlineShape(
        BlockState state, BlockView view, BlockPos pos, ShapeContext context
    ) {
        switch (state.get(DoubleFaceFacing90.FACING).id) {
            case 0: return SHAPE0;
            case 1: return SHAPE1;
            case 2: return SHAPE2;
            case 3: return SHAPE3;
            case 4: return SHAPE4;
            case 5: return SHAPE5;
            case 6: return SHAPE6;
            case 7: return SHAPE7;
            case 8: return SHAPE8;
            case 9: return SHAPE9;
            default: assert false;
        }
        return null;
    }

    @Override
    protected void
    appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DoubleFaceFacing90.FACING);
        if (this.getWaterloggedProperty()) {
            builder.add(WATERLOGGED);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState st = ctx.getWorld().getBlockState(ctx.getBlockPos());
        BlockState sst = st.isOf(this) ? st.with(
            DoubleFaceFacing90.FACING, DoubleFaceFacing90.byID(
                st.get(DoubleFaceFacing90.FACING).id % 2 + 4
            )
        ) : this.getDefaultState().with(
            DoubleFaceFacing90.FACING, DoubleFaceFacing90.byID(
                ctx.getSide().getAxis().isVertical() ?
                ctx.getHorizontalPlayerFacing().getOpposite().getHorizontal() :
                (ctx.getSide().getHorizontal() + 2) % 4 + 6
            )
        );
        if (this.getWaterloggedProperty()) {
            sst = sst.with(WATERLOGGED, ctx.getWorld().getFluidState(
                ctx.getBlockPos()
            ).getFluid() == WATER);
        }
        return sst;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        return state.get(DoubleFaceFacing90.FACING).id < 4 &&
               ctx.getStack().isOf(this.asItem());
    }

    @Override
    public BlockState rotate(BlockState st, BlockRotation rot) {
        if (rot == BlockRotation.NONE) {
            return st;
        }
        return st.with(
            DoubleFaceFacing90.FACING, DoubleFaceFacing90Rotator.rotate(
                st.get(DoubleFaceFacing90.FACING),
                ((rot == BlockRotation.CLOCKWISE_90) ? 1 :
                 ((rot == BlockRotation.CLOCKWISE_180) ? 2 : 3))
            )
        );
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mir) {
        if (mir == BlockMirror.NONE) {
            return state;
        }
        BlockState sst = this.arr.get(
            this.arr.size() - 1 - this.arri
        ).getDefaultState().with(
            DoubleFaceFacing90.FACING,
            (mir == BlockMirror.FRONT_BACK) ?
            DoubleFaceFacing90Mirrorer.mirrorX(
                state.get(DoubleFaceFacing90.FACING)
            ) :
            DoubleFaceFacing90Mirrorer.mirrorZ(
                state.get(DoubleFaceFacing90.FACING)
            )
        );
        if (this.getWaterloggedProperty()) {
            sst = sst.with(WATERLOGGED, state.get(WATERLOGGED));
        }
        return sst;
    }

    @Override
    public BlockState getStateForNeighborUpdate(
        BlockState state, Direction direction, BlockState neighborState,
        WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (this.getWaterloggedProperty() &&
            state.get(WATERLOGGED).booleanValue()) {
            world.scheduleFluidTick(pos, WATER, WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(
            state, direction, neighborState, world, pos, neighborPos
        );
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return (this.getWaterloggedProperty() &&
                state.get(WATERLOGGED).booleanValue()) ?
               WATER.getStill(false) : super.getFluidState(state);
    }
}
