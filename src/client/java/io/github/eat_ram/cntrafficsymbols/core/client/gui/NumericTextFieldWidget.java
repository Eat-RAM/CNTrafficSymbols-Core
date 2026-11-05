package io.github.eat_ram.cntrafficsymbols.core.client.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class NumericTextFieldWidget extends TextFieldWidget {
    public final boolean allowDecimal;
    public final boolean allowNegative;

    public NumericTextFieldWidget(
        TextRenderer textRenderer, int x, int y, int width, int height,
        Text text, boolean allowDecimal, boolean allowNegative
    ) {
        super(textRenderer, x, y, width, height, text);
        this.allowDecimal = allowDecimal;
        this.allowNegative = allowNegative;
    }

    public NumericTextFieldWidget(
        TextRenderer textRenderer, int x, int y, int width, int height,
        Text text, boolean allowDecimal
    ) {
        this(textRenderer, x, y, width, height, text, allowDecimal, false);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!this.isActive()) {
            return false;
        }
        if (chr >= '0' && chr <= '9') {
            return super.charTyped(chr, modifiers);
        }
        if (this.allowDecimal && chr == '.') {
            if (!this.getText().contains(".") ||
                this.getSelectedText().contains(".")) {
                return super.charTyped(chr, modifiers);
            }
        }
        if (this.allowNegative && chr == '-' &&
            (this.getText().isEmpty() ||
             this.getSelectedText().contains("-"))) {
            return super.charTyped(chr, modifiers);
        }
        return false;
    }

    @Override
    public void write(String string) {
        StringBuilder sb = new StringBuilder();
        boolean hasDot =
        this.getText().contains(".") && !this.getSelectedText().contains(".");
        boolean hasHyphen =
        this.getText().contains("-") && !this.getSelectedText().contains("-");
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else if (this.allowDecimal && c == '.' && !hasDot) {
                sb.append('.');
                hasDot = true;
            } else if (this.allowNegative && c == '-' && !hasHyphen) {
                sb.append('-');
                hasHyphen = true;
            }
        }
        if (!sb.isEmpty()) {
            super.write(sb.toString());
        }
    }
}
