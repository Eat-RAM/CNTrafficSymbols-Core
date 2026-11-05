package io.github.eat_ram.cntrafficsymbols.core.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.eat_ram.cntrafficsymbols.core.struct.DoubleInterval;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DoubleIntervalHelper {
    public static final List<DoubleInterval> FULL_MEASURE =
    Collections.singletonList(new DoubleInterval(0., 1.));

    private static final Pattern INTERVAL_PATTERN = Pattern.compile(
        "\\[?\\s*([0-9]*\\.?[0-9]+)\\s*[,\\-~]\\s*([0-9]*\\.?[0-9]+)\\s*]?"
    );
    private static final Pattern INTERVAL_DELIM =
    Pattern.compile("^\\s*,?\\s*$");

    @Contract(pure = true)
    public static double clamp01(double v) {
        return v > 0. ? (v < 1. ? v : 1.) : 0.;
    }

    public static @NotNull List<@NotNull DoubleInterval>
    fromLoopedMeasure(double start, double end) {
        start = clamp01(start);
        end = clamp01(end);
        if (start <= end) {
            return List.of(new DoubleInterval(start, end));
        }
        List<DoubleInterval> list = new ArrayList<>(2);
        list.add(new DoubleInterval(0., end));
        list.add(new DoubleInterval(start, 1.));
        return unionAll(list);
    }

    public static @NotNull List<@NotNull DoubleInterval>
    unionAll(@Nullable Collection<DoubleInterval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return Collections.emptyList();
        }
        List<DoubleInterval> sorted = new ArrayList<>(intervals);
        sorted.sort(null);
        List<DoubleInterval> result = new ArrayList<>();
        DoubleInterval current = sorted.get(0);
        int size = sorted.size();
        for (int i = 1; i < size; i++) {
            DoubleInterval next = sorted.get(i);
            DoubleInterval merged = current.tryUnion(next);
            if (merged != null) {
                current = merged;
            } else {
                result.add(current);
                current = next;
            }
        }
        result.add(current);
        if (result.size() == 1) {
            DoubleInterval single = result.get(0);
            if (single.start <= DoubleInterval.EPSILON &&
                single.end >= 1. - DoubleInterval.EPSILON) {
                return FULL_MEASURE;
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Contract(value = "null, _ -> false", pure = true)
    public static boolean contains(
        @Nullable Collection<@NotNull DoubleInterval> intervals, double time
    ) {
        if (intervals == null || intervals.isEmpty()) {
            return false;
        }
        for (DoubleInterval interval : intervals) {
            if (interval.contains(time)) {
                return true;
            }
        }
        return false;
    }

    @Contract("null -> false")
    public static boolean isFullMeasure(
        @Nullable Collection<DoubleInterval> intervals
    ) {
        if (intervals == null || intervals.isEmpty()) {
            return false;
        }
        List<DoubleInterval> merged = unionAll(intervals);
        if (merged.size() == 1) {
            DoubleInterval single = merged.get(0);
            return single.start <= DoubleInterval.EPSILON &&
                   single.end >= 1. - DoubleInterval.EPSILON;
        }
        return false;
    }

    @Contract("null -> null")
    public static @Nullable List<@NotNull DoubleInterval>
    fromString(@Nullable String str) {
        if (str == null) {
            return null;
        }
        if (str.isBlank()) {
            return Collections.emptyList();
        }
        Matcher matcher = INTERVAL_PATTERN.matcher(str);
        List<DoubleInterval> list = new ArrayList<>();
        int lastEnd = 0;
        while (matcher.find()) {
            String gap = str.substring(lastEnd, matcher.start());
            if (lastEnd == 0) {
                if (!gap.isBlank()) {
                    return null;
                }
            } else if (!INTERVAL_DELIM.matcher(gap).matches()) {
                return null;
            }
            String matchStr = matcher.group().strip();
            if (matchStr.startsWith("[") != matchStr.endsWith("]")) {
                return null;
            }
            try {
                double s = Double.parseDouble(matcher.group(1));
                double e = Double.parseDouble(matcher.group(2));
                list.addAll(fromLoopedMeasure(s, e));
            } catch (NumberFormatException ignored) {}
            lastEnd = matcher.end();
        }
        if (list.isEmpty() || !str.substring(lastEnd).isBlank()) {
            return null;
        }
        return unionAll(list);
    }

    @Contract("null -> null")
    public static @Nullable List<@NotNull DoubleInterval>
    parseString(@Nullable String str) {
        if (str == null) {
            return null;
        }
        if (str.isBlank()) {
            return Collections.emptyList();
        }
        str = str.strip();
        Matcher matcher = INTERVAL_PATTERN.matcher(str);
        List<DoubleInterval> list = new ArrayList<>();
        while (matcher.find()) {
            try {
                double s = Double.parseDouble(matcher.group(1));
                double e = Double.parseDouble(matcher.group(2));
                list.addAll(fromLoopedMeasure(s, e));
            } catch (NumberFormatException ignored) {}
        }
        return list.isEmpty() ? null : unionAll(list);
    }

    public static @NotNull String
    format(@Nullable Collection<@NotNull DoubleInterval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return "";
        }
        if (isFullMeasure(intervals)) {
            return "[0.0, 1.0]";
        }
        StringBuilder sb = new StringBuilder();
        boolean more = false;
        for (DoubleInterval in : intervals) {
            if (more) {
                sb.append(", ");
            }
            sb.append(String.format(
                Locale.ROOT, "[%.5f, %.5f]", in.start, in.end
            ));
            more = true;
        }
        return sb.toString();
    }

    @Contract(value = "-> fail", pure = true)
    private DoubleIntervalHelper() {
        throw new UnsupportedOperationException("Utility class");
    }
}
