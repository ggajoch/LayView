package pl.gajoch.layview.gui.GUI_2D.options;

import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.graphics2d.options.Scene2DOptions;
import pl.gajoch.layview.gui.common.movable.JFXPanelWindow;

public class Scene2DOptionsEditor extends JFXPanelWindow<Scene2DOptionsController> {
    public Scene2DOptionsEditor() {
        super("Edit options", "Scene2DOptionsWindow.fxml");
    }

    public void exec(SimpleObjectProperty<Scene2DOptions> scene2DOptions) {
        windowController.setup(frame, scene2DOptions);
        open();
    }
}
