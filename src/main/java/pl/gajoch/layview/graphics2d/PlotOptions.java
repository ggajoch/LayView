package pl.gajoch.layview.graphics2d;

import javafx.scene.paint.Color;

public class PlotOptions {
    public float width;

    public double symbolRadius;
    public int divisions;

    public boolean isLine;

    public Color color;

    public String xLabel;
    public String yLabel;

    public boolean showGrid;

    public PlotOptions() {
        width = 3;
        symbolRadius = 0.05;
        isLine = true;
        color = Color.GREEN;
        divisions = 10;
        xLabel = new String("X");
        yLabel = new String("Y");
        showGrid = true;
    }
}
