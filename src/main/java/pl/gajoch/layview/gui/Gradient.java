package pl.gajoch.layview.gui;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gradient {
    private final List<GradientPoint> points;
    private final Vec3d reference;
    private double min, max;

    Gradient() {
        this.points = new ArrayList<GradientPoint>();
        this.reference = new Vec3d();
    }

    Gradient(Gradient second) {
        this.points = new ArrayList<>(second.points);
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

    Gradient(List<GradientPoint> points, Vec3d reference, double min, double max) {
        this.points = points;

        Collections.sort(this.points);
        this.reference = reference;
        this.min = min;
        this.max = max;
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

}