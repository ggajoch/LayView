package pl.gajoch.layview.gui.GUI_3D.gradientPoint;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.gajoch.layview.graphics3d.options.GradientPoint;
import pl.gajoch.layview.utils.gui.RichTextField;

import static pl.gajoch.layview.utils.GUIUtils.*;

public class GradientPointEditorController {
    // ----------------------------- Public API  -----------------------------
    public GradientPoint getPoint() {
        return point;
    }

    public void setup(Stage stage, GradientPoint point) {
        this.stage = stage;
        setPoint(point);
    }

    // -------------------------- Private variables  -------------------------

    private Stage stage;
    private GradientPoint point;

    // --------------------------- Private methods  --------------------------

    private void setPoint(GradientPoint point) {
        if (point != null) {
            value.setText(point.toString());
            colorPicker.setValue(point.getColor());
        }
    }

    // ------------------------------- Objects  ------------------------------

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField value;
    @FXML
    private ColorPicker colorPicker;

    // --------------------------- button handlers ---------------------------

    @FXML
    private void ok_click() {
        try {
            point = new GradientPoint(RichTextField.of(value).getDouble(), colorPicker.getValue());
            this.stage.close();
        } catch (NumberFormatException e) {
            showErrorMessage("Bad number", "Cannot parse value: \"" + value.getText() + "\"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel_click() {
        this.point = null;
        this.stage.close();
    }
}