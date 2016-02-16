package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.gajoch.layview.videoExporter.VideoExporter;

import java.io.IOException;

public class Controller {
    private Gradient grad1;
    private Gradient grad2;
    private FileInput files;

    @FXML
    private SubScene Scene3D;

    @FXML
    private Button add, del, add2, SNAP, SEJV;

    @FXML
    private TextField xPos;
    @FXML
    private TextField yPos;
    @FXML
    private TextField xSize;
    @FXML
    private TextField ySize;

    private GradientEditor edit1, edit2;

    Stage primaryStage;

    GraphicsWindowManager graphicsWindowManager;

    public VideoExporter exporter;




    public void setup(Stage stage) throws IOException {
        this.primaryStage = stage;
        grad1 = new Gradient();
        grad1.add(new GradientPoint(0, Color.BLUE));
        grad1.add(new GradientPoint(1, Color.RED));
        grad2 = new Gradient();
        grad2.add(new GradientPoint(0, Color.BLACK));
        grad2.add(new GradientPoint(1, Color.WHITE));
        edit1 = new GradientEditor();
        edit2 = new GradientEditor();

        files = new FileInput();


        graphicsWindowManager = new GraphicsWindowManager(stage, Scene3D);

        add.setOnAction(event -> {
            graphicsWindowManager.add();
        });

        del.setOnAction(event -> {
            graphicsWindowManager.del();
        });

        add2.setOnAction(event -> {
            graphicsWindowManager.add3D();
        });

        exporter = new VideoExporter(Scene3D,"C:\\Users\\Piotr\\Desktop\\tmp\\OBRAZKEN",
                "C:\\Users\\Piotr\\Desktop\\tmp\\00test.avi",10,768,768);

        SNAP.setOnAction(event -> {
            exporter.saveSnapshot();
        });

        SEJV.setOnAction(event -> {
            exporter.closeVideo();
        });
    }

    void printGradient(Gradient x) {
        System.out.println("\n\nhandler!");
    }

    @FXML
    private void Gradient1_handler() throws Exception {
        SimpleObjectProperty<Gradient> g = new SimpleObjectProperty<>(grad1);
        g.addListener((observable, oldValue, newValue) -> printGradient(newValue));

        edit1.exec((SimpleObjectProperty<Gradient>)graphicsWindowManager.actual_scene.getValue().properties.getOrDefault("grad", new Integer(1)), 0, 0);
    }

    @FXML
    private void Gradient2_handler() throws Exception {
        SimpleObjectProperty<Gradient> g = new SimpleObjectProperty<>(grad2);
        g.addListener((observable, oldValue, newValue) -> printGradient(newValue));
        grad2 = edit2.exec(g, 3, 4);
    }

    @FXML
    private void file_select_handler() throws Exception {
        FileInputSelector edit = new FileInputSelector();
        files = edit.exec(files);
    }
}