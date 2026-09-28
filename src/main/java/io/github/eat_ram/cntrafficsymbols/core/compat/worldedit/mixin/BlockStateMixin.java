package io.github.eat_ram.cntrafficsymbols.core.compat.worldedit.mixin;

import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Table;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sk89q.worldedit.registry.state.Property;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import io.github.eat_ram.cntrafficsymbols.core.compat.worldedit
       .WorldEditPasteWarning;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Restriction(
    require = @Condition(value = "worldedit", versionPredicates = "<7.4.0")
)
@Mixin(value = BlockState.class, remap = false)
public abstract class BlockStateMixin {
    @Shadow
    private Table<Property<?>, Object, BlockState> states;

    @Shadow
    protected abstract <V> Map<Property<?>, Object>
    withValue(final Property<V> property, final V value);

    @Unique
    private static final Logger cntrafficsymbols_core$LOGGER =
    LoggerFactory.getLogger(BlockStateMixin.class);
    @Unique
    private static final Pattern COLON = Pattern.compile(":");
    @Unique
    private Map<Map<Property<?>, Object>, BlockState>
    cntrafficsymbols_core$lazyStateMap;

    @ModifyExpressionValue(method = "generateStateMap", at = @At(
        value = "INVOKE", ordinal = 0,
        target = "Lcom/google/common/collect/ImmutableMap;values()Lcom/google/common/collect/ImmutableCollection;"
    ))
    private static ImmutableCollection<BlockState> skipEagerPopulate(
        ImmutableCollection<BlockState> original, BlockType blockType,
        @Local ImmutableMap<Map<Property<?>, Object>, BlockState> stateMap
    ) {
        String id = blockType.getId();
        if (WorldEditPasteWarning.CNTRAFFICSYMBOLS_NS.matcher(
            COLON.split(id, 2)[0]
        ).matches() && stateMap.size() > 1024) {
            for (BlockState i : original) {
                ((BlockStateMixin)(Object)i)
                .cntrafficsymbols_core$lazyStateMap = stateMap;
            }
            cntrafficsymbols_core$LOGGER.info(
                "[CNTrafficSymbols Core] WorldEdit: Skip populating for {} " +
                "as the property combination count {} exceeds 1024",
                id, stateMap.size()
            );
            return ImmutableList.of();
        }
        return original;
    }

    @Inject(
        method = "with(Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;",
        at = @At("HEAD"), cancellable = true
    )
    private <V> void onWith(
        final Property<V> property, final V value,
        CallbackInfoReturnable<BlockState> cir
    ) {
        if (this.states == null &&
            this.cntrafficsymbols_core$lazyStateMap != null) {
            BlockState result = this.cntrafficsymbols_core$lazyStateMap.get(
                this.withValue(property, value)
            );
            cir.setReturnValue(
                result == null ? (BlockState)(Object)this : result
            );
        }
    }
}
