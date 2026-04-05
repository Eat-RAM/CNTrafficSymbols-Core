package io.github.eat_ram.cntrafficsymbols.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(net.minecraft.state.State.class)
public interface StateAccessor<O, S> {
    @Accessor("owner")
    public O getOwner();
}
