package moleculesampleapp;

import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;

public class VectorRotate {
    public Rotate xRotate, yRotate, zRotate;

    public VectorRotate() {
        xRotate = new Rotate(0, new Point3D(0, 0, 1));
        yRotate = new Rotate(0, new Point3D(1, 0, 0));
        zRotate = new Rotate(0, new Point3D(0, 1, 0));
    }

    public void set(Vec3d vector) {
        xRotate.setAngle(Math.toDegrees(vector.x));
        yRotate.setAngle(Math.toDegrees(vector.y));
        zRotate.setAngle(Math.toDegrees(vector.z));
    }
}
