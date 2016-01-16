import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Utils {
    public static void showErrorMessage(String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
    
    public static Double toDouble(TextField field) throws NumberFormatException {
        return Double.valueOf(field.getText());
    }
}
