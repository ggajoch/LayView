import javafx.scene.control.Alert;

public class Utils {
    public static void showErrorMessage(String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
