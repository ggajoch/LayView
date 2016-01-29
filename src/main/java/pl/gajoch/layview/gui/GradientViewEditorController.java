
package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.gajoch.layview.utils.GUIUtils;

import java.io.IOException;

import static pl.gajoch.layview.utils.GUIUtils.*;

public class GradientViewEditorController {
    GradientEditor gradientEditor;
    // ----------------------------- Public API  -----------------------------
    public void setup(Stage stage, GradientViewProperties properties) throws IOException {
        this.stage = stage;
        this.properties = properties;
        gradientEditor = new GradientEditor();
    }

    // -------------------------- Private variables  -------------------------

    private Stage stage;
    private GradientViewProperties properties;

    // --------------------------- Private methods  --------------------------

    // ------------------------------- Objects  ------------------------------

    @FXML
    private Button gradientEdit;

    // --------------------------- button handlers ---------------------------

    @FXML
    private void gradientEdit_click() throws IOException {
        SimpleObjectProperty<Gradient> simpleObjectProperty = properties.gradient;
        simpleObjectProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("EDIT");
            properties.gradient.setValue(newValue);
        });
        gradientEditor.exec(simpleObjectProperty, 0.0, 0.0);
    }
}