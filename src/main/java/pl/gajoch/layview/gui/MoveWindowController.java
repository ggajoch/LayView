package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static pl.gajoch.layview.utils.GUIUtils.showErrorMessage;

public class MoveWindowController {

    // ----------------------------- Public API  -----------------------------

    public WindowPosition getWindowPosition() {
        return windowPosition.get();
    }

    public void setup(Stage stage, SimpleObjectProperty<WindowPosition> windowPosition) {
        this.stage = stage;
        xSizeRich = RichTextField.of(xSize);
        ySizeRich = RichTextField.of(ySize);
        xOffsetRich = RichTextField.of(xOffset);
        yOffsetRich = RichTextField.of(yOffset);

        setWindowPosition(windowPosition.get());
        start = windowPosition.get();
        this.windowPosition = windowPosition;

        xSize.textProperty().addListener(observable -> recalculate());
        ySize.textProperty().addListener(observable -> recalculate());
        xOffset.textProperty().addListener(observable -> recalculate());
        yOffset.textProperty().addListener(observable -> recalculate());
    }

    // -------------------------- Private variables  -------------------------

    private Stage stage;
    private WindowPosition start;
    private SimpleObjectProperty<WindowPosition> windowPosition;
    RichTextField xSizeRich, ySizeRich, xOffsetRich, yOffsetRich;

    // --------------------------- Private methods  --------------------------

    private void setWindowPosition(WindowPosition windowPosition) {
        if (windowPosition != null) {
            xSizeRich.set(windowPosition.xSize);
            ySizeRich.set(windowPosition.ySize);
            xOffsetRich.set(windowPosition.xOffset);
            yOffsetRich.set(windowPosition.yOffset);
        }
    }

    private void recalculate() {
        WindowPosition pos = new WindowPosition(xSizeRich.getInt(), ySizeRich.getInt(),
                xOffsetRich.getInt(), yOffsetRich.getInt());
        windowPosition.set(pos);
    }

    // ------------------------------- Objects  ------------------------------

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField xSize, ySize, xOffset, yOffset;

    // --------------------------- button handlers ---------------------------

    @FXML
    private void ok_click() {
        this.stage.close();
    }

    @FXML
    private void cancel_click() {
        windowPosition.set(start);
        this.stage.close();
    }
}