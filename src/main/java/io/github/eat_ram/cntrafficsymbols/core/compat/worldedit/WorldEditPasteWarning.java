package io.github.eat_ram.cntrafficsymbols.core.compat.worldedit;

import java.util.IdentityHashMap;
import java.util.regex.Pattern;

import net.minecraft.block.Block;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class WorldEditPasteWarning {
    public static final Pattern CNTRAFFICSYMBOLS_NS =
    Pattern.compile("cntrafficsymbols_\\dd\\d");
    public static final
    ThreadLocal<@NotNull IdentityHashMap<@NotNull Block, Void>>
    COPIED_BLOCKS = ThreadLocal.withInitial(IdentityHashMap::new);

    @Contract("-> fail")
    private WorldEditPasteWarning() {
        throw new UnsupportedOperationException();
    }
}