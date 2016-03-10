package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene3DOptionsEditor {
    private Scene3DOptionsController windowController;
    private Stage primaryStage;

    public Scene3DOptionsEditor() {
        Platform.runLater(() -> {
            FXMLLoader load = new FXMLLoader();
            Parent loader = new Group();
            try {
                load.setLocation(getClass().getResource("Scene3DOptionsWindow.fxml"));
                loader = load.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            primaryStage = new Stage();
            primaryStage.setTitle("Edit options");
            primaryStage.setScene(new Scene(loader));

            windowController = load.getController();
        });
    }

    public void exec(SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        windowController.setup(primaryStage, scene3DOptions);
        primaryStage.showAndWait();
    }
}
