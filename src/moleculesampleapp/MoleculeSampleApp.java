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
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cmcastil
 */
public class MoleculeSampleApp extends Application {

    Stage stage;
    Scene scene;


    private void handleKeyboard(SubScene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case S:
                        write_snapshot(scene);
                        break;
                }
            }
        });
    }

    private void write_snapshot(SubScene scene) {
        WritableImage image = new WritableImage(768, 768);
        scene.snapshot(null, image);
        FileChooser x = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        x.getExtensionFilters().add(extFilter);
        File file = x.showSaveDialog(stage);
//        File file = new File("D:\\Charts.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }catch (IOException e) {
//                             TODO: handle exception here
        }
        return;
    }

    @Override
    public void start(Stage primaryStage) {
        
       // setUserAgentStylesheet(STYLESHEET_MODENA);
        System.out.println("start()");

        List<Builder> listBuilders = new ArrayList<Builder>();
        listBuilders.add(new Builder());
        listBuilders.add(new Builder());
        listBuilders.add(new Builder());
        listBuilders.add(new Builder());


        List<SubScene> sceneList = listBuilders.stream().
                map(b -> new SubScene(b.getRoot(), 384, 384, true, SceneAntialiasing.BALANCED)).
                collect(Collectors.toList());

        GridPane gridPane = new GridPane();
        Group root = new Group();

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        Sphere oxygenSphere = new Sphere(40.0);
        oxygenSphere.setMaterial(redMaterial);

        root.getChildren().add(oxygenSphere);

        root.setTranslateX(100.0);
        root.setTranslateY(50.0);

        SubScene scena = new SubScene(root,384,384, true, SceneAntialiasing.BALANCED);

        gridPane.add(sceneList.get(0), 0, 0);
        gridPane.add(sceneList.get(1), 1, 0);
        gridPane.add(sceneList.get(2), 0, 1);
        gridPane.add(scena/*sceneList.get(3)*/, 1, 1);


        SubScene subScene = new SubScene(gridPane, 768, 768, true, SceneAntialiasing.BALANCED);

        Button btn = new Button("Snapshot");
        btn.setOnAction(event -> write_snapshot(subScene));

        VBox vbox = new VBox(subScene, btn);
        scene = new Scene(vbox, 800, 800, true);


        scene.setFill(Color.BLACK);

        for(int i = 0; i < listBuilders.size(); ++i) {
            listBuilders.get(i).handlers_set(sceneList.get(i));
        }

        handleKeyboard(subScene, gridPane);

        stage = primaryStage;
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        for(int i = 0; i < listBuilders.size(); ++i) {
            listBuilders.get(i).cam_set(sceneList.get(i));
        }
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
