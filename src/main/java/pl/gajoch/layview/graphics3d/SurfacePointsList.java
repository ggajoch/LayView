package pl.gajoch.layview.graphics3d;

import java.util.ArrayList;
import java.util.Collection;

public class SurfacePointsList {
    public ArrayList<SurfacePoint> points;
    public SurfacePoint min, max;

    public SurfacePointsList() {
        points = new ArrayList<>();
        min = new SurfacePoint();
        max = new SurfacePoint();
    }

    public void add(SurfacePoint point) {
        if (points.isEmpty()) {
            max = new SurfacePoint(point);
            min = new SurfacePoint(point);
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

    public void addAll(Collection<? extends SurfacePoint> collection) {
        for(SurfacePoint surfacePoint : collection) {
            this.add(surfacePoint);
        }
    }
}
