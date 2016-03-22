package pl.gajoch.layview.graphics2d.options;

import java.awt.*;

public class LineOptions {
    public float width;
    public double symbolRadius;
    public int divisions;
    public boolean isLine;
    public Color color;

    public LineOptions() {
    }

    public LineOptions(final LineOptions second) {
        this.width = second.width;
        this.symbolRadius = second.symbolRadius;
        this.divisions = second.divisions;
        this.isLine = second.isLine;
        this.color = new Color(second.color.getRed(), second.color.getGreen(), second.color.getBlue(), 1);
    }
}
