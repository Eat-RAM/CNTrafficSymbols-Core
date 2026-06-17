package io.github.eat_ram.cntrafficsymbols.core.mixin;

import io.github.eat_ram.cntrafficsymbols.core.block.ManagedWaterloggable;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.item.Items.WATER_BUCKET;

@Mixin(net.minecraft.item.BucketItem.class)
public abstract class BucketItemMixin {
    @Inject(method = "placeFluid", at = @At("HEAD"), cancellable = true)
    private void injectPlaceFluid(
        @Nullable LivingEntity user, World world, BlockPos pos,
        @Nullable BlockHitResult hitResult,
        CallbackInfoReturnable<Boolean> info
    ) {
        if (WATER_BUCKET == (Object)this) {
            Block b = world.getBlockState(pos).getBlock();
            if (b instanceof ManagedWaterloggable mw &&
                !mw.getWaterloggedProperty()) {
                info.setReturnValue(false);
            }
        }
    }
}
