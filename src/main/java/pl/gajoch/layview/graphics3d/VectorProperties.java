package pl.gajoch.layview.graphics3d;

import javafx.scene.paint.Color;

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
        this(tipLen, tipRadius, radius, lenScale, SpecularColor, 3);
    }

    public VectorProperties(double tipLen, double tipRadius, double radius, double lenScale) {
        this(tipLen, tipRadius, radius, lenScale, Color.BLACK);
    }

    public VectorProperties() {
        this(2, 2, 1, 1);
    }

    public VectorProperties(VectorProperties second) {
        this.tipLen = second.tipLen;
        this.tipRadius = second.tipRadius;
        this.radius = second.radius;
        this.SpecularColor = new Color(second.SpecularColor.getRed(),
                second.SpecularColor.getGreen(),
                second.SpecularColor.getBlue(),
                second.SpecularColor.getOpacity());
        this.lenScale = second.lenScale;
        this.divisions = second.divisions;
    }
}
