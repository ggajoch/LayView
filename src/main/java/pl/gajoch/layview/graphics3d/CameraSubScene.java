package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.MenuItem;
import javafx.scene.transform.Translate;
import org.apache.commons.math3.geometry.euclidean.threed.CardanEulerSingularityException;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import pl.gajoch.layview.gui.GraphicsWindowManager;
import pl.gajoch.layview.gui.MovableSubScene;

import java.util.ArrayList;
import java.util.List;

public class CameraSubScene extends MovableSubScene {
    private Vector2D mousePos;
    private Vector2D mouseOld;
    private Vector2D mouseDelta;

    private Vec3d angle;
    private double scale;
    private Vec3d offset;

    private static final double SCROLL_SCALE = 0.0025;
    private static final double ROTATE_SCALE = .01;
    private static final double MOVE_SCALE = 1;
    private VectorRotate vectorRotate;
    private Translate planeTranslate;
    private VectorTranslate xyzTranslate;

    private PerspectiveCamera camera;

    private UniformScale cameraScale;

    public Group elements;

    private Group cameraDistance;

    private Group cameraView;

    public CameraSubScene(GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);
        mousePos = new Vector2D(0, 0);
        mouseOld = new Vector2D(0, 0);
        mouseDelta = new Vector2D(0, 0);
        offset = new Vec3d(0, 0, 0);
        scale = 0;
        angle = new Vec3d(0, 0, 0);

        vectorRotate = new VectorRotate();


        xyzTranslate = new VectorTranslate();
        planeTranslate = new Translate(width / 2, height / 2, 0);

        cameraScale = new UniformScale();

        elements = new Group();

        elements.getTransforms().addAll(
                vectorRotate.xRotate,
                vectorRotate.yRotate,
                vectorRotate.zRotate,
                xyzTranslate
        );

        cameraDistance = new Group(elements);

        cameraDistance.getTransforms().addAll(planeTranslate, cameraScale);

        camera = new PerspectiveCamera(false);

        cameraView = new Group(cameraDistance);

        cameraView.getChildren().add(camera);

        super.scene.rootProperty().setValue(cameraView);

        super.scene.setPickOnBounds(true); //to catch mouse also on "background"

        super.scene.setCamera(camera);

        setHandlers(super.scene);


        MenuItem item1 = new MenuItem("Edit");
        item1.setOnAction(e -> {
            System.out.println("Edit!");
        });

        List<MenuItem> menu = new ArrayList<>();
        menu.add(item1);

        this.generateContextMenu(menu);
    }

    public void fixCenter(double width, double height){
        planeTranslate.setX(width/2);
        planeTranslate.setY(height/2);
    }

    public void setHandlers(SubScene subScene) {
        subScene.setOnMousePressed(event -> {
            mousePos = new Vector2D(event.getSceneX(), event.getSceneY());
            mouseOld = new Vector2D(event.getSceneX(), event.getSceneY());

            if (event.isMiddleButtonDown()) {

                offset.set(0, 0, 0);
                scale = 0;
                angle.set(0, 0, 0);

                vectorRotate.set(angle);

                xyzTranslate.set(offset);

                cameraScale.set(Math.pow(10, scale));
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
                Rotation baseRotation = new Rotation(RotationOrder.XYZ, angle.x, angle.y, angle.z);
                //get base rotation (it is transform form neutral point to actual point of view)
                Rotation deltaRotation = new Rotation(
                        new Vector3D(0, 0),
                        new Vector3D(-mouseDelta.getX() * ROTATE_SCALE, -mouseDelta.getY() * ROTATE_SCALE)
                );//get rotation according to mouse movement
                Rotation finalRotation = deltaRotation.applyTo(baseRotation);//sum both the rotations
                try {
                    double angles[] = finalRotation.getAngles(RotationOrder.XYZ);

                    angle.x = angles[0];
                    angle.y = angles[1];
                    angle.z = angles[2];

                    vectorRotate.set(angle);
                } catch (CardanEulerSingularityException e) {
                    System.out.print("ROTATE ERROR\r\n");
                }
            } else if (event.isSecondaryButtonDown()) {
                Rotation baseRotation = new Rotation(RotationOrder.ZXY, angle.x, angle.y, angle.z);//magic happens!
                Vector3D planeTranslate = baseRotation.applyInverseTo(
                        new Vector3D(mouseDelta.getX() * MOVE_SCALE, mouseDelta.getY() * MOVE_SCALE, 0)
                );

                offset.x += planeTranslate.getX() * Math.pow(10, -scale);
                offset.y += planeTranslate.getY() * Math.pow(10, -scale);
                offset.z += planeTranslate.getZ() * Math.pow(10, -scale);

                xyzTranslate.set(offset);
            }

        });

        subScene.setOnScroll(event -> {
            double modifier = 1.0;
            if (event.isControlDown()) {
                modifier = 0.1;
            }
            scale += SCROLL_SCALE * modifier * event.getDeltaY();

            cameraScale.set(Math.pow(10, scale));
        });
    }
}
