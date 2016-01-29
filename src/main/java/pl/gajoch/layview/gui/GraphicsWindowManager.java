package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GraphicsWindowManager {
    Pane pane3D;
    ArrayList<MovableSubScene> subScenes;
    SimpleObjectProperty<MovableSubScene> actual_scene;
    WindowPositionControl windowPositionControl;

    SubScene subScene;
    Stage stage;

    public void clicked(MovableSubScene scene) {
        if (actual_scene.getValue() != null && actual_scene.getValue() == scene) {
            actual_scene.setValue(null);
        } else {
            actual_scene.setValue(scene);
        }
    }
    GraphicsWindowManager(Stage stage, SubScene subScene, WindowPositionControl windowPositionControl) {
        this.stage = stage;
        this.subScene = subScene;
        this.windowPositionControl = windowPositionControl;

        windowPositionControl.addListener((observable, oldValue, newValue) -> {
            System.out.println("new!");
            actual_scene.getValue().setPosition(newValue);
            recalculate();
            recalculateWindowSize();
        });
        actual_scene = new SimpleObjectProperty<>();
        subScenes = new ArrayList<>();

        actual_scene.addListener((observable, oldValue, newValue) -> {
            try {
                recalculate();
                setTextFields();
            } catch (NumberFormatException ignored) {
            }
        });

        subScene.setOnMouseClicked(event -> {
            if( event.isStillSincePress() ) {
                actual_scene.setValue(null);
                setTextFields();
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

    public void add() {
//        subScenes.add(new MovableSubScene(this, 100, 100));
        subScenes.add(new GradientView(this, 100, 100));
        recalculate();
        setTextFields();
        recalculateWindowSize();
    }

    public void add3D() {
        subScenes.add(new MoleculeView(this, 100, 100));
        recalculate();
        setTextFields();
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
        setTextFields();
        recalculateWindowSize();
    }


    void setTextFields() {
        if (actual_scene.getValue() == null) {
            windowPositionControl.noValues();
        } else {
            windowPositionControl.set(actual_scene.getValue().getPosition());
        }
    }

}
