package pl.gajoch.layview.gui;

import javafx.scene.control.TextField;

public class RichTextField {

    static RichTextField of(TextField field) {
        return new RichTextField(field);
    }

    public double getDouble() {
        return Double.valueOf(textField.getText());
    }

    public void set(double value) {
        textField.setText(Double.toString(value));
    }

    private final TextField textField;

    private RichTextField(TextField textField) {
        this.textField = textField;
    }
}
