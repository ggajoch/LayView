package pl.gajoch.layview.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class GUIUtils {
    public static void showErrorMessage(String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
    
    public static Double toDouble(TextInputControl field) throws NumberFormatException {
        return Double.valueOf(field.getText());
    }

    public static void setTextField(TextField field, Double value) {
        field.setText(Double.toString(value));
    }


}

