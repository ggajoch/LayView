package moleculesampleapp;

import java.util.ArrayList;

/**
 * Created by Piotr on 11/02/2016.
 */
public class SurfacePointsList {
    public ArrayList<VectorPoint> points;
    public VectorPoint minPos, maxPos;

    SurfacePointsList() {
        points = new ArrayList<>();
        minPos = new VectorPoint();
        maxPos = new VectorPoint();
    }

    public void add(VectorPoint point) {
        if (points.isEmpty()) {
            maxPos = new VectorPoint(point);
            minPos = new VectorPoint(point);
        } else {
            maxPos.position.x = Math.max(maxPos.position.x, point.position.x);
            maxPos.position.y = Math.max(maxPos.position.y, point.position.y);
            maxPos.position.z = Math.max(maxPos.position.z, point.position.z);

            if (maxPos.vector.x < point.vector.x) maxPos.vector.x = point.vector.x;
            if (maxPos.vector.y < point.vector.y) maxPos.vector.y = point.vector.y;
            if (maxPos.vector.z < point.vector.z) maxPos.vector.z = point.vector.z;
        }
        points.add(point);
    }
}
