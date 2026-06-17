package io.github.eat_ram.cntrafficsymbols.core.state;

public interface StateOptimizable {
    public default boolean stateOptimizationEnabled() {
        return true;
    }
}
