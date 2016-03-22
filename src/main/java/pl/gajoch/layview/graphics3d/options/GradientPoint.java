package pl.gajoch.layview.graphics3d.options;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

public class GradientPoint implements Comparable<GradientPoint>, Cloneable {

    // -------------------------- Private variables  -------------------------
    private Stop point;

    // ----------------------------- Public API  -----------------------------
    public GradientPoint() {
        this.point = new Stop(0.0, Color.BLACK);
    }

    public GradientPoint(GradientPoint second) {
        this.point = new Stop(second.point.getOffset(), second.point.getColor());
    }

    public GradientPoint(Stop stop) {
        this.point = stop;
    }

    public GradientPoint(double value, Color color) {
        this.point = new Stop(value, color);
    }

    public final Stop getStop() {
        return point;
    }

    public final double getOffset() {
        return point.getOffset();
    }

    public final Color getColor() {
        return point.getColor();
    }

    public int compareTo(GradientPoint rhs) {
        Double valThis = this.getStop().getOffset(),
                valRhs = rhs.getStop().getOffset();
        return (valThis.compareTo(valRhs));
    }

    public String toString() {
        return Double.toString(this.point.getOffset());
    }
}
