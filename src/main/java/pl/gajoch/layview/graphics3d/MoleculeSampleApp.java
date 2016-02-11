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

package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;

/**
 * @author cmcastil
 */
public class MoleculeSampleApp extends Application {

    Stage stage;
    Scene scene;


    @Override
    public void start(Stage primaryStage) {

        System.out.println("start()");

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.DARKRED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);
        greenMaterial.setSpecularColor(Color.gray(0.3));

        Sphere oxygenSphere = new Sphere(10);
        oxygenSphere.setMaterial(redMaterial);

        /*
        SurfacePointsList surface = new SurfacePointsList();

        for (double x = 0; x < 100; x += 10) {
            for (double y = -100; y < 100; y += 10) {
                for (double z = -100; z < 100; z += 10) {
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= 100) {
                        surface.add(new SurfacePoint(new Vec3d(x, y, z), new Vec3d(x / 10, y / 10, z / 10), new Color(y / 200 + 0.5, 0, -y / 200 + 0.5, 1)));
                    }
                }
            }
        }


        CameraSubScene camera1 = new CameraSubScene(384, 384, true, SceneAntialiasing.BALANCED);

        camera1.elements.getChildren().addAll(new BoxSurface(surface));

        Vector myArrow2 = new Vector(new SurfacePoint(new Vec3d(0, 0, 0), new Vec3d(25, 50, 0), Color.GREEN), new VectorProperties(100, 100, 50, 1));
        //myArrow2.setMaterial(redMaterial);


        CameraSubScene camera2 = new CameraSubScene(384, 384, true, SceneAntialiasing.BALANCED);

        camera2.elements.getChildren().addAll(myArrow2);


        CameraSubScene camera3 = new CameraSubScene(384, 384, true, SceneAntialiasing.BALANCED);

        camera3.elements.getChildren().addAll(new VectorSurface(surface));

        Vector myArrow4 = new Vector(new SurfacePoint(new Vec3d(0, 0, 0), new Vec3d(0, 0, 100), Color.GREEN), new VectorProperties(100, 100, 50, 1));


        CameraSubScene camera4 = new CameraSubScene(384, 384, true, SceneAntialiasing.BALANCED);

        camera4.elements.getChildren().addAll(myArrow4);


        GridPane gridPane = new GridPane();

        gridPane.add(camera1, 0, 0);
        gridPane.add(camera2, 1, 0);
        gridPane.add(camera3, 0, 1);
        gridPane.add(camera4, 1, 1);


        SubScene subScene = new SubScene(gridPane, 768, 768, true, SceneAntialiasing.BALANCED);
        VBox vbox = new VBox(subScene);
        scene = new Scene(vbox, 800, 800, true);

        scene.setFill(Color.LIGHTGRAY);

        stage = primaryStage;
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();*/

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
