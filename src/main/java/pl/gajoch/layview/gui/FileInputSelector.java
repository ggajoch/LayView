package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.gajoch.layview.utils.OMFData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class FileInputSelector extends JFXPanelWindow<FileInputSelectorController> {
    public FileInputSelector() {
        super("Select files...", "FileInputSelector.fxml");
    }

    public void exec(SimpleObjectProperty<FileInput> files) {
        windowController.setup(frame, files);
        open();
    }
}
