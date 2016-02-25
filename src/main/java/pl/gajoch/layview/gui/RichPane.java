package pl.gajoch.layview.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class RichPane {
    private final Pane pane;

    private RichPane(Pane pane) {
        this.pane = pane;
    }

    public static RichPane of(Pane pane) {
        return new RichPane(pane);
    }

    public void setFill(Paint color) {
        BackgroundFill myBF = new BackgroundFill(color, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        pane.setBackground(new Background(myBF));
    }

    public void setFill(GradientPoint point) {
        this.setFill(point.getColor());
    }
}