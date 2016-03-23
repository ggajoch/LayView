package pl.gajoch.layview.gui.common.export;

import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.gui.common.movable.JFXPanelWindow;
import pl.gajoch.layview.options.ExportOptions;

public class ExportMenu extends JFXPanelWindow<ExportMenuController> {
    public ExportMenu() {
        super("Export options", "VideoExport.fxml");
    }

    public void exec(SimpleObjectProperty<ExportOptions> exportOptions) {
        windowController.setup(frame, exportOptions);
        open();
    }
}
