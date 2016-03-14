package pl.gajoch.layview.graphics2d;

import javafx.scene.paint.Color;

public class PlotOptions {
    public float width;

    public double symbolRadius;
    public int divisions;

    public boolean isLine;

    public Color color;

    public AxisOptions xAxisOptions;
    public AxisOptions yAxisOptions;

    public PlotOptions(PlotOptions second){
        this.width = second.width;
        this.symbolRadius = second.symbolRadius;

        this.isLine = second.isLine;
        this.color = new Color(second.color.getRed(),second.color.getGreen(),second.color.getBlue(), 1);

        this.xAxisOptions = new AxisOptions(second.xAxisOptions);
        this.yAxisOptions = new AxisOptions(second.yAxisOptions);
    }

    public PlotOptions() {
        width = 3;
        symbolRadius = 5;
        isLine = true;
        color = Color.GREEN;
        divisions = 10;
        xAxisOptions = new AxisOptions();
        xAxisOptions.label = new String("X");

        yAxisOptions = new AxisOptions();
        yAxisOptions.label = new String("Y");
    }
}
