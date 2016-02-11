package moleculesampleapp;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.Color;

/**
 * Created by Piotr on 11/02/2016.
 */
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
