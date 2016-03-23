package pl.gajoch.layview.gui.common.export;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pl.gajoch.layview.options.ExportOptions;
import pl.gajoch.layview.utils.GUIUtils;
import pl.gajoch.layview.utils.gui.RichTextField;

import javax.swing.*;
import java.io.File;

import static pl.gajoch.layview.utils.GUIUtils.showNumberFormatException;


public class ExportMenuController {
    private JFrame frame;
    private SimpleObjectProperty<ExportOptions> exportOptions;

    @FXML
    private TextField fileName;
    @FXML
    private TextField _FPS;

    private RichTextField FPS;

    public void setup(JFrame frame, SimpleObjectProperty<ExportOptions> exportOptions) {
        this.frame = frame;
        this.exportOptions = exportOptions;
        FPS = RichTextField.of(_FPS);
        set(exportOptions.get());
    }

    private void set(ExportOptions exportOptions) {
        this.fileName.setText(exportOptions.file.getAbsolutePath());
        this.FPS.set(exportOptions.FPS);
    }

    @FXML
    private void fileSelect_handler() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to export to...");

        fileChooser.setInitialDirectory(new File(this.fileName.getText()).getParentFile());
        File file = fileChooser.showOpenDialog(null);

        fileName.setText(file.getAbsolutePath());
    }

    @FXML
    private void ok_handler() {
        try {
            exportOptions.set(new ExportOptions(new File(fileName.getText()), FPS.getDouble()));
        } catch (NumberFormatException ex) {
            showNumberFormatException(ex);
            return;
        }
        Platform.runLater(() ->
                GUIUtils.closeJFrame(frame));
    }
}
