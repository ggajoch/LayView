package moleculesampleapp;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import math.math.geometry.Rotation;
import math.math.geometry.RotationOrder;
import math.math.geometry.Vector3D;

/**
 * Created by Piotr on 04/02/2016.
 */
public class CameraView {
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;

    private double xAngle;
    private double yAngle;
    private double zAngle;
    private double scale;
    private double xOffset, yOffset, zOffset;

    private static final double SCROLL_SCALE = 0.0025;
    private static final double ROTATE_SCALE = .01;
    private static final double MOVE_SCALE = 1;
    private Rotate xRotate, yRotate, zRotate;
    private Translate xyzTranslate;

    private Scale cameraScale;

    Group elements;

    private Group cameraDistance;

    CameraView(){
        xOffset = yOffset = zOffset = 0;
        scale = 0;
        xAngle = yAngle = zAngle = 0;

        xRotate = new Rotate(0,new Point3D(0,0,1));
        yRotate = new Rotate(0,new Point3D(1,0,0));
        zRotate = new Rotate(0,new Point3D(0,1,0));

        xyzTranslate = new Translate(0,0,0);

        cameraScale = new Scale(1,1,1);

        elements = new Group();

        elements.getTransforms().addAll(xRotate, yRotate, zRotate, xyzTranslate);

        cameraDistance = new Group(elements);

        cameraDistance.getTransforms().addAll(cameraScale);
    }

    public void setHandlers(SubScene subScene){
        subScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();

            }
        });
        subScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                if(me.isControlDown()){
                    mouseDeltaX = 0;
                }

                if(me.isShiftDown()){
                    mouseDeltaY = 0;
                }

                if(me.isPrimaryButtonDown()){
                    Rotation baseRotation = new Rotation(RotationOrder.XYZ, xAngle, yAngle, zAngle);//get base rotation (it is transform form neutral point to actual point of view)
                    Rotation deltaRotation = new Rotation(new Vector3D(0,0), new Vector3D(-mouseDeltaX*ROTATE_SCALE,-mouseDeltaY*ROTATE_SCALE));//get rotation according to mouse movement
                    Rotation finalRotation = deltaRotation.applyTo(baseRotation);//sum both the rotations
                    try {
                        double angles[] = finalRotation.getAngles(RotationOrder.XYZ);

                        xAngle = angles[0];
                        yAngle = angles[1];
                        zAngle = angles[2];

                        xRotate.setAngle(Math.toDegrees(xAngle));
                        yRotate.setAngle(Math.toDegrees(yAngle));
                        zRotate.setAngle(Math.toDegrees(zAngle));
                    }catch (Exception e){
                        System.out.print("ROTATE ERROR\r\n");
                    }
                }else if(me.isSecondaryButtonDown()){
                    Rotation baseRotation = new Rotation(RotationOrder.ZXY, xAngle, yAngle, zAngle);//magic happens!
                    Vector3D planeTranslate = baseRotation.applyInverseTo(new Vector3D(mouseDeltaX*MOVE_SCALE,mouseDeltaY*MOVE_SCALE,0));

                    xOffset += planeTranslate.getX()*Math.pow(10,-scale);
                    yOffset += planeTranslate.getY()*Math.pow(10,-scale);
                    zOffset += planeTranslate.getZ()*Math.pow(10,-scale);

                    //System.out.print("\tdx="+planeTranslate.getX()+"\tdy="+planeTranslate.getY()+"\tdz="+planeTranslate.getZ()+"\trx="+Math.toDegrees(xAngle)+"\r\n");

                    xyzTranslate.setX(xOffset);
                    xyzTranslate.setY(yOffset);
                    xyzTranslate.setZ(zOffset);
                }

            }
        });
        subScene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double modifier = 1.0;
                if(event.isControlDown()){
                    modifier = 0.1;
                }
                scale += SCROLL_SCALE * modifier * (double)event.getDeltaY();
                //System.out.print("Distance="+ scale +" \tmodifier="+modifier+" \tjump="+event.getDeltaY()+"\r\n");
                cameraScale.setX(Math.pow(10,scale));
                cameraScale.setY(Math.pow(10,scale));
                cameraScale.setZ(Math.pow(10,scale));
            }
        });
    }

    Group getView(){
        return cameraDistance;
    }


}
