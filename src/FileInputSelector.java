import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileInputSelector {
    public FileInput exec(FileInput files) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("FileInputSelector.fxml"));
        Parent loader = load.load();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Edit point");
        primaryStage.setScene(new Scene(loader));

        FileInputSelectorController windowController = load.getController();
        windowController.setup(primaryStage, files);

        primaryStage.showAndWait();
        return windowController.getFiles();
    }
}
