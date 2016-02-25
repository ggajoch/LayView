package pl.gajoch.layview.gui;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import pl.gajoch.layview.videoExporter.VideoExporter;

import java.io.IOException;

public class Controller {
    @FXML
    private SubScene Scene3D;

    @FXML
    private MenuItem SnapMenu, SejvMenu;

    Stage primaryStage;

    GraphicsWindowManager graphicsWindowManager;

    public VideoExporter exporter;


    public void setup(Stage stage) throws IOException {
        this.primaryStage = stage;

        graphicsWindowManager = new GraphicsWindowManager(stage, Scene3D);

        exporter = new VideoExporter(Scene3D,"C:\\Users\\Piotr\\Desktop\\tmp\\OBRAZKEN",
                "C:\\Users\\Piotr\\Desktop\\tmp\\00test.avi",10,768,768);

        SnapMenu.setOnAction(event -> {
            exporter.saveSnapshot();
        });

        SejvMenu.setOnAction(event -> {
            exporter.closeVideo();
        });
    }

    @FXML
    private void Close_handler() {
        this.primaryStage.close();
    }

    @FXML
    private void Add2D_handler() {
        graphicsWindowManager.add();
    }

    @FXML
    private void Add3D_handler() {
        graphicsWindowManager.add3D();
    }
}