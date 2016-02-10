package moleculesampleapp;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * Created by Piotr on 23/01/2016.
 */
public class Vector extends Group {
    Vector(VectorPoint vector, VectorProperties properties){

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(vector.color);
        material.setSpecularColor(properties.SpecularColor);

        Cone tip = new Cone(properties.tipRadius, properties.tipLen, properties.divisions);
        tip.setMaterial(material);
        tip.setTranslateZ(vector.vector.length() * properties.lenScale);

        Cylinder line = new Cylinder(properties.radius, vector.vector.length() * properties.lenScale, properties.divisions);
        line.setMaterial(material);
        line.setRotationAxis(new Point3D(1, 0, 0));
        line.setRotate(90);
        line.setTranslateZ(vector.vector.length() * properties.lenScale / 2.0);

        Group preRotation = new Group(tip, line);
        preRotation.getTransforms().add(new Rotate(90, new Point3D(1, 0, 0)));

        Group rotations = new Group(preRotation);

        rotations.getTransforms().addAll(
                new Rotate(Math.toDegrees(Math.atan2(vector.vector.x, -vector.vector.y)), new Point3D(0, 0, 1)),
                new Rotate(Math.toDegrees(Math.atan2(-vector.vector.z, Math.sqrt(Math.pow(vector.vector.x, 2) + Math.pow(vector.vector.y, 2)))), new Point3D(1, 0, 0))
        );

        Group translation = new Group(rotations);
        translation.getTransforms().add(new Translate(vector.position.x, vector.position.y, vector.position.z));

        this.getChildren().add(translation);
    }
}
