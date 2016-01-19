package pl.gajoch.layview.gui;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Gradient grad1;
    private Gradient grad2;
    private FileInput files;

    @FXML
    private SubScene scene1;
    @FXML
    private SubScene scene2;
    private GradientEditor edit1, edit2;


    public void setup(Stage stage) throws IOException {
        grad1 = new Gradient();
        grad1.add(new GradientPoint(0, Color.BLUE));
        grad1.add(new GradientPoint(1, Color.RED));
        grad2 = new Gradient();
        grad2.add(new GradientPoint(0, Color.BLACK));
        grad2.add(new GradientPoint(1, Color.WHITE));

        files = new FileInput();

        scene1.setFill(Color.BLUE);
        scene2.setFill(Color.RED);

        scene1.fillProperty().set(Color.RED);

        System.out.println("X");

        edit1 = new GradientEditor();
        edit2 = new GradientEditor();
    }

    @FXML
    private void Gradient1_handler() throws Exception {
        grad1 = edit1.exec(grad1, 1, 2);
    }

    @FXML
    private void Gradient2_handler() throws Exception {
        grad2 = edit2.exec(grad2, 3, 4);
    }

    @FXML
    private void file_select_handler() throws Exception {
        FileInputSelector edit = new FileInputSelector();
        files = edit.exec(files);
    }
}