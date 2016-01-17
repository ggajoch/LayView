package pl.gajoch.layview.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FileInputSelector {
    private final Stage primaryStage;
    private final FileInputSelectorController windowController;

    public FileInputSelector() throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("FileInputSelector.fxml"));
        Parent loader = load.load();

        primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        windowController = load.getController();
    }

    public FileInput exec(FileInput files) {
        windowController.setup(primaryStage, files);
        primaryStage.showAndWait();
        return windowController.getFiles();
    }
}
