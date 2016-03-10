package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MoveWindowEditor {
    private MoveWindowController windowController;
    private Stage primaryStage;

    public MoveWindowEditor() {
        Platform.runLater(() -> {
            FXMLLoader load = new FXMLLoader();
            Parent loader = new Group();
            try {
                load.setLocation(getClass().getResource("MoveWindow.fxml"));
                loader = load.load();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            primaryStage = new Stage();
            primaryStage.setTitle("Edit position");
            primaryStage.setScene(new Scene(loader));

            windowController = load.getController();
        });
    }

    public void exec(SimpleObjectProperty<Rectangle> windowPosition) {
        windowController.setup(primaryStage, windowPosition);
        primaryStage.showAndWait();
    }
}
