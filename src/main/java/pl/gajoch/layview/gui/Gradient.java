package pl.gajoch.layview.gui;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

import java.util.*;
import java.util.stream.Collectors;

public class Gradient {
    private final SortedSet<GradientPoint> points;
    private final Vec3d reference;
    private double min;
    private double max;

    Gradient() {
        this.points = new TreeSet<>();
        this.reference = new Vec3d();
        min = max = 0;
    }

    Gradient(Gradient second) {
        this.points = new TreeSet<>(second.points);
        this.reference = new Vec3d(second.reference);
        this.min = second.min;
        this.max = second.max;
    }

    public void setReference(Vec3d vector) {
        reference.set(vector.x, vector.y, vector.z);
    }

    public Vec3d getReference() {
        return reference;
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


    Gradient(TreeSet<GradientPoint> points, Vec3d reference, double min, double max) {
        this.points = points;
        this.reference = reference;
        this.min = min;
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
}