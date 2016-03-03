package pl.gajoch.layview.graphics2d;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Piotr on 03/03/2016.
 */
public class PlotPointsList {
    public ArrayList<PlotPoint> points;
    public PlotPoint min, max;

    public PlotPointsList() {
        points = new ArrayList<>();
        min = new PlotPoint();
        max = new PlotPoint();
    }

    public void add(PlotPoint point) {
        if (points.isEmpty()) {
            max = new PlotPoint(point);
            min = new PlotPoint(point);
        } else {
            max.value.x = Math.max(max.value.x, point.value.x);
            max.value.y = Math.max(max.value.y, point.value.y);

            min.value.x = Math.min(min.value.x, point.value.x);
            min.value.y = Math.min(min.value.y, point.value.y);
        }
        points.add(point);
    }

    public void addAll(Collection<? extends PlotPoint> collection) {
        collection.forEach(this::add);
    }

    public void clear() {
        this.points.clear();
    }
}
