package pl.gajoch.layview.gui.common.movable;

import javafx.beans.property.SimpleObjectProperty;

import java.awt.*;

public class MoveWindowEditor extends JFXPanelWindow<MoveWindowController> {
    public MoveWindowEditor() {
        super("Move window...", "MoveWindow.fxml");
    }

    public void exec(SimpleObjectProperty<Rectangle> windowPosition) {
        windowController.setup(frame, windowPosition);
        open();
    }
}
