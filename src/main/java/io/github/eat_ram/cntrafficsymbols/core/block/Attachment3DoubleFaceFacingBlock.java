package io.github.eat_ram.cntrafficsymbols.core.block;

import io.github.eat_ram.cntrafficsymbols.core.struct.Attachment3;
import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleFaceFacing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.RotationPropertyHelper;

import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;

public class Attachment3DoubleFaceFacingBlock extends DoubleFaceFacingBlock {
    public Attachment3DoubleFaceFacingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(
            Attachment3.ATTACHMENT, Attachment3.DOWN
        ));
    }

    @Override
    protected void
    appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DoubleFaceFacing.FACING, Attachment3.ATTACHMENT);
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
        ).with(Attachment3.ATTACHMENT, ctx.getSide().getAxis().isVertical() ? (
            (ctx.getSide().getId() == 0) ? Attachment3.UP : Attachment3.DOWN
        ) : (
            (ctx.getHitPos().y - ctx.getBlockPos().getY() >= .75) ?
            Attachment3.UP : (
                (ctx.getHitPos().y - ctx.getBlockPos().getY() <= .25) ?
                Attachment3.DOWN : Attachment3.NONE
            )
        ));
        if (this.getWaterloggedProperty()) {
            sst = sst.with(WATERLOGGED, ctx.getWorld().getFluidState(
                ctx.getBlockPos()
            ).getFluid() == WATER);
        }
        return sst;
    }
}
