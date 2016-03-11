package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;

public class GradientEditor extends JFXPanelWindow<GradientEditorController> {
    public GradientEditor() {
        super("Edit point", "GradientEditor.fxml");
    }

    public void exec(SimpleObjectProperty<HintGradient> gradient) {
        windowController.setup(frame, gradient, gradient.get().getHintMin(), gradient.get().getHintMax());
        open();
    }
}
