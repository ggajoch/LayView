package pl.gajoch.layview.graphics2d;


import com.sun.javafx.geom.Vec2d;

public class PlotPoint {
    public Vec2d value;

    public PlotPoint() {
        value = new Vec2d();
    }

    public PlotPoint(Vec2d value) {
        this.value = value;
    }

    public PlotPoint(PlotPoint second) {
        this.value = new Vec2d(second.value);
    }
}
