package moleculesampleapp;

import javafx.scene.paint.Color;

/**
 * Created by Piotr on 10/02/2016.
 */
public class VectorProperties {
    public double tipLen, tipRadius, radius, lenScale;
    public Color SpecularColor;
    public int divisions;

    public VectorProperties(double tipLen, double tipRadius, double radius, double lenScale, Color SpecularColor, int divisions) {
        this.tipLen = tipLen;
        this.tipRadius = tipRadius;
        this.radius = radius;
        this.SpecularColor = SpecularColor;
        this.lenScale = lenScale;
        this.divisions = divisions;
    }

    public VectorProperties(double tipLen, double tipRadius, double radius, double lenScale, Color SpecularColor) {
        this(tipLen, tipRadius, radius, lenScale, SpecularColor, 64);
    }

    public VectorProperties(double tipLen, double tipRadius, double radius, double lenScale) {
        this(tipLen, tipRadius, radius, lenScale, Color.BLACK);
    }

    public VectorProperties() {
        this(2, 2, 1, 1);
    }
}
