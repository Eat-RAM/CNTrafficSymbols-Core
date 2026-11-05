package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;

public interface FluidloggedConfigurable {
    default BlockState
    configurePlacedFluid(BlockState raw, ItemPlacementContext ctx) {
        return raw;
    }

    default BlockState
    configureCopiedFluid(BlockState src, BlockState dstRaw) {
        return dstRaw;
    }
}
