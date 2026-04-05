package io.github.eat_ram.cntrafficsymbols.core.state;

import java.util.IdentityHashMap;
import java.util.Map;

import io.github.eat_ram.cntrafficsymbols.core.mixin.StateAccessor;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.jetbrains.annotations.Nullable;

public abstract class StateOptimizer {
    private static final IdentityHashMap<
        Object, IdentityHashMap<State<?, ?>, Void>
    > recordList = new IdentityHashMap<>();

    public static void add(State<?, ?> state) {
        Object o = ((StateAccessor<?, ?>)state).getOwner();
        if (!recordList.containsKey(o)) {
            recordList.put(o, new IdentityHashMap<>());
        }
        recordList.get(o).put(state, null);
    }

    @SuppressWarnings("unchecked")
    public static <O> @Nullable State<O, ?>
    get(O owner, Map<Property<?>, Comparable<?>> ppts) {
        if (!recordList.containsKey(owner)) {
            return null;
        }
        for (State<?, ?> i : recordList.get(owner).keySet()) {
            if (i.getEntries().equals(ppts)) {
                return (State<O, ?>) i;
            }
        }
        return null;
    }
}
