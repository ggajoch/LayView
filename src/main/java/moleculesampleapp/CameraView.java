package moleculesampleapp;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec3d;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by Piotr on 04/02/2016.
 */
public class CameraView {
    private Vector2D mousePos;
    private Vector2D mouseOld;
    private Vector2D mouseDelta;

    private Vec3d angle;
    private double scale;
    private Vec3d offset;

    private static final double SCROLL_SCALE = 0.0025;
    private static final double ROTATE_SCALE = .01;
    private static final double MOVE_SCALE = 1;
    private Rotate xRotate, yRotate, zRotate;
    private Translate xyzTranslate, planeTranslate;

    private PerspectiveCamera camera;

    private Scale cameraScale;

    public Group elements;

    private Group cameraDistance;

    private Group cameraView;

    private SubScene scene;

    CameraView(double width, double height, boolean depthBuffer, SceneAntialiasing sceneAntialiasing) {
        mousePos = new Vector2D(0, 0);
        mouseOld = new Vector2D(0, 0);
        mouseDelta = new Vector2D(0, 0);
        offset = new Vec3d(0, 0, 0);
        scale = 0;
        angle = new Vec3d(0, 0, 0);

        xRotate = new Rotate(0, new Point3D(0, 0, 1));
        yRotate = new Rotate(0, new Point3D(1, 0, 0));
        zRotate = new Rotate(0, new Point3D(0, 1, 0));

        xyzTranslate = new Translate(0, 0, 0);
        planeTranslate = new Translate(width / 2, height / 2, 0);

        cameraScale = new Scale(1, 1, 1);

        elements = new Group();

        elements.getTransforms().addAll(xRotate, yRotate, zRotate, xyzTranslate);

        cameraDistance = new Group(elements);

        cameraDistance.getTransforms().addAll(planeTranslate, cameraScale);

        camera = new PerspectiveCamera(false);

        //camera.getTransforms().add(planeTranslate.createInverse());

        cameraView = new Group(cameraDistance);

        cameraView.getChildren().add(camera);

        scene = new SubScene(cameraView, width, height, depthBuffer, sceneAntialiasing);

        scene.setPickOnBounds(true); //to catch mouse also on "background"

        scene.setCamera(camera);

        setHandlers(scene);
    }

    public void setHandlers(SubScene subScene) {
        subScene.setOnMousePressed(event -> {
            mousePos = new Vector2D(event.getSceneX(), event.getSceneY());
            mouseOld = new Vector2D(event.getSceneX(), event.getSceneY());

            if (event.isMiddleButtonDown()) {

                offset.x = offset.y = offset.z = 0;
                scale = 0;
                angle.x = angle.y = angle.z = 0;

                xRotate.setAngle(Math.toDegrees(angle.x));
                yRotate.setAngle(Math.toDegrees(angle.y));
                zRotate.setAngle(Math.toDegrees(angle.z));

                xyzTranslate.setX(offset.x);
                xyzTranslate.setY(offset.y);
                xyzTranslate.setZ(offset.z);

                cameraScale.setX(Math.pow(10, scale));
                cameraScale.setY(Math.pow(10, scale));
                cameraScale.setZ(Math.pow(10, scale));
            }
        });
        subScene.setOnMouseDragged(event -> {

            mouseOld = new Vector2D(1, mousePos);
            mousePos = new Vector2D(event.getSceneX(), event.getSceneY());
            mouseDelta = new Vector2D(1, mousePos, -1, mouseOld);

            if (event.isControlDown()) {
                mouseDelta = new Vector2D(0, mouseDelta.getY());
            }

            if (event.isShiftDown()) {
                mouseDelta = new Vector2D(mouseDelta.getX(), 0);
            }

            if (event.isPrimaryButtonDown()) {
                Rotation baseRotation = new Rotation(RotationOrder.XYZ, angle.x, angle.y, angle.z);//get base rotation (it is transform form neutral point to actual point of view)
                Rotation deltaRotation = new Rotation(new Vector3D(0, 0), new Vector3D(-mouseDelta.getX() * ROTATE_SCALE, -mouseDelta.getY() * ROTATE_SCALE));//get rotation according to mouse movement
                Rotation finalRotation = deltaRotation.applyTo(baseRotation);//sum both the rotations
                try {
                    double angles[] = finalRotation.getAngles(RotationOrder.XYZ);

                    angle.x = angles[0];
                    angle.y = angles[1];
                    angle.z = angles[2];

                    xRotate.setAngle(Math.toDegrees(angle.x));
                    yRotate.setAngle(Math.toDegrees(angle.y));
                    zRotate.setAngle(Math.toDegrees(angle.z));
                } catch (Exception e) {
                    System.out.print("ROTATE ERROR\r\n");
                }
            } else if (event.isSecondaryButtonDown()) {
                Rotation baseRotation = new Rotation(RotationOrder.ZXY, angle.x, angle.y, angle.z);//magic happens!
                Vector3D planeTranslate = baseRotation.applyInverseTo(new Vector3D(mouseDelta.getX() * MOVE_SCALE, mouseDelta.getY() * MOVE_SCALE, 0));

                offset.x += planeTranslate.getX() * Math.pow(10, -scale);
                offset.y += planeTranslate.getY() * Math.pow(10, -scale);
                offset.z += planeTranslate.getZ() * Math.pow(10, -scale);

                //System.out.print("\tdx="+planeTranslate.getX()+"\tdy="+planeTranslate.getY()+"\tdz="+planeTranslate.getZ()+"\trx="+Math.toDegrees(xAngle)+"\r\n");

                xyzTranslate.setX(offset.x);
                xyzTranslate.setY(offset.y);
                xyzTranslate.setZ(offset.z);
            }

        });

        subScene.setOnScroll(event -> {
            double modifier = 1.0;
            if (event.isControlDown()) {
                modifier = 0.1;
            }
            scale += SCROLL_SCALE * modifier * event.getDeltaY();
            //System.out.print("Distance="+ scale +" \tmodifier="+modifier+" \tjump="+event.getDeltaY()+" "+event.getDeltaX()+"\r\n");
            cameraScale.setX(Math.pow(10, scale));
            cameraScale.setY(Math.pow(10, scale));
            cameraScale.setZ(Math.pow(10, scale));
        });
    }

    SubScene getScene() {
        return scene;
    }


}
