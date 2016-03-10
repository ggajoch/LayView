package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.gajoch.layview.utils.OMFData;

import java.io.IOException;
import java.util.List;

public class FileInputSelector {
    private Stage primaryStage;
    private FileInputSelectorController windowController;

    public FileInputSelector() {
        Platform.runLater(() -> {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("FileInputSelector.fxml"));
            Parent loader = new Group();
            try {
                loader = load.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            primaryStage = new Stage();
            primaryStage.setTitle("Edit point");
            primaryStage.setScene(new Scene(loader));

            windowController = load.getController();
        });
    }

    public FileInput exec(FileInput files) {
        windowController.setup(primaryStage, files);
        primaryStage.showAndWait();
        return windowController.parseFiles();
    }
}
