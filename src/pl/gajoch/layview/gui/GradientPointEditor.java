package pl.gajoch.layview.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GradientPointEditor {
    private final GradientPointEditorController windowController;
    private final Stage primaryStage;

    public GradientPointEditor() throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("GradientPointEditor.fxml"));
        Parent loader = load.load();

        primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        windowController = load.getController();
    }

    public GradientPoint exec(GradientPoint point) {
        windowController.setup(primaryStage, point);
        primaryStage.showAndWait();
        return windowController.getPoint();
    }
}


