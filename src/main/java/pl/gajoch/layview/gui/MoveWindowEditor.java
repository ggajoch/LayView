package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MoveWindowEditor {
    private final MoveWindowController windowController;
    private final Stage primaryStage;

    public MoveWindowEditor() {
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
    }

    public WindowPosition exec(SimpleObjectProperty<WindowPosition> windowPosition) {
        windowController.setup(primaryStage, windowPosition);
        primaryStage.showAndWait();
        return windowController.getWindowPosition();
    }
}
