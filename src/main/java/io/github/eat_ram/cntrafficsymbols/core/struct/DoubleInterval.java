package io.github.eat_ram.cntrafficsymbols.core.struct;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DoubleInterval implements Comparable<DoubleInterval> {
    public static final double EPSILON = 1e-7;
    public static final Codec<DoubleInterval> CODEC =
    Codec.DOUBLE.listOf().comapFlatMap(list -> {
        if (list.size() < 2) {
            return DataResult.error(
                () -> "DoubleInterval list must have size 2: " + list
            );
        }
        double s = list.get(0);
        double e = list.get(1);
        return s <= e ? DataResult.success(new DoubleInterval(s, e)) :
               DataResult.error(
            () -> "start cannot be greater than end: " + s + " > " + e
        );
    }, interval -> List.of(interval.start, interval.end));
    public static final Codec<List<DoubleInterval>> LIST_CODEC =
    CODEC.listOf();

    public final double start;
    public final double end;

    @Contract(pure = true)
    public DoubleInterval(double start, double end) {
        if (!(start <= end)) {
            throw new IllegalArgumentException(
                "start cannot be greater than end: " + start + " > " + end
            );
        }
        this.start = start;
        this.end = end;
    }

    @Contract(pure = true)
    public double getStart() {
        return this.start;
    }

    @Contract(pure = true)
    public double getEnd() {
        return this.end;
    }

    @Contract(pure = true)
    public double length() {
        return this.end - this.start;
    }

    @Contract(pure = true)
    public boolean contains(double v) {
        return v >= this.start && v <= this.end;
    }

    @Contract(pure = true)
    public boolean connects(@NotNull DoubleInterval other) {
        return Math.max(this.start, other.start) <=
               Math.min(this.end, other.end) + EPSILON;
    }

    @Contract(pure = true)
    public @Nullable DoubleInterval tryUnion(@NotNull DoubleInterval other) {
        return this.connects(other) ? new DoubleInterval(
            Math.min(this.start, other.start), Math.max(this.end, other.end)
        ) : null;
    }

    @Contract(pure = true)
    public @Nullable DoubleInterval
    intersection(@NotNull DoubleInterval other) {
        return this.connects(other) ? new DoubleInterval(
            Math.max(this.start, other.start), Math.min(this.end, other.end)
        ) : null;
    }

    @Override
    @Contract(pure = true)
    public int compareTo(@NotNull DoubleInterval other) {
        int c = Double.compare(this.start, other.start);
        return c != 0 ? c : Double.compare(this.end, other.end);
    }

    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object o) {
        return this == o || (
            o instanceof DoubleInterval that &&
            Double.compare(that.start, this.start) == 0 &&
            Double.compare(that.end, this.end) == 0
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.end);
    }

    @Override
    @Contract(pure = true)
    public @NotNull String toString() {
        return String.format(
            Locale.ROOT, "[%.5f, %.5f]", this.start, this.end
        );
    }
}
