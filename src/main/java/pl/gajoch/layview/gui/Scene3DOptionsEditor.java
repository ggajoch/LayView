package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;

public class Scene3DOptionsEditor extends JFXPanelWindow<Scene3DOptionsController> {
    public Scene3DOptionsEditor() {
        super("Edit options", "Scene3DOptionsWindow.fxml");
    }

    public void exec(SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        windowController.setup(frame, scene3DOptions);
        open();
    }
}
