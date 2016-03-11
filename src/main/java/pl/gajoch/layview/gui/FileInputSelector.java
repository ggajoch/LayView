package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;

public class FileInputSelector extends JFXPanelWindow<FileInputSelectorController> {
    public FileInputSelector() {
        super("Select files...", "FileInputSelector.fxml");
    }

    public void exec(SimpleObjectProperty<FileInput> files) {
        windowController.setup(frame, files);
        open();
    }
}
