package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GradientEditor extends JFXPanelWindow<GradientEditorController> {
    public GradientEditor() {
        super("Edit point", "GradientEditor.fxml");
    }

    public void exec(SimpleObjectProperty<HintGradient> gradient) {
        windowController.setup(frame, gradient, gradient.get().getHintMin(), gradient.get().getHintMax());
        open();
    }
}
