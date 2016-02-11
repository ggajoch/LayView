package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.Color;

public class SurfacePoint {
    public Vec3d position;
    public Vec3d vector;
    public Color color;

    public SurfacePoint() {
        color = new Color(0, 0, 0, 1);
        position = new Vec3d(0, 0, 0);
        vector = new Vec3d(0, 0, 0);
    }

    public SurfacePoint(SurfacePoint point) {
        color = new Color(point.color.getRed(), point.color.getGreen(), point.color.getBlue(), point.color.getOpacity());
        position = new Vec3d(point.position);
        vector = new Vec3d(point.vector);
    }

    public SurfacePoint(Vec3d position_) {
        color = new Color(0, 0, 0, 1);
        position = new Vec3d(position_);
        vector = new Vec3d(0, 0, 0);
    }

    public SurfacePoint(Vec3d position_, Vec3d vector_) {
        color = new Color(0, 0, 0, 1);
        position = new Vec3d(position_);
        vector = new Vec3d(vector_);
    }

    public SurfacePoint(Vec3d position_, Vec3d vector_, Color color_) {
        color = new Color(color_.getRed(), color_.getGreen(), color_.getBlue(), color_.getOpacity());
        position = new Vec3d(position_);
        vector = new Vec3d(vector_);
    }
}
