package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class MovableSubScene {
    Gradient grad;
    public SubScene scene;
    Properties properties;
    public SimpleObjectProperty<WindowPosition> position;
    private MoveWindowEditor moveWindowEditor;
    private GraphicsWindowManager graphicsWindowManager;

    public MovableSubScene(GraphicsWindowManager parent, double width, double height) {
        graphicsWindowManager = parent;
        Pane p = new Pane();
        scene = new SubScene(p, width, height, true, SceneAntialiasing.BALANCED);
        moveWindowEditor = new MoveWindowEditor();

        position = new SimpleObjectProperty<>();

        scene.setOnMouseClicked(event -> {
            if( event.getButton() == MouseButton.PRIMARY && event.isStillSincePress() ) {
                parent.clicked(this);
                event.consume();
            }
        });
    }

    protected void generateContextMenu(Collection<? extends MenuItem> list) {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(list);

        MenuItem item1 = new MenuItem("Move...");
        item1.setOnAction(e -> {
            position.set(this.getPosition());

            position.addListener((observable, oldValue, newValue) -> {
                setPosition(newValue);
            });
            moveWindowEditor.exec(position);
        });
        contextMenu.getItems().add(item1);

        MenuItem item2 = new MenuItem("Delete");
        item2.setOnAction(e -> {
            graphicsWindowManager.del();
        });
        contextMenu.getItems().add(item2);

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
            if (me.getButton() == MouseButton.SECONDARY && me.isStillSincePress()) {
                System.out.println("Mouse Left Pressed");
                System.out.println(me.getScreenX());
                System.out.println(me.getScreenY());
                contextMenu.show(scene, me.getScreenX(), me.getScreenY());
            } else {
                contextMenu.hide();
            }
            me.consume();
        });
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
