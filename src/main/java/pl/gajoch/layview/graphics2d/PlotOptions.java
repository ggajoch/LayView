package pl.gajoch.layview.graphics2d;

import javafx.scene.paint.Color;

public class PlotOptions {
    public float width;
    public float symbolRadius;
    public boolean isLine;
    public Color color;

    public PlotOptions() {
        width = 3;
        symbolRadius = 1;
        isLine = true;
        color = Color.GREEN;
    }
}
