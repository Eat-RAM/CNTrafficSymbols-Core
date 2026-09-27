package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public interface ManagedWaterloggable extends Waterloggable {
    @Override
    public default boolean canFillWithFluid(
        @Nullable LivingEntity filler, BlockView view, BlockPos pos,
        BlockState state, Fluid fluid
    ) {
        return this.getWaterloggedProperty() &&
               Waterloggable.super.canFillWithFluid(
                   filler, view, pos, state, fluid
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
        @Nullable LivingEntity drainer, WorldAccess world, BlockPos pos,
        BlockState state
    ) {
        return this.getWaterloggedProperty() ?
               Waterloggable.super.tryDrainFluid(drainer, world, pos, state) :
               ItemStack.EMPTY;
    }

    public default boolean getWaterloggedProperty() {
        return true;
    }
}
