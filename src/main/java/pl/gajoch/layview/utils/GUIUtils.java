package pl.gajoch.layview.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class GUIUtils {
    public static void showErrorMessage(String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void showNumberFormatException(NumberFormatException exception) {
        String message = exception.getMessage();
        int begin = message.indexOf("\""), end = message.indexOf("\"", begin+1);
        CharSequence name = message.subSequence(begin+1, end);
        System.out.println("a = " + begin + ", b = " + end + " name = " + name);
        showErrorMessage("Bad number format!", "Cannot parse \"" +  name + "\"");
    }
    
    public static Double toDouble(TextInputControl field) throws NumberFormatException {
        return Double.valueOf(field.getText());
    }

    public static void setTextField(TextField field, Double value) {
        field.setText(Double.toString(value));
    }

    public static void closeJFrame(JFrame frame) {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}

