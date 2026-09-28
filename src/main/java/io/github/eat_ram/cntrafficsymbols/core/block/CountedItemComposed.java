package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public interface CountedItemComposed {
    public int
    getComposedItemCount(BlockState state, @Nullable BlockEntity be);
}
