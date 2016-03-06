package pl.gajoch.layview.graphics3d;

import pl.gajoch.layview.gui.GraphicsWindowManager;
import pl.gajoch.layview.gui.MovableSubScene;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JPanelTest extends MovableSubScene {
    public JPanelTest(GraphicsWindowManager parent, int width, int height) {
        super(parent, width, height);
//        this.scene.setDoubleBuffered(true);
        this.scene.setBackground(Color.RED);
        this.generateContextMenu(new ArrayList<>());
    }
}
