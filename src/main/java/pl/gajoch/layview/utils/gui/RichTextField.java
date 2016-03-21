package pl.gajoch.layview.utils.gui;

import javafx.scene.control.TextField;

public class RichTextField {

    public static RichTextField of(TextField field) {
        return new RichTextField(field);
    }

    public double getDouble() throws NumberFormatException {
        return Double.valueOf(textField.getText());
    }

    public int getInt() throws NumberFormatException {
        return Integer.valueOf(textField.getText());
    }

    public void set(double value) {
        textField.setText(Double.toString(value));
    }

    public void set(int value) {
        textField.setText(Integer.toString(value));
    }

    public void setInteger(Number number) {
        this.set(number.intValue());
    }
    public final TextField get() {
        return textField;
    }

    private final TextField textField;

    private RichTextField(TextField textField) {
        this.textField = textField;
    }

    public void disable() {
        textField.setText(" ");
        textField.setDisable(true);
    }

    public void enable() {
        textField.setDisable(false);
    }
}
