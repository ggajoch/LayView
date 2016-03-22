package pl.gajoch.layview.gui.GUI_2D.options;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.gajoch.layview.graphics2d.options.PlotOptions;
import pl.gajoch.layview.graphics2d.options.Scene2DOptions;
import pl.gajoch.layview.utils.GUIUtils;
import pl.gajoch.layview.utils.gui.RichTextField;

import javax.swing.*;

import static pl.gajoch.layview.utils.GUIUtils.showNumberFormatException;

public class Scene2DOptionsController {
    private JFrame frame;

    // -------------------------- Private variables  -------------------------
    private SimpleObjectProperty<Scene2DOptions> scene2DOptions;
    private RichTextField RichFPS;
    @FXML
    private Button okButton, cancelButton;

    // --------------------------- Private methods  --------------------------
    @FXML
    private TextField FPS;

    // ------------------------------- Objects  ------------------------------

    // ----------------------------- Public API  -----------------------------
    public void setup(JFrame frame, SimpleObjectProperty<Scene2DOptions> scene2DOptions) {
        this.frame = frame;
        this.scene2DOptions = scene2DOptions;
        RichFPS = RichTextField.of(FPS);
        RichFPS.set(scene2DOptions.get().FPS);
    }

    private void recalculate() throws NumberFormatException {
        Scene2DOptions newScene = new Scene2DOptions(RichFPS.getDouble(), new PlotOptions());
        scene2DOptions.set(newScene);
    }

    // --------------------------- button handlers ---------------------------

    @FXML
    private void ok_click() {
        try {
            recalculate();
            GUIUtils.closeJFrame(frame);
        } catch (NumberFormatException ex) {
            showNumberFormatException(ex);
        }
    }

    @FXML
    private void cancel_click() {
        GUIUtils.closeJFrame(frame);
    }
}