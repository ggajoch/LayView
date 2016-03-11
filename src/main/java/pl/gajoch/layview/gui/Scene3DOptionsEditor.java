package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene3DOptionsEditor extends JFXPanelWindow<Scene3DOptionsController> {
    public Scene3DOptionsEditor() {
        super("Edit options", "Scene3DOptionsWindow.fxml");
    }

    public void exec(SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        windowController.setup(frame, scene3DOptions);
        open();
    }
}
