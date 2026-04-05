package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public interface ManagedWaterloggable extends Waterloggable {
    @Override
    public default boolean canFillWithFluid(
        PlayerEntity player, BlockView view, BlockPos pos, BlockState state,
        Fluid fluid
    ) {
        return this.getWaterloggedProperty() &&
               Waterloggable.super.canFillWithFluid(
                   player, view, pos, state, fluid
               );
    }

    @Override
    public default boolean tryFillWithFluid(
        WorldAccess world, BlockPos pos, BlockState state,
        FluidState fluidState
    ) {
        return this.getWaterloggedProperty() &&
               Waterloggable.super.tryFillWithFluid(
                   world, pos, state, fluidState
               );
    }

    @Override
    public default ItemStack tryDrainFluid(
        PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state
    ) {
        return this.getWaterloggedProperty() ?
               Waterloggable.super.tryDrainFluid(player, world, pos, state) :
               ItemStack.EMPTY;
    }

    public default boolean getWaterloggedProperty() {
        return true;
    }
}
