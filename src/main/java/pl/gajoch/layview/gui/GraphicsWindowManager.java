package pl.gajoch.layview.gui;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pl.gajoch.layview.graphics2d.LineGraph;
import pl.gajoch.layview.graphics3d.CameraSubScene;
import pl.gajoch.layview.graphics3d.SurfacePoint;
import pl.gajoch.layview.graphics3d.SurfacePointsList;
import pl.gajoch.layview.graphics3d.VectorSurface;

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

        if (actual_scene.getValue() != null) {
            final Rectangle redBorder = new Rectangle(0, 0, Color.TRANSPARENT);
            redBorder.setStroke(Color.RED);
            redBorder.setManaged(false);
            redBorder.setDisable(true);
            redBorder.setStrokeWidth(10);
            redBorder.setLayoutX(actual_scene.getValue().scene.getLayoutX() + 5);
            redBorder.setLayoutY(actual_scene.getValue().scene.getLayoutY() + 5);
            redBorder.setWidth(actual_scene.getValue().scene.getWidth() - 10);
            redBorder.setHeight(actual_scene.getValue().scene.getHeight() - 10);

            pane3D.getChildren().add(redBorder);
            redBorder.setOnMouseDragged(event -> System.out.println("rect dragged"));
        }
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

    private void add_size_recalculations(MovableSubScene scene) {
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
        GradientView view = new GradientView(this, 100, 100);
        add_size_recalculations(view);
        subScenes.add(view);
        LineGraph line = new LineGraph();
        view.scene.rootProperty().setValue(line);
        recalculate();
        recalculateWindowSize();
    }

    public void add3D() {
        CameraSubScene scene = new CameraSubScene(this,100,100);
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

        add_size_recalculations(scene);

        scene.elements.getChildren().addAll(new VectorSurface(surface));
        subScenes.add(scene);
        recalculate();
        recalculateWindowSize();
    }

    public void del() {
        try {
            if( subScenes.contains(actual_scene.getValue()) ) {
                subScenes.remove(actual_scene.getValue());
                actual_scene.setValue(subScenes.get(0));
            }
        } catch (IndexOutOfBoundsException exception) {
            actual_scene.setValue(null);
        }
        recalculate();
        recalculateWindowSize();
    }
}
