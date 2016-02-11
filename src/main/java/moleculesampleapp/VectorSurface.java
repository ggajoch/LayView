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
public class VectorSurface extends Group {
    public VectorSurface(SurfacePointsList surfacePointsList, VectorProperties vectorProperties) {
        for (VectorPoint point : surfacePointsList.points) {
            Vector element = new Vector(point, vectorProperties);
            this.getChildren().add(element);
        }
    }

    public VectorSurface(SurfacePointsList surfacePointsList) {
        this(surfacePointsList, new VectorProperties());
    }
}
