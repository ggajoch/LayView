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

        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Get prediction");
        item1.setOnAction(e -> {
            System.out.println("WORKS");
//            RichTextField.of(minVectorTextField).set(minHint);
            try {
                GradientViewEditor gradientViewEditor = new GradientViewEditor();
                gradientViewEditor.exec(properties);
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }

        });
        contextMenu.getItems().add(item1);

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me)->{
            if(me.getButton()== MouseButton.SECONDARY ){
                System.out.println("Mouse Left Pressed");
                System.out.println(me.getScreenX());
                System.out.println(me.getScreenY());
                contextMenu.show(scene,me.getScreenX(),me.getScreenY());
            }else{
                contextMenu.hide();
            }
            me.consume();
        });
//        scene.setContextMenu(contextMenu);
    }
}
