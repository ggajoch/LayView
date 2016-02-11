package moleculesampleapp;

import java.util.ArrayList;

/**
 * Created by Piotr on 11/02/2016.
 */
public class SurfacePointsList {
    public ArrayList<VectorPoint> points;
    public VectorPoint min, max;

    SurfacePointsList() {
        points = new ArrayList<>();
        min = new VectorPoint();
        max = new VectorPoint();
    }

    public void add(VectorPoint point) {
        if (points.isEmpty()) {
            max = new VectorPoint(point);
            min = new VectorPoint(point);
        } else {
            max.position.x = Math.max(max.position.x, point.position.x);
            max.position.y = Math.max(max.position.y, point.position.y);
            max.position.z = Math.max(max.position.z, point.position.z);

            max.vector.x = Math.max(max.vector.x, point.vector.x);
            max.vector.y = Math.max(max.vector.y, point.vector.y);
            max.vector.z = Math.max(max.vector.z, point.vector.z);
        }
        points.add(point);
    }
}
