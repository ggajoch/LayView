package pl.gajoch.layview.graphics2d;


import java.awt.geom.Point2D;

public class PlotPoint {
    private final Point2D value;

    public final Point2D get() {
        return value;
    }

    public double getY() {
        return value.getY();
    }

    public double getX() {
        return value.getX();
    }

    public PlotPoint() {
        value = new Point2D.Double();
    }

    public PlotPoint(Point2D value) {
        this.value = value;
    }

    public PlotPoint(PlotPoint second) {
        this.value = new Point2D.Double(second.getX(), second.getY());
    }
}
