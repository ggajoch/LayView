package pl.gajoch.layview.gui.common.fileInput;

import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.gui.common.movable.JFXPanelWindow;
import pl.gajoch.layview.options.FileInput;

public class FileInputSelector extends JFXPanelWindow<FileInputSelectorController> {
    public FileInputSelector() {
        super("Select files...", "FileInputSelector.fxml");
    }

    public void exec(SimpleObjectProperty<FileInput> files) {
        windowController.setup(frame, files);
        open();
    }
}
