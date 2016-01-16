import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GradientPointEditor {
    public GradientPoint exec(GradientPoint point) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("GradientPointEditor.fxml"));
        Parent loader = load.load();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        GradientPointEditorController windowController = load.getController();
        windowController.setup(primaryStage, point);

        primaryStage.showAndWait();
        return windowController.getPoint();
    }
}


