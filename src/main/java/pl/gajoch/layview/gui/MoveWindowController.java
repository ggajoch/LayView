package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.gajoch.layview.utils.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MoveWindowController {

    // ----------------------------- Public API  -----------------------------

    public Rectangle getWindowPosition() {
        return windowPosition.get();
    }

    public void setup(JFrame frame, SimpleObjectProperty<Rectangle> windowPosition) {
        this.frame = frame;
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

    private JFrame frame;
    private Stage stage;
    private Rectangle start;
    private SimpleObjectProperty<Rectangle> windowPosition;
    RichTextField xSizeRich, ySizeRich, xOffsetRich, yOffsetRich;

    // --------------------------- Private methods  --------------------------

    private void setWindowPosition(Rectangle windowPosition) {
        if (windowPosition != null) {
            xSizeRich.setInteger(windowPosition.getWidth());
            ySizeRich.setInteger(windowPosition.getHeight());
            xOffsetRich.setInteger(windowPosition.getX());
            yOffsetRich.setInteger(windowPosition.getY());
        }
    }

    private void recalculate() {
        Rectangle pos = new Rectangle(xOffsetRich.getInt(), yOffsetRich.getInt(),
                xSizeRich.getInt(), ySizeRich.getInt());
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
        GUIUtils.closeJFrame(frame);
    }

    @FXML
    private void cancel_click() {
        windowPosition.set(start);
        GUIUtils.closeJFrame(frame);
    }
}