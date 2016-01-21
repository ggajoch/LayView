package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GradientEditor {
    private final GradientEditorController windowController;
    private final Stage primaryStage;

    public GradientEditor() throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("GradientEditor.fxml"));
        Parent loader = load.load();

        primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        windowController = load.getController();
    }

    public Gradient exec(SimpleObjectProperty<Gradient> gradient, double minVectorHint, double maxVectorHint) throws Exception {
        windowController.setup(primaryStage, gradient, minVectorHint, maxVectorHint);
        primaryStage.showAndWait();
        return windowController.getGradient();
    }
}
