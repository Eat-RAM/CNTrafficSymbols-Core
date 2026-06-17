package io.github.eat_ram.cntrafficsymbols.core.state;

import java.util.IdentityHashMap;
import java.util.Map;

import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.jetbrains.annotations.Nullable;

public abstract class StateOptimizer {
    private static final IdentityHashMap<
        Object, IdentityHashMap<State<?, ?>, Void>
    > recordList = new IdentityHashMap<>();

    public static void add(State<?, ?> state) {}

    public static <O> @Nullable State<O, ?>
    get(O owner, Map<Property<?>, Comparable<?>> ppts) {
        return null;
    }
}
