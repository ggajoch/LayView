package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MovableSubScene {
    Gradient grad;
    SubScene scene;
    SimpleObjectProperty<MovableSubScene> actual_scene;
    public MovableSubScene(double width, double height, SimpleObjectProperty<MovableSubScene> actual_scene) {
        Pane dummy = new Pane();
        RichPane richDummy = RichPane.of(dummy);
        richDummy.setFill(Color.BLUE);

        scene = new SubScene(dummy, width, height, true, SceneAntialiasing.BALANCED);
        this.actual_scene = actual_scene;
        actual_scene.setValue(this);

        scene.setOnMouseClicked(event -> {
            if( actual_scene.getValue() != null && actual_scene.getValue() == this ) {
                actual_scene.setValue(null);
            } else {
                actual_scene.setValue(this);
            }
            System.out.println("Event frame");
            event.consume();
        });

        grad = new Gradient();
        grad.add(new GradientPoint(0, Color.BLACK));
    }

    void redraw() {
        Pane dummy = new Pane();
        RichPane richDummy = RichPane.of(dummy);
        richDummy.setFill(grad.getPaint());
        scene.setRoot(dummy);
    }

    public void setWidth(double value) {
        scene.setWidth(value);
    }

    public void setHeight(double value) {
        scene.setHeight(value);
    }

    public void setLayoutX(double value) {
        scene.setLayoutX(value);
    }

    public void setLayoutY(double value) {
        scene.setLayoutY(value);
    }

    public double getHeight() {
        return scene.getHeight();
    }

    public double getWidth() {
        return scene.getWidth();
    }

    public double getLayoutX() {
        return scene.getLayoutX();
    }

    public double getLayoutY() {
        return scene.getLayoutY();
    }
}
