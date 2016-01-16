import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gradient {
    private final List<GradientPoint> points;
//    private final DVector reference;

//    private float min, max;

    Gradient() {
        this.points = new ArrayList<GradientPoint>();
//        this.reference = new DVector();
    }

    Gradient(List<GradientPoint> points_) {
        this.points = points_;
        Collections.sort(this.points);
//        this.reference = new DVector();
    }

    public List<GradientPoint> getPoints() {
        return points;
    }

    public void add(int index, GradientPoint element) {
        points.add(index, element);
    }

    public void add(GradientPoint element) {
        points.add(element);
    }

    public GradientPoint get(int index) {
        return points.get(index);
    }

    public void clear() {
        this.points.clear();
    }
    /*Gradient(List<GradientPoint> points_, DVector reference_) {
        this.points = points_;
        Collections.sort(this.points);
        this.reference = reference_;
    }*/
}