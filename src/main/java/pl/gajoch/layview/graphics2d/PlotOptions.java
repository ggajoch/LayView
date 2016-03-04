package pl.gajoch.layview.graphics2d;

import javafx.scene.paint.Color;

public class PlotOptions {
    public float width;
    public double symbolRadius;
    public boolean isLine;
    public Color color;
    public int divisions;

    public PlotOptions() {
        width = 3;
        symbolRadius = 0.05;
        isLine = true;
        color = Color.GREEN;
        divisions = 10;
    }
}
