package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;

public class Scene2DOptionsEditor extends JFXPanelWindow<Scene2DOptionsController> {
    public Scene2DOptionsEditor() {
        super("Edit options", "Scene2DOptionsWindow.fxml");
    }

    public void exec(SimpleObjectProperty<Scene2DOptions> scene2DOptions) {
        windowController.setup(frame, scene2DOptions);
        open();
    }
}
