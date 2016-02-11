package moleculesampleapp;

import javafx.scene.Group;

public class VectorSurface extends Group {
    public VectorSurface(SurfacePointsList surfacePointsList, VectorProperties vectorProperties) {
        for (SurfacePoint point : surfacePointsList.points) {
            Vector element = new Vector(point, vectorProperties);
            this.getChildren().add(element);
        }
    }

    public VectorSurface(SurfacePointsList surfacePointsList) {
        this(surfacePointsList, new VectorProperties());
    }
}
