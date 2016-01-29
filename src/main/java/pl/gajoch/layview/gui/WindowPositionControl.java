package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;

public class WindowPositionControl {
    final RichTextField xSize, ySize,
            xOffset, yOffset;

    public WindowPositionControl(TextField xSize, TextField ySize, TextField xOffset, TextField yOffset) {
        this.xSize = RichTextField.of(xSize);
        this.ySize = RichTextField.of(ySize);
        this.xOffset = RichTextField.of(xOffset);
        this.yOffset = RichTextField.of(yOffset);

        position = new WindowPosition(0,0,0,0);
        positionProperty = new SimpleObjectProperty<>(position);

        ChangeListener<? super String> handler = (observable1, oldValue1, newValue1) -> {
            try {
                positionProperty.setValue(this.get());
            } catch (NumberFormatException ignored) {
            }
        };

        this.xSize.get().textProperty().addListener(handler);
        this.ySize.get().textProperty().addListener(handler);
        this.xOffset.get().textProperty().addListener(handler);
        this.yOffset.get().textProperty().addListener(handler);
    }

    public void noValues() {
        xSize.disable();
        ySize.disable();
        xOffset.disable();
        yOffset.disable();
    }

    public void set(WindowPosition position) {
        xSize.enable();
        xSize.set(position.xSize);
        ySize.enable();
        ySize.set(position.ySize);
        xOffset.enable();
        xOffset.set(position.xOffset);
        yOffset.enable();
        yOffset.set(position.yOffset);
    }


    public WindowPosition get() {
        return new WindowPosition(xSize.getInt(), ySize.getInt(),
                xOffset.getInt(), yOffset.getInt());
    }

    WindowPosition position;
    SimpleObjectProperty<WindowPosition> positionProperty;

    public void addListener(ChangeListener<? super WindowPosition> listener) {
        System.out.println("AAA");
        positionProperty.addListener(listener);
//        xSize.get().textProperty().addListener(listener);
//        ySize.get().textProperty().addListener(listener);
//        xOffset.get().textProperty().addListener(listener);
//        yOffset.get().textProperty().addListener(listener);
    }
}
