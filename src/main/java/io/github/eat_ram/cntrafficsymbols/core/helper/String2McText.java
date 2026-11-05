package io.github.eat_ram.cntrafficsymbols.core.helper;

import java.util.ArrayList;
import java.util.Locale;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class String2McText {
    public static final int ALLOW_NAMED_COLOR = 1;
    public static final int ALLOW_HEX_COLOR = 2;
    public static final int ALLOW_BOLD = 4;
    public static final int ALLOW_ITALIC = 8;
    public static final int ALLOW_UNDERLINE = 16;
    public static final int ALLOW_STRIKETHROUGH = 32;
    public static final int ALLOW_OBFUSCATED = 64;
    public static final int ALLOW_NOSHADOW = 128;
    public static final int ALLOW_CUSTOM_SHADOW = 256;

    public static MutableText
    tryApplyStyle(MutableText text, @NotNull String styleName, int flags) {
        if ((flags & ALLOW_NAMED_COLOR) == ALLOW_NAMED_COLOR) {
            Formatting color;
            switch (styleName) {
                case "black": color = Formatting.BLACK; break;
                case "dark_blue": color = Formatting.DARK_BLUE; break;
                case "dark_green": color = Formatting.DARK_GREEN; break;
                case "dark_aqua": color = Formatting.DARK_AQUA; break;
                case "dark_red": color = Formatting.DARK_RED; break;
                case "dark_purple": color = Formatting.DARK_PURPLE; break;
                case "gold": color = Formatting.GOLD; break;
                case "gray": color = Formatting.GRAY; break;
                case "dark_gray": color = Formatting.DARK_GRAY; break;
                case "blue": color = Formatting.BLUE; break;
                case "green": color = Formatting.GREEN; break;
                case "aqua": color = Formatting.AQUA; break;
                case "red": color = Formatting.RED; break;
                case "light_purple": color = Formatting.LIGHT_PURPLE; break;
                case "yellow": color = Formatting.YELLOW; break;
                case "white": color = Formatting.WHITE; break;
                default: color = null;
            }
            if (color != null) {
                return text.formatted(color);
            }
        }
        if ((flags & ALLOW_HEX_COLOR) == ALLOW_HEX_COLOR &&
            styleName.startsWith("#")) {
            try {
                return text.withColor(
                    Integer.parseInt(styleName.substring(1), 16)
                );
            } catch (NumberFormatException ignored) {}
        }
        if ((flags & ALLOW_BOLD) == ALLOW_BOLD &&
            ("bold".equals(styleName) || "b".equals(styleName))) {
            return text.styled(style -> style.withBold(true));
        }
        if ((flags & ALLOW_ITALIC) == ALLOW_ITALIC &&
            ("italic".equals(styleName) || "i".equals(styleName))) {
            return text.styled(style -> style.withItalic(true));
        }
        if ((flags & ALLOW_UNDERLINE) == ALLOW_UNDERLINE &&
            ("underline".equals(styleName) || "u".equals(styleName))) {
            return text.styled(style -> style.withUnderline(true));
        }
        if ((flags & ALLOW_STRIKETHROUGH) == ALLOW_STRIKETHROUGH &&
            ("strikethrough".equals(styleName) || "d".equals(styleName))) {
            return text.styled(style -> style.withStrikethrough(true));
        }
        if ((flags & ALLOW_OBFUSCATED) == ALLOW_OBFUSCATED &&
            "obfuscated".equals(styleName)) {
            return text.styled(style -> style.withObfuscated(true));
        }
        if ((flags & ALLOW_NOSHADOW) == ALLOW_NOSHADOW &&
            "noshadow".equals(styleName)) {
            return text; // 1.20.4 does not support shadow customization
        }
        if ((flags & ALLOW_CUSTOM_SHADOW) == ALLOW_CUSTOM_SHADOW &&
            styleName.startsWith("shadow:#")) {
            return text; // 1.20.4 does not support shadow customization
        }
        return text;
    }

    public static MutableText tagStyle(@NotNull String str, int flags) {
        MutableText root = Text.empty();
        ArrayList<MutableText> textStack = new ArrayList<>();
        ArrayList<String> tagStack = new ArrayList<>();
        boolean escaped = false;
        boolean inTag = false;
        StringBuilder toAppend = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (escaped) {
                toAppend.append(c);
                escaped = false;
            } else {
                switch (c) {
                    case '\\':
                        escaped = true;
                        break;
                    case '<':
                        if (inTag) {
                            toAppend.append(c);
                        } else {
                            if (!toAppend.isEmpty()) {
                                String g = toAppend.toString();
                                if (textStack.isEmpty()) {
                                    root = root.append(Text.literal(g));
                                } else {
                                    int idx = textStack.size() - 1;
                                    textStack
                                    .set(idx, textStack.get(idx).append(
                                        Text.literal(g)
                                    ));
                                }
                                toAppend = new StringBuilder();
                            }
                            inTag = true;
                        }
                        break;
                    case '>':
                        if (inTag) {
                            inTag = false;
                            String tag = toAppend.toString().trim()
                                         .toLowerCase(Locale.ROOT);
                            toAppend = new StringBuilder();
                            if ("/".equals(tag)) {
                                if (!textStack.isEmpty()) {
                                    tagStack.remove(tagStack.size() - 1);
                                    MutableText popped =
                                    textStack.remove(textStack.size() - 1);
                                    if (textStack.isEmpty()) {
                                        root = root.append(popped);
                                    } else {
                                        int idx = textStack.size() - 1;
                                        textStack.set(
                                            idx,
                                            textStack.get(idx).append(popped)
                                        );
                                    }
                                }
                            } else {
                                tagStack.add(tag);
                                textStack.add(tryApplyStyle(
                                    Text.empty(), tag, flags
                                ));
                            }
                        }
                        break;
                    default:
                        toAppend.append(c);
                }
            }
        }
        if (!toAppend.isEmpty()) {
            String g = toAppend.toString();
            if (textStack.isEmpty()) {
                root = root.append(Text.literal(g));
            } else {
                int idx = textStack.size() - 1;
                textStack.set(idx, textStack.get(idx).append(Text.literal(g)));
            }
        }
        while (!textStack.isEmpty()) {
            tagStack.remove(tagStack.size() - 1);
            MutableText popped = textStack.remove(textStack.size() - 1);
            if (textStack.isEmpty()) {
                root = root.append(popped);
            } else {
                int idx = textStack.size() - 1;
                textStack.set(idx, textStack.get(idx).append(popped));
            }
        }
        return root;
    }

    @Contract(value = "-> fail", pure = true)
    private String2McText() {
        throw new UnsupportedOperationException("Utility class");
    }
}
