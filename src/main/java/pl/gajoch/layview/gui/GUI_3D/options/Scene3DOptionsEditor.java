package pl.gajoch.layview.gui.GUI_3D.options;

import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.graphics3d.options.Scene3DOptions;
import pl.gajoch.layview.gui.common.movable.JFXPanelWindow;

public class Scene3DOptionsEditor extends JFXPanelWindow<Scene3DOptionsController> {
    public Scene3DOptionsEditor() {
        super("Edit options", "Scene3DOptionsWindow.fxml");
    }

    public void exec(SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        windowController.setup(frame, scene3DOptions);
        open();
    }

    public void setHints(double minHint1, double maxHint1,
                         double minHint2, double maxHint2) {
        windowController.setHints(minHint1, maxHint1, minHint2, maxHint2);
    }
}
