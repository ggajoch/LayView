package pl.gajoch.layview.utils;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.sun.istack.internal.NotNull;

import java.awt.*;

public class RichTextRenderer extends TextRenderer {
    public RichTextRenderer(@NotNull Font font) {
        super(font);
    }

    public double getStringWidth(String string) {
        double width = 0;

        for (int i = 0, n = string.length(); i < n; i++) {
            width += this.getCharWidth(string.charAt(i));
        }

        return width;
    }
}
