import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        if( point != null ) {
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
            point = new GradientPoint(Double.valueOf(value.getText()), colorPicker.getValue());
            this.stage.close();
        } catch (NumberFormatException e) {
            Utils.showErrorMessage("Bad number", "Cannot parse value: \"" + value.getText() + "\"");
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