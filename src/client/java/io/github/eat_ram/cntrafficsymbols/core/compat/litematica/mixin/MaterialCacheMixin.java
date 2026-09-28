package io.github.eat_ram.cntrafficsymbols.core.compat.litematica.mixin;

import java.util.Iterator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.eat_ram.cntrafficsymbols.core.block.CountedItemComposed;
import io.github.eat_ram.cntrafficsymbols.core.block.MultiItemComposed;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Restriction(require = @Condition("litematica"))
@Mixin(fi.dy.masa.litematica.materials.MaterialCache.class)
public abstract class MaterialCacheMixin {
    @ModifyReturnValue(method = "requiresMultipleItems", at = @At("RETURN"))
    private boolean addEntries(boolean original, BlockState state) {
        Block block = state.getBlock();
        if (block instanceof MultiItemComposed) {
            boolean req = false;
            for (ItemStack i :
                 ((MultiItemComposed)block).getComposedItems(state, null)) {
                if (req) {
                    return true;
                }
                req = true;
            }
        }
        return original;
    }

    @ModifyReturnValue(method = "getStateToItemOverride", at = @At("RETURN"))
    private ItemStack addEntries(ItemStack original, BlockState state) {
        Block block = state.getBlock();
        if (block instanceof MultiItemComposed) {
            Iterator<ItemStack> itr =
            ((MultiItemComposed)block).getComposedItems(state, null)
            .iterator();
            if (itr.hasNext()) {
                ItemStack i = itr.next();
                if (!itr.hasNext()) {
                    return i;
                }
            }
        }
        return original;
    }

    @ModifyReturnValue(
        method = "getItems(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Lcom/google/common/collect/ImmutableList;",
        at = @At("RETURN")
    )
    private ImmutableList<ItemStack> addEntries(
        ImmutableList<ItemStack> original, BlockState state, World world,
        BlockPos pos
    ) {
        Block block = state.getBlock();
        if (block instanceof MultiItemComposed) {
            return ImmutableList.copyOf(
                ((MultiItemComposed)block).getComposedItems(state, null)
            );
        }
        return original;
    }

    @Inject(method = "overrideStackSize", at = @At("RETURN"))
    private void
    overrideStackSize(BlockState state, ItemStack stack, CallbackInfo ci) {
        Block block = state.getBlock();
        if (block instanceof MultiItemComposed) {
            for (ItemStack i :
                 ((MultiItemComposed)block).getComposedItems(state, null)) {
                if (stack.getItem() == i.getItem()) {
                    stack.setCount(i.getCount());
                    break;
                }
            }
        } else if (block instanceof CountedItemComposed) {
            stack.setCount(
                ((CountedItemComposed)block).getComposedItemCount(state, null)
            );
        }
    }
}
