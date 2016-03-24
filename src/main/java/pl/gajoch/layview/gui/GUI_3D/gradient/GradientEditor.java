package pl.gajoch.layview.gui.GUI_3D.gradient;

import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.graphics3d.options.HintGradient;
import pl.gajoch.layview.gui.common.movable.JFXPanelWindow;

public class GradientEditor extends JFXPanelWindow<GradientEditorController> {
    public GradientEditor() {
        super("Edit point", "GradientEditor.fxml");
    }

    public void exec(SimpleObjectProperty<HintGradient> gradient) {
        windowController.setup(frame, gradient);
        open();
    }

    public void setHints(double minHint, double maxHint) {
        windowController.setHints(minHint, maxHint);
    }
}
