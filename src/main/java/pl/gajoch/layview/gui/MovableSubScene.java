package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.util.Properties;

public class MovableSubScene {
    Gradient grad;
    public SubScene scene;
    SimpleObjectProperty<MovableSubScene> actual_scene;
    Properties properties;
    public MovableSubScene(GraphicsWindowManager parent, double width, double height) {

//        Builder builder = new Builder();

//        scene = new SubScene(builder.getRoot(), width, height, true, SceneAntialiasing.BALANCED);
        Pane p = new Pane();
        scene = new SubScene(p, width, height, true, SceneAntialiasing.BALANCED);

//        builder.handlers_set(scene);
//        builder.cam_set(scene);

//        this.actual_scene = actual_scene;
//        actual_scene.setValue(this);

        scene.setOnMouseClicked(event -> {
            if( event.getButton() == MouseButton.PRIMARY && event.isStillSincePress() ) {
                parent.clicked(this);
                event.consume();
            }
        });

//        grad = new Gradient();
//        grad.add(new GradientPoint(0, Color.BLACK));
    }

    public void setPosition(WindowPosition position) {
        scene.setWidth(position.xSize);
        scene.setHeight(position.ySize);
        scene.setLayoutX(position.xOffset);
        scene.setLayoutY(position.yOffset);
        fixCenter(position.xSize, position.ySize);
    }

    public void fixCenter(double width, double height){

    }

    public WindowPosition getPosition() {
        return new WindowPosition(Double.valueOf(scene.getWidth()).intValue(),
                Double.valueOf(scene.getHeight()).intValue(),
                Double.valueOf(scene.getLayoutX()).intValue(),
                Double.valueOf(scene.getLayoutY()).intValue());
    }

}
