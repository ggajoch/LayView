package pl.gajoch.layview.gui.common.movable;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.gajoch.layview.utils.GUIUtils;
import pl.gajoch.layview.utils.gui.RichTextField;

import javax.swing.*;
import java.awt.*;

public class MoveWindowController {

    // ----------------------------- Public API  -----------------------------

    RichTextField xSizeRich, ySizeRich, xOffsetRich, yOffsetRich;

    // -------------------------- Private variables  -------------------------
    private JFrame frame;
    private Rectangle start;
    private SimpleObjectProperty<Rectangle> windowPosition;
    @FXML
    private Button okButton;

    // --------------------------- Private methods  --------------------------
    @FXML
    private Button cancelButton;
    @FXML
    private TextField xSize, ySize, xOffset, yOffset;

    // ------------------------------- Objects  ------------------------------

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