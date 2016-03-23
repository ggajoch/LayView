package pl.gajoch.layview.graphics2d.options;


import java.awt.*;

public class AxisOptions {
    public RichString label;

    public double min;
    public double max;
    public double tickIncrement;

    public int numberSize;

    public boolean drawGrids;

    public float frameWidth;
    public Color frameColor;

    public float tickWidth;
    public Color tickColor;
    public double tickLength;


    public AxisOptions(AxisOptions second) {
        this.label = new RichString(second.label);
        this.min = second.min;
        this.max = second.max;

        this.tickIncrement = second.tickIncrement;
        this.numberSize = second.numberSize;

        this.drawGrids = second.drawGrids;

        this.frameWidth = second.frameWidth;
        this.frameColor = new Color(second.frameColor.getRed(),
                second.frameColor.getGreen(),
                second.frameColor.getBlue(),
                1);

        this.tickWidth = second.tickWidth;
        this.tickColor = new Color(second.tickColor.getRed(),
                second.tickColor.getGreen(),
                second.tickColor.getBlue(),
                1);
        this.tickLength = second.tickLength;
    }

    public AxisOptions() {
        this.label = new RichString("", 30, Color.RED);
        this.min = -100;
        this.max = 100;

        this.tickIncrement = 20;
        this.numberSize = 25;

        this.drawGrids = true;

        this.frameWidth = 2;
        this.frameColor = Color.GRAY;

        this.tickWidth = 1;
        this.tickLength = 5;
        this.tickColor = Color.BLUE;

    }

}
