package io.github.eat_ram.cntrafficsymbols.core.compat.worldedit.mixin;

import java.util.IdentityHashMap;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.command.ClipboardCommands;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.util.formatting.text.TextComponent;
import com.sk89q.worldedit.util.formatting.text.TranslatableComponent;
import com.sk89q.worldedit.util.formatting.text.format.TextColor;
import com.sk89q.worldedit.world.World;
import io.github.eat_ram.cntrafficsymbols.core.compat.worldedit
       .WorldEditPasteWarning;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.Block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = @Condition("worldedit"))
@Mixin(value = ClipboardCommands.class, remap = false)
public abstract class ClipboardCommandsMixin {
    @Inject(method = "paste", at = @At("HEAD"))
    private void resetCopiedBlocks(CallbackInfo ci) {
        WorldEditPasteWarning.COPIED_BLOCKS.set(new IdentityHashMap<>());
    }

    @Inject(method = "paste", at = @At("RETURN"))
    private void warnActorIfTriggered(
        Actor actor, World world, LocalSession session,
        EditSession editSession, boolean ignoreAirBlocks,
        boolean pasteStructureVoid, boolean atOrigin, boolean selectPasted,
        boolean onlySelect, boolean pasteEntities, boolean pasteBiomes,
        Mask sourceMask, CallbackInfo ci
    ) {
        IdentityHashMap<Block, Void> blocks =
        WorldEditPasteWarning.COPIED_BLOCKS.get();
        if (!blocks.isEmpty()) {
            actor.print(TextComponent.of(
                "[CNTrafficSymbols Core] WARNING: Unsupported non-orthogonal" +
                " or vertical WorldEdit transform for blocks, keeping " +
                "original state:", TextColor.YELLOW
            ));
            for (Block block : blocks.keySet()) {
                actor.print(TranslatableComponent.of(
                    block.getTranslationKey(), TextColor.YELLOW
                ));
            }
            WorldEditPasteWarning.COPIED_BLOCKS.set(new IdentityHashMap<>());
        }
    }
}
