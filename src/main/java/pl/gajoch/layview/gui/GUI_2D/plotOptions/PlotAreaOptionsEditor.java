package pl.gajoch.layview.gui.GUI_2D.plotOptions;

import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.graphics2d.options.PlotAreaOptions;
import pl.gajoch.layview.graphics2d.options.Scene2DOptions;
import pl.gajoch.layview.gui.common.movable.JFXPanelWindow;

public class PlotAreaOptionsEditor extends JFXPanelWindow<PlotAreaOptionsController> {
    public PlotAreaOptionsEditor() {
        super("Edit options", "PlotAreaOptions.fxml");
    }

    public void exec(SimpleObjectProperty<PlotAreaOptions> plotAreaOptions) {
        windowController.setup(frame, plotAreaOptions);
        open();
    }
}
