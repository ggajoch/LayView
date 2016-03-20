package pl.gajoch.layview.graphics3d;

import pl.gajoch.layview.gui.common.movable.MovableJPanel;

import java.awt.*;
import java.util.ArrayList;

public class JPanelTest extends MovableJPanel {
    public JPanelTest(int width, int height) {
        super(width, height);
        this.setBackground(Color.RED);
        this.generateContextMenu(new ArrayList<>());
    }
}
