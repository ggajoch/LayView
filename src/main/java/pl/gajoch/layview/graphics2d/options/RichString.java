package pl.gajoch.layview.graphics2d.options;

import java.awt.*;

public class RichString {
    public String text;
    public int size;
    public Color color;

    public RichString() {
    }

    public RichString(String text, int size, Color color) {
        this.text = text;
        this.size = size;
        this.color = color;
    }

    public RichString(RichString rhs) {
        this.text = rhs.text;
        this.size = rhs.size;
        this.color = new Color(rhs.color.getRed(),
                rhs.color.getGreen(),
                rhs.color.getBlue(),
                1);
    }
}
