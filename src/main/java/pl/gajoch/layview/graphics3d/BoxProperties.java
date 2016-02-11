package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.Color;

public class BoxProperties {
    public Color SpecularColor;
    public Vec3d dimensions;

    public BoxProperties(Vec3d dimensions, Color SpecularColor) {
        this.SpecularColor = SpecularColor;
        this.dimensions = dimensions;
    }

    public BoxProperties(Vec3d dimensions) {
        this(dimensions, Color.BLACK);
    }

    public BoxProperties() {
        this(new Vec3d(10, 10, 10));
    }
}
