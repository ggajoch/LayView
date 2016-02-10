package moleculesampleapp;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

/**
 * Created by Piotr on 06/02/2016.
 */
public class VectorSurface extends BoxSurface {
    private double lenScale, tipRadius, tipLen, radius;

    public VectorSurface() {
        this(new Vec3d(10, 10, 10), 1.0, 1, 1.5, 2, 1.0);
    }

    public VectorSurface(Vec3d pitch_, double pitchScale_) {
        this(pitch_, pitchScale_, 2, 5, 5, 1.0);
    }

    public VectorSurface(Vec3d pitch_, double pitchScale_, double radius_, double tipRadius_, double tipLen_, double lenScale_) {
        super(pitch_, pitchScale_);
        lenScale = lenScale_;
        tipRadius = tipRadius_;
        tipLen = tipLen_;
        radius = radius_;
    }

    public Group getVectorGroup() {
        final Group group = new Group();
        for (VectorPoint point : points) {
            VectorArrow element = new VectorArrow(point, radius, tipRadius, tipLen, lenScale);
            element.getTransforms().add(new Translate(point.position.x * pitchScale, point.position.y * pitchScale, point.position.z * pitchScale));
            group.getChildren().add(element);
        }
        return group;
    }
}
