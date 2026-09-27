package io.github.eat_ram.cntrafficsymbols.core.compat.worldedit.mixin;

import com.sk89q.worldedit.extent.transform.BlockTransformExtent;
import com.sk89q.worldedit.internal.block.BlockStateIdAccess;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.math.transform.Transform;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import io.github.eat_ram.cntrafficsymbols.core.compat.worldedit.WorldEditPasteWarning;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockMirror;
import net.minecraft.util.math.BlockRotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Restriction(require = @Condition("worldedit"))
@Mixin(value = BlockTransformExtent.class, remap = false)
public abstract class BlockTransformExtentMixin {
    @Unique
    private static final Logger cntrafficsymbols_core$LOGGER =
    LoggerFactory.getLogger(BlockTransformExtentMixin.class);
    @Unique
    private static Transform cntrafficsymbols_core$lastWarnedTransform = null;

    @SuppressWarnings("unchecked")
    @Inject(method = "transform", at = @At("HEAD"), cancellable = true)
    private static <B extends BlockStateHolder<B>> void
    transform(B block, Transform transform, CallbackInfoReturnable<B> cir) {
        if (block == null || transform == null || transform.isIdentity()) {
            return;
        }
        int rawId =
        BlockStateIdAccess.getBlockStateId(block.toImmutableState());
        if (!BlockStateIdAccess.isValidInternalId(rawId)) {
            return;
        }
        BlockState mcState = Block.getStateFromRawId(rawId);
        Block mcBlock = mcState.getBlock();
        if (!WorldEditPasteWarning.CNTRAFFICSYMBOLS_NS.matcher(
            Registries.BLOCK.getId(mcBlock).getNamespace()
        ).matches()) {
            return;
        }
        Vector3 zero = transform.apply(Vector3.ZERO);
        Vector3 east = transform.apply(Vector3.at(1, 0, 0)).subtract(zero);
        Vector3 up = transform.apply(Vector3.at(0, 1, 0)).subtract(zero);
        Vector3 south = transform.apply(Vector3.at(0, 0, 1)).subtract(zero);
        double ex = east.x(), ez = east.z();
        double sx = south.x(), sz = south.z();
        boolean verticalUnchanged =
        up.x() == 0. && up.y() == 1. && up.z() == 0. && east.y() == 0. &&
        south.y() == 0.;
        boolean caseNoSwap =
        (ex == 1. || ex == -1.) && ez == 0. && sx == 0. &&
        (sz == 1. || sz == -1.);
        boolean caseAxisSwap =
        ex == 0. && (ez == 1. || ez == -1.) && (sx == 1. || sx == -1.) &&
        sz == 0.;
        if (!verticalUnchanged || (!caseNoSwap && !caseAxisSwap)) {
            WorldEditPasteWarning.COPIED_BLOCKS.get().put(mcBlock, null);
            if (!transform.equals(cntrafficsymbols_core$lastWarnedTransform)) {
                cntrafficsymbols_core$lastWarnedTransform = transform;
                cntrafficsymbols_core$LOGGER.warn(
                    "[CNTrafficSymbols Core] Unsupported non-orthogonal or " +
                    "vertical WorldEdit transform {} for block {}; keeping " +
                    "original state.", transform,
                    Registries.BLOCK.getId(mcBlock)
                );
            }
            cir.setReturnValue(block);
            return;
        }
        if (caseNoSwap) {
            if (ex == -1. && sz == -1.) {
                mcState = mcState.rotate(BlockRotation.CLOCKWISE_180);
            } else if (ex == -1.) { // ex == -1, sz == 1
                mcState = mcState.mirror(BlockMirror.FRONT_BACK);
            } else if (sz == -1.) { // ex == 1, sz == -1
                mcState = mcState.mirror(BlockMirror.LEFT_RIGHT);
            }
        } else /* caseAxisSwap */ if (ez == 1. && sx == -1.) {
            mcState = mcState.rotate(BlockRotation.CLOCKWISE_90);
        } else if (ez == -1. && sx == 1.) {
            mcState = mcState.rotate(BlockRotation.COUNTERCLOCKWISE_90);
        } else if (ez == 1.) { // ez == 1, sx == 1
            mcState = mcState.mirror(BlockMirror.FRONT_BACK)
                             .rotate(BlockRotation.COUNTERCLOCKWISE_90);
        } else { // ez == -1, sx == -1
            mcState = mcState.mirror(BlockMirror.FRONT_BACK)
                             .rotate(BlockRotation.CLOCKWISE_90);
        }
        com.sk89q.worldedit.world.block.BlockState newWeState =
        BlockStateIdAccess.getBlockStateById(Block.getRawIdFromState(mcState));
        if (newWeState != null) {
            cir.setReturnValue((B)((block instanceof BaseBlock) ?
                newWeState.toBaseBlock(((BaseBlock)block).getNbtReference()) :
                newWeState
            ));
        }
    }
}
