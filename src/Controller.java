import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class Controller {
    private Gradient grad1;
    private Gradient grad2;

    public void setup() {
        grad1 = new Gradient();
        grad1.add(new GradientPoint(0, Color.BLUE));
        grad1.add(new GradientPoint(1, Color.RED));
        grad2 = new Gradient();
        grad2.add(new GradientPoint(0, Color.BLACK));
        grad2.add(new GradientPoint(1, Color.WHITE));
    }

    @FXML
    private void Gradient1_handler() throws Exception {
        GradientEditor edit = new GradientEditor();
        grad1 = edit.exec(grad1);
    }

    @FXML
    private void Gradient2_handler() throws Exception {
        GradientEditor edit = new GradientEditor();
        grad2 = edit.exec(grad2);
    }
}