import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GradientEditor {
    public Gradient exec(Gradient gradient) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("GradientEditor.fxml"));
        Parent loader = load.load();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        GradientEditorController windowController = load.getController();
        windowController.setup(primaryStage, gradient, 1, 2);

        primaryStage.showAndWait();
        return windowController.getGradient();
    }
}
