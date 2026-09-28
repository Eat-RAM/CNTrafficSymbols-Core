package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MultiItemComposed {
    public @NotNull Iterable<@NotNull ItemStack>
    getComposedItems(BlockState state, @Nullable BlockEntity be);
}
