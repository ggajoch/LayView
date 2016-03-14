package pl.gajoch.layview.graphics2d;

import javafx.scene.paint.Color;

/**
 * Created by Piotr on 14/03/2016.
 */
public class AxisOptions {
    public String label;
    public double labelSize;
    public Color labelColor;

    public double min;
    public double max;
    public double tickIncrement;

    public double numberSize;

    public boolean drawGrids;

    public double frameWidth;
    public Color frameColor;

    public double tickWidth;
    public Color tickColor;


    public AxisOptions(AxisOptions second){
        this.label = new String(second.label);
        this.labelSize = second.labelSize;

        this.labelColor = new Color(second.labelColor.getRed(),
                second.labelColor.getGreen(),
                second.labelColor.getBlue(),
                1);
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
    }

    public AxisOptions(){
        this.label = new String();
        this.labelSize = 10;

        this.labelColor = Color.RED;
        this.min = -1;
        this.max = 1;

        this.tickIncrement = 0.1;
        this.numberSize = 5;

        this.drawGrids = true;

        this.frameWidth = 2;
        this.frameColor = Color.GRAY;

        this.tickWidth = 1;
        this.tickColor = Color.LIGHTGRAY;

    }

}
