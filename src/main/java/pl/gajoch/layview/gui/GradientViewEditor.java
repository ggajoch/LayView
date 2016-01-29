package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GradientViewEditor {
    private final GradientViewEditorController windowController;
    private final Stage primaryStage;

    public GradientViewEditor() throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("GradientViewEditor.fxml"));
        Parent loader = load.load();

        primaryStage = new Stage();
        primaryStage.setTitle("Edit");
        primaryStage.setScene(new Scene(loader));

        windowController = load.getController();
    }

    public void exec(GradientViewProperties properties) throws IOException {
        windowController.setup(primaryStage, properties);
        primaryStage.showAndWait();
    }
}
