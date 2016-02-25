package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Vector extends Group {
    private Cylinder line;
    private Rotate rotPlane, rotOutPlane;
    public Translate translate;
    private double lenScale;
    private PhongMaterial material;
    private Cone tip;
    Vector(SurfacePoint vector, VectorProperties properties) {


        lenScale = properties.lenScale;


        material = new PhongMaterial();
        material.setDiffuseColor(vector.color);
        material.setSpecularColor(properties.SpecularColor);

        tip = new Cone(properties.tipRadius, properties.tipLen, properties.divisions);
        tip.setMaterial(material);
        tip.setTranslateZ(vector.vector.length() * lenScale);

        line = new Cylinder(properties.radius, 1, properties.divisions);
        line.setMaterial(material);
        line.setRotationAxis(new Point3D(1, 0, 0));
        line.setRotate(90);

        line.setTranslateZ(vector.vector.length() * lenScale / 2.0);
        line.setScaleY(vector.vector.length() * lenScale);

        Group preRotation = new Group(tip, line);
        preRotation.getTransforms().add(new Rotate(90, new Point3D(1, 0, 0)));

        Group rotations = new Group(preRotation);

        rotPlane = new Rotate(Math.toDegrees(Math.atan2(vector.vector.x, -vector.vector.y)), new Point3D(0, 0, 1));
        rotOutPlane = new Rotate(Math.toDegrees(Math.atan2(-vector.vector.z, Math.sqrt(Math.pow(vector.vector.x, 2) + Math.pow(vector.vector.y, 2)))), new Point3D(1, 0, 0));
        translate = new Translate(vector.position.x, vector.position.y, vector.position.z);

        rotations.getTransforms().addAll(
                rotPlane,
                rotOutPlane
        );

        Group translation = new Group(rotations);
        translation.getTransforms().add(translate);

        this.getChildren().add(translation);
    }

    public void update(SurfacePoint vector){
        material.setDiffuseColor(vector.color);
        tip.setTranslateZ(vector.vector.length() * lenScale);
        line.setTranslateZ(vector.vector.length() * lenScale / 2.0);
        line.setScaleY(vector.vector.length() * lenScale);

        rotPlane.setAngle(Math.toDegrees(Math.atan2(vector.vector.x, -vector.vector.y)));
        rotOutPlane.setAngle(Math.toDegrees(Math.atan2(-vector.vector.z, Math.sqrt(Math.pow(vector.vector.x, 2) + Math.pow(vector.vector.y, 2)))));
        translate.setX(vector.position.x);
        translate.setY(vector.position.y);
        translate.setZ(vector.position.z);
    }

    public void updateValue(Vec3d vector){
        tip.setTranslateZ(vector.length() * lenScale);
        line.setTranslateZ(vector.length() * lenScale / 2.0);
        line.setScaleY(vector.length() * lenScale);

        rotPlane.setAngle(Math.toDegrees(Math.atan2(vector.x, -vector.y)));
        rotOutPlane.setAngle(Math.toDegrees(Math.atan2(-vector.z, Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2)))));
    }
}
