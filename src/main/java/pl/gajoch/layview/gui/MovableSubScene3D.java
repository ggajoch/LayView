package pl.gajoch.layview.gui;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MovableSubScene3D extends MovableSubScene {
    public MovableSubScene3D(GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);

        MenuItem item1 = new MenuItem("Edit");
        item1.setOnAction(e -> {
            System.out.println("Edit!");
        });

        List<MenuItem> menu = new ArrayList<>();
        menu.add(item1);

        this.generateContextMenu(menu);
    }
}
