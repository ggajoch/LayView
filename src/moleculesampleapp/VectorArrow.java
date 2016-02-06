package moleculesampleapp;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

/**
 * Created by Piotr on 06/02/2016.
 */
public class VectorArrow extends Group {
    public VectorArrow(VectorPoint vector, double radius_, double tipRadius_, double tipLen_, double scale){
        this(vector, radius_, tipRadius_, tipLen_, scale, 0.001);
    }

    public VectorArrow(VectorPoint vector, double radius_, double tipRadius_, double tipLen_, double scale, double precision){
        DArrow arrow = new DArrow(vector.vector.length()*scale, radius_, tipRadius_, tipLen_, precision);
        arrow.getTransforms().add(new Rotate(90,new Point3D(1,0,0)));
        PhongMaterial arrowMaterial = new PhongMaterial();
        arrowMaterial.setDiffuseColor(vector.color);
        arrowMaterial.setSpecularColor(Color.gray(0.3));
        arrow.setMaterial(arrowMaterial);

        Group rotations = new Group(arrow);

        rotations.getTransforms().addAll(
                new Rotate(Math.toDegrees(Math.atan2(vector.vector.x,-vector.vector.y)),new Point3D(0,0,1)),
                new Rotate(Math.toDegrees(Math.atan2(-vector.vector.z, Math.sqrt(Math.pow(vector.vector.x,2)+Math.pow(vector.vector.y,2)))),new Point3D(1,0,0))
        );

        this.getChildren().add(rotations);
    }
}
