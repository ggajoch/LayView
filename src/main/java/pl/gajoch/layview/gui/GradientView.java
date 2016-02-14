package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Properties;

public class GradientView extends MovableSubScene {
    GradientViewProperties properties;

    public GradientView (GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);

        GradientViewProperties properties = new GradientViewProperties();
        properties.gradient = new SimpleObjectProperty<>(new Gradient());

        Pane p = new Pane();
        scene.setRoot(p);
        RichPane richPane = RichPane.of(p);


        properties.gradient.addListener((observable, oldValue, newValue) -> {
            richPane.setFill(newValue.getPaint());
        });


//        scene.setContextMenu(contextMenu);
    }
}
