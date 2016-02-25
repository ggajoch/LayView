package pl.gajoch.layview.graphics3d;

import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

public class BoxSurface extends Group {
    public BoxSurface(SurfacePointsList surfacePointsList, BoxProperties boxProperties) {
        for (SurfacePoint point : surfacePointsList.points) {
            Box element = new Box(boxProperties.dimensions.x, boxProperties.dimensions.y, boxProperties.dimensions.z);
            PhongMaterial elementMaterial = new PhongMaterial();
            elementMaterial.setDiffuseColor(point.color);
//            elementMaterial.setSpecularColor(boxProperties.SpecularColor);
            element.setMaterial(elementMaterial);
            element.getTransforms().add(new Translate(point.position.x, point.position.y, point.position.z));
            this.getChildren().add(element);
        }
    }

    public BoxSurface(SurfacePointsList surfacePointsList) {
        this(surfacePointsList, new BoxProperties());
    }
}
