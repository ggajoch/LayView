package pl.gajoch.layview.graphics3d;

public class VectorProperties {
    public double tipLen, tipRadius, radius, lenScale;
    public int divisions;

    public VectorProperties(double tipLen, double tipRadius, double radius, double lenScale, int divisions) {
        this.tipLen = tipLen;
        this.tipRadius = tipRadius;
        this.radius = radius;
        this.lenScale = lenScale;
        this.divisions = divisions;
    }

    public VectorProperties(double tipLen, double tipRadius, double radius, double lenScale) {
        this(tipLen, tipRadius, radius, lenScale, 4);
    }


    public VectorProperties() {
        this(0.025, 0.025, 0.01, 0.1);
    }

    public VectorProperties(VectorProperties second) {
        this.tipLen = second.tipLen;
        this.tipRadius = second.tipRadius;
        this.radius = second.radius;
        this.lenScale = second.lenScale;
        this.divisions = second.divisions;
    }
}