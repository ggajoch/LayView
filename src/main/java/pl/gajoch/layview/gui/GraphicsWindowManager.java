package pl.gajoch.layview.gui;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.gajoch.layview.graphics2d.LineGraph;
import pl.gajoch.layview.graphics3d.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GraphicsWindowManager {
    Pane pane3D;
    ArrayList<MovableSubScene> subScenes;
    SimpleObjectProperty<MovableSubScene> actual_scene;
    SubScene subScene;
    Stage stage;

    public void clicked(MovableSubScene scene) {
        if (actual_scene.getValue() != null && actual_scene.getValue() == scene) {
            actual_scene.setValue(null);
        } else {
            actual_scene.setValue(scene);
        }
    }
    GraphicsWindowManager(Stage stage, SubScene subScene) {
        this.stage = stage;
        this.subScene = subScene;
        actual_scene = new SimpleObjectProperty<>();
        subScenes = new ArrayList<>();

        actual_scene.addListener((observable, oldValue, newValue) -> {
            try {
                recalculate();
            } catch (NumberFormatException ignored) {
            }
        });

        subScene.setOnMouseClicked(event -> {
            if( event.isStillSincePress() ) {
                actual_scene.setValue(null);
            }
        });

        pane3D = new Pane();
        subScene.setRoot(pane3D);
    }

    private void recalculate() {
        pane3D.getChildren().clear();
        pane3D.getChildren().addAll(
                subScenes.stream()
                        .map(scene -> scene.scene)
                        .collect(Collectors.toList()));
    }

    private void recalculateWindowSize() {
        try {
            double maxWidth = subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().xOffset + sc.getPosition().xSize)
                    .max().getAsDouble();

            double maxHeight = subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().yOffset + sc.getPosition().ySize)
                    .max().getAsDouble();

            subScene.setWidth(maxWidth);
            subScene.setHeight(maxHeight);
            System.out.println("size: " + maxWidth + maxHeight);
        } catch (NoSuchElementException ignored) {
            subScene.setWidth(0);
            subScene.setHeight(0);
        }
        stage.sizeToScene();
    }

    private void addSizeRecalculations(MovableSubScene scene) {
        ChangeListener<? super Number> handler = (observable1, oldValue1, newValue1) -> {
            recalculate();
            recalculateWindowSize();
        };

        scene.scene.widthProperty().addListener(handler);
        scene.scene.heightProperty().addListener(handler);
        scene.scene.layoutXProperty().addListener(handler);
        scene.scene.layoutYProperty().addListener(handler);
    }

    public void add() {
        MovableSubScene view = new MovableSubScene(this, 100, 100);
        addSizeRecalculations(view);
        view.generateContextMenu(new ArrayList<>());
        subScenes.add(view);
        LineGraph line = new LineGraph();
        view.scene.rootProperty().setValue(line);

        recalculate();
        recalculateWindowSize();
        System.out.print("2D++\r\n");
    }

    public void add3D() {
        CameraSubScene scene = new Scene3D(this,600,600);
        addSizeRecalculations(scene);
        subScenes.add(scene);
        recalculate();
        recalculateWindowSize();
    }

    public void del(MovableSubScene scene) {
        if( subScenes.contains(scene) ) {
            subScenes.remove(scene);
        }
        recalculate();
        recalculateWindowSize();
    }
}
