package pl.gajoch.layview.graphics3d.options;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.*;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Gradient {
    private final SortedSet<GradientPoint> points;
    private final Vec3d reference;
    private double min;
    private double max;

    public Gradient() {
        this.points = new TreeSet<>();
        this.reference = new Vec3d();
        min = max = 0;
    }

    public Gradient(Gradient second) {
        this.points = new TreeSet<>(second.points);
        this.reference = new Vec3d(second.reference);
        this.min = second.min;
        this.max = second.max;
    }

    Gradient(TreeSet<GradientPoint> points, Vec3d reference, double min, double max) {
        this.points = points;
        this.reference = reference;
        this.min = min;
        this.max = max;
    }

    public Vec3d getReference() {
        return reference;
    }

    public void setReference(Vec3d vector) {
        reference.set(vector.x, vector.y, vector.z);
    }

    public double getMinVector() {
        return min;
    }

    public void setMinVector(double min) {
        this.min = min;
    }

    public double getMaxVector() {
        return max;
    }

    public void setMaxVector(double max) {
        this.max = max;
    }

    public SortedSet<GradientPoint> getPoints() {
        return points;
    }

    public void add(GradientPoint element) {
        points.add(element);
    }

    public void clear() {
        this.points.clear();
    }


    public Paint getPaint() {
        if (points.size() == 0) {
            return new Color(0, 0, 0, 1);
        }
        if (points.size() == 1) {
            return points.first().getColor();
        }

        double mini = points.first().getOffset();
        double maxi = points.last().getOffset();

        List<Stop> list = points.stream()
                .map(point -> new Stop((point.getOffset() - mini) / (maxi - mini), point.getColor()))
                .collect(Collectors.toList());

        return new LinearGradient(0, 0, 0, 1.0, true, CycleMethod.REPEAT, list);
    }

    public boolean isValidStrict() {
        return this.isValid() && this.min != this.max;
    }

    public boolean isValid() {
        return this.points.size() >= 2 && this.reference.length() > 0;
    }
}