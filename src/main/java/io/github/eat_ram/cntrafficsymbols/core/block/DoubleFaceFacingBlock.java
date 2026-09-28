package io.github.eat_ram.cntrafficsymbols.core.block;

import io.github.eat_ram.cntrafficsymbols.core.helper.DoubleFaceFacingMirrorer;
import io.github.eat_ram.cntrafficsymbols.core.helper.DoubleFaceFacingRotator;
import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;

public class DoubleFaceFacingBlock extends Block
implements ManagedWaterloggable, CountedItemComposed {
    public DoubleFaceFacingBlock(Settings settings) {
        super(settings);
        BlockState st = this.getDefaultState().with(
            DoubleFaceFacing.FACING, DoubleFaceFacing.SOUTH
        );
        if (this.getWaterloggedProperty()) {
            st = st.with(WATERLOGGED, false);
        }
        this.setDefaultState(st);
    }

    @Override
    public BlockState rotate(BlockState st, BlockRotation rotation) {
        if (rotation == BlockRotation.NONE) {
            return st;
        }
        return st.with(DoubleFaceFacing.FACING, DoubleFaceFacingRotator.rotate(
            st.get(DoubleFaceFacing.FACING),
            ((rotation == BlockRotation.CLOCKWISE_90) ? 1 :
             ((rotation == BlockRotation.CLOCKWISE_180) ? 2 : 3))
        ));
    }

    @Override
    public BlockState mirror(BlockState st, BlockMirror mirror) {
        if (mirror == BlockMirror.NONE) {
            return st;
        }
        return st.with(
            DoubleFaceFacing.FACING,
            (mirror == BlockMirror.FRONT_BACK) ?
            DoubleFaceFacingMirrorer.mirrorX(st.get(DoubleFaceFacing.FACING)) :
            DoubleFaceFacingMirrorer.mirrorZ(st.get(DoubleFaceFacing.FACING))
        );
    }

    @Override
    public boolean
    isTransparent(BlockState state, BlockView view, BlockPos pos) {
        return true;
    }

    @Override
    protected void
    appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DoubleFaceFacing.FACING);
        if (this.getWaterloggedProperty()) {
            builder.add(WATERLOGGED);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState st = ctx.getWorld().getBlockState(ctx.getBlockPos());
        BlockState sst = st.isOf(this) ? st.with(
            DoubleFaceFacing.FACING, DoubleFaceFacing.byID(
                st.get(DoubleFaceFacing.FACING).id % 8 + 16
            )
        ) : this.getDefaultState().with(
            DoubleFaceFacing.FACING, DoubleFaceFacing.byID(
                ctx.getSide().getAxis().isVertical() ?
                RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180f) :
                (ctx.getSide().getHorizontal() + 2) % 4 + 24
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

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        return state.get(DoubleFaceFacing.FACING).id < 16 &&
               ctx.getStack().isOf(this.asItem());
    }

    @Override
    public float calcBlockBreakingDelta(
        BlockState state, PlayerEntity player, BlockView view, BlockPos pos
    ) {
        return state.get(DoubleFaceFacing.FACING).isSingle() ?
               super.calcBlockBreakingDelta(state, player, view, pos) :
               (super.calcBlockBreakingDelta(
                   state, player, view, pos
               ) / 1.5625f);
    }

    @Override
    public int
    getComposedItemCount(BlockState state, @Nullable BlockEntity be) {
        return state.get(DoubleFaceFacing.FACING).isSingle() ? 1 : 2;
    }
}
