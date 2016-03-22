package pl.gajoch.layview.graphics2d.options;

import java.awt.*;

public class TitleOptions {
    public String title;
    public int size;
    public Color color;

    public TitleOptions() {
    }

    public TitleOptions(TitleOptions second) {
        this.title = new String(second.title);
        this.size = second.size;
        this.color = new Color(second.color.getRed(), second.color.getGreen(), second.color.getBlue(), 1);
    }
}
