package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GradientEditor {
    private final GradientEditorController windowController;
    private final Stage primaryStage;

    public GradientEditor() {
        FXMLLoader load = new FXMLLoader();
        Parent loader = new Group();
        load.setLocation(getClass().getResource("GradientEditor.fxml"));
        try {
            loader = load.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        windowController = load.getController();
    }

    public void exec(SimpleObjectProperty<HintGradient> gradient, double minVectorHint, double maxVectorHint) {
        windowController.setup(primaryStage, gradient, minVectorHint, maxVectorHint);
        primaryStage.showAndWait();
    }
}
