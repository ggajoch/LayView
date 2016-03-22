package pl.gajoch.layview.gui.GUI_2D.plotOptions;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.gajoch.layview.graphics2d.options.PlotAreaOptions;
import pl.gajoch.layview.graphics2d.options.PlotOptions;
import pl.gajoch.layview.graphics2d.options.Scene2DOptions;
import pl.gajoch.layview.utils.GUIUtils;
import pl.gajoch.layview.utils.gui.RichTextField;

import javax.swing.*;

import java.awt.*;

import static pl.gajoch.layview.utils.GUIUtils.showNumberFormatException;

public class PlotAreaOptionsController {
    private JFrame frame;

    private SimpleObjectProperty<PlotAreaOptions> plotAreaOptions;
    private RichTextField minX, maxX, minY, maxY;

    @FXML
    private Button okButton, cancelButton;
    @FXML
    private TextField _minX, _maxX, _minY, _maxY;

    public void setup(JFrame frame, SimpleObjectProperty<PlotAreaOptions> plotAreaOptions) {
        this.frame = frame;
        this.plotAreaOptions = plotAreaOptions;
        minX = RichTextField.of(_minX);
        minX.set((int)plotAreaOptions.get().margins.getMinX());

        maxX = RichTextField.of(_maxX);
        maxX.set((int)plotAreaOptions.get().margins.getMaxX());

        minY = RichTextField.of(_minY);
        minY.set((int)plotAreaOptions.get().margins.getMinY());

        maxY = RichTextField.of(_maxY);
        maxY.set((int)plotAreaOptions.get().margins.getMaxY());
    }

    private void recalculate() throws NumberFormatException {
        plotAreaOptions.set(new PlotAreaOptions(new Rectangle(minX.getInt(), minY.getInt(),
                (maxX.getInt() - minX.getInt()), (maxY.getInt() - minY.getInt()))));
    }

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