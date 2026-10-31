package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;

import static net.minecraft.state.property.Properties.WATERLOGGED;

public interface VanillaWaterloggable
extends FluidloggedConfigurable, Waterloggable {
    @Override
    default BlockState
    configurePlacedFluid(BlockState raw, ItemPlacementContext ctx) {
        return raw.with(WATERLOGGED, ctx.getWorld().getFluidState(
            ctx.getBlockPos()
        ).getFluid() == Fluids.WATER);
    }

    @Override
    default BlockState
    configureCopiedFluid(BlockState src, BlockState dstRaw) {
        return dstRaw.withIfExists(WATERLOGGED, src.get(WATERLOGGED));
    }
}
