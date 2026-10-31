package io.github.eat_ram.cntrafficsymbols.core.client.helper;

import java.text.MessageFormat;

import io.github.eat_ram.cntrafficsymbols.core.helper.String2McText;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.HoverEvent.ShowText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public abstract class I18nHelper {
    public static MutableText tagTr(String key, Object @NotNull... args) {
        String g = I18n.translate(key);
        try {
            return String2McText.tagStyle(
                args.length == 0 ? g : MessageFormat.format(g, args), -1
            );
        } catch (IllegalArgumentException e) {
            return Text.literal(g).styled(style -> {
                TextColor red = TextColor.fromRgb(0xFF0000);
                MutableText tooltip = Text.empty().append(
                    Text.literal(e.getClass().getName()).withColor(red)
                );
                String message = e.getLocalizedMessage();
                if (message != null) {
                    tooltip = tooltip.append(": " + message);
                }
                return style.withColor(red)
                       .withHoverEvent(new ShowText(tooltip));
            });
        }
    }

    @Contract(value = "-> fail", pure = true)
    private I18nHelper() {
        throw new UnsupportedOperationException("Utility class");
    }
}
