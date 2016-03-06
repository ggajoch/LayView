package pl.gajoch.layview.graphics3d;

import javafx.scene.Group;

import java.util.stream.Collectors;

public class VectorSurface extends Group {
    public VectorSurface(SurfacePointsList surfacePointsList, VectorProperties vectorProperties) {
        this.getChildren()
                .addAll(surfacePointsList.points.stream()
                .map(surfacePoint -> new Vector(surfacePoint, vectorProperties))
                .collect(Collectors.toList()));
    }

    public VectorSurface(SurfacePointsList surfacePointsList) {
        this(surfacePointsList, new VectorProperties());
    }
}
