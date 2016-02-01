/*
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package moleculesampleapp;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.event.EventHandler;
import math.math.geometry.Rotation;
import math.math.geometry.RotationOrder;
import math.math.geometry.Vector3D;

/**
 *
 * @author cmcastil
 */
public class MoleculeSampleApp extends Application {

    Stage stage;
    Scene scene;
    double angle = 0;
    Arrow myArrow;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    double xAngle;
    double yAngle;
    double zAngle;
    double distance;
    double xOffset, yOffset, zOffset;

    private static final double SCROLL_SCALE = 0.025;
    private static final double ROTATE_SCALE = .01;
    private static final double MOVE_SCALE = 1;
    Rotate xRotate, yRotate, zRotate;
    Translate xyzTranslate;

    @Override
    public void start(Stage primaryStage) {

        xOffset = yOffset = zOffset = 0;
        distance = 1;
        xAngle = yAngle = zAngle = 0;

        System.out.println("start()");

        Group root = new Group();

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.DARKRED);

        Sphere oxygenSphere = new Sphere(10);
        oxygenSphere.setMaterial(redMaterial);

        myArrow = new Arrow();

        root.getChildren().add(myArrow);

        xRotate = new Rotate(0,new Point3D(0,0,1));
        yRotate = new Rotate(0,new Point3D(1,0,0));
        zRotate = new Rotate(0,new Point3D(0,1,0));

        xyzTranslate = new Translate(0,0,0);

        myArrow.getTransforms().addAll(xRotate, yRotate, zRotate, xyzTranslate);

        root.setTranslateX(768/2.0);
        root.setTranslateY(768/2.0);

        root.getChildren().add(oxygenSphere);


        SubScene subScene = new SubScene(root, 768, 768, true, SceneAntialiasing.BALANCED);


        VBox vbox = new VBox(subScene);
        scene = new Scene(vbox, 800, 800, true);


        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();

            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
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
                    Rotation baseRotation = new Rotation(RotationOrder.XYZ, yAngle, zAngle, xAngle);
                    Vector3D planeTranslate = baseRotation.applyInverseTo(new Vector3D(mouseDeltaX*MOVE_SCALE,mouseDeltaY*MOVE_SCALE,0));

                    xOffset += planeTranslate.getX();
                    yOffset += planeTranslate.getY();
                    zOffset += planeTranslate.getZ();

                    System.out.print("\tdx="+planeTranslate.getX()+"\tdy="+planeTranslate.getY()+"\tdz="+planeTranslate.getZ()+"\trx="+Math.toDegrees(xAngle)+"\r\n");

                    xyzTranslate.setX(xOffset);
                    xyzTranslate.setY(yOffset);
                    xyzTranslate.setZ(zOffset);
                }

            }
        });
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                distance += SCROLL_SCALE*event.getDeltaY();
                System.out.print("Distance="+distance+"\r\n");
            }
        });

        scene.setFill(Color.LIGHTGRAY);

        /*for(int i = 0; i < listBuilders.size(); ++i) {
            listBuilders.get(i).handlers_set(sceneList.get(i));
        }


        */
        //handleKeyboard(subScene, root);
        stage = primaryStage;
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
        for(int i = 0; i < listBuilders.size(); ++i) {
            listBuilders.get(i).cam_set(sceneList.get(i));
        }*/

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
