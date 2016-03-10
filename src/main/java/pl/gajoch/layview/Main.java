package pl.gajoch.layview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pl.gajoch.layview.gui.Controller;
import pl.gajoch.layview.gui.GraphicsWindowManager;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    public static void main(String[] args) {
        new Thread(() -> {
            launch(args);
        }).start();
        GraphicsWindowManager graphicsWindowManager = new GraphicsWindowManager();
    }
}

