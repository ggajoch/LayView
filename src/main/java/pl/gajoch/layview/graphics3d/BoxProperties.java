package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;

public class BoxProperties {
    public Vec3d dimensions;

    public BoxProperties(Vec3d dimensions) {
        this.dimensions = dimensions;
    }

    public BoxProperties() {
        this(new Vec3d(10, 10, 10));
    }
}