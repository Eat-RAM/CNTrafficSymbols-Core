package io.github.eat_ram.cntrafficsymbols.core.mixin;

import java.util.*;

import com.google.common.collect.Table;
import io.github.eat_ram.cntrafficsymbols.core.state.StateOptimizable;
import io.github.eat_ram.cntrafficsymbols.core.state.StateOptimizer;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.state.State.class)
public abstract class StateMixin<O, S> {
    @Shadow
    @Final
    protected O owner;

    @Shadow
    private Table<Property<?>, Comparable<?>, S> withTable;

    @Inject(method = "createWithTable", at = @At("HEAD"), cancellable = true)
    private void injectCreateWithTable(CallbackInfo info) {
        if (this.owner instanceof StateOptimizable so &&
            so.stateOptimizationEnabled()) {
            State<?, ?> TH = (State<?, ?>)(Object)this;
            StateOptimizer.add(TH);
            this.withTable = new Table<>() {
                @Override
                @Contract(value = "_, _ -> true", pure = true)
                public boolean contains(Object rowKey, Object columnKey) {
                    return true;
                }

                @Override
                @Contract(value = "_ -> true", pure = true)
                public boolean containsRow(Object rowKey) {
                    return true;
                }

                @Override
                @Contract(value = "_ -> true", pure = true)
                public boolean containsColumn(Object columnKey) {
                    return true;
                }

                @Override
                @Contract(value = "_ -> true", pure = true)
                public boolean containsValue(Object value) {
                    return true;
                }

                @Override
                @SuppressWarnings("unchecked")
                public S get(Object rowKey, Object columnKey) {
                    HashMap<Property<?>, Comparable<?>> HM =
                    new HashMap<>(TH.getEntries());
                    HM.put((Property<?>) rowKey, (Comparable<?>) columnKey);
                    return (S)StateOptimizer.get(
                        ((StateAccessor<?, ?>)TH).getOwner(), HM
                    );
                }

                @Override
                @Contract(value = "-> false", pure = true)
                public boolean isEmpty() {
                    return false;
                }

                @Override
                @Contract(pure = true)
                public @Range(from = 1, to = 1) int size() {
                    return 1;
                }

                @Override
                @Contract(pure = true)
                public void clear() {}

                @Override
                @Contract(value = "_, _, _ -> null", pure = true)
                public S
                put(Property<?> rowKey, Comparable<?> columnKey, S value) {
                    return null;
                }

                @Override
                @Contract(pure = true)
                public void putAll(@NotNull Table<
                    ? extends Property<?>, ? extends Comparable<?>, ? extends S
                > table) {}

                @Override
                @Contract(value = "_, _ -> null", pure = true)
                public S remove(Object rowKey, Object columnKey) {
                    return null;
                }

                @Override
                public @NotNull Map<Comparable<?>, S> row(Property<?> rowKey) {
                    return Map.of();
                }

                @Override
                public @NotNull Map<Property<?>, S>
                column(Comparable<?> columnKey) {
                    return Map.of();
                }

                @Override
                public @NotNull Set<Cell<Property<?>, Comparable<?>, S>>
                cellSet() {
                    return Set.of();
                }

                @Override
                public @NotNull Set<Property<?>> rowKeySet() {
                    return Set.of();
                }

                @Override
                public @NotNull Set<Comparable<?>> columnKeySet() {
                    return Set.of();
                }

                @Override
                public @NotNull Collection<S> values() {
                    return List.of();
                }

                @Override
                public @NotNull Map<Property<?>, Map<Comparable<?>, S>>
                rowMap() {
                    return Map.of();
                }

                @Override
                public @NotNull Map<Comparable<?>, Map<Property<?>, S>>
                columnMap() {
                    return Map.of();
                }
            };
            info.cancel();
        }
    }
}