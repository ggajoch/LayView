package pl.gajoch.layview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pl.gajoch.layview.gui.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("gui/Main.fxml"));
        Parent loader = load.load();

        primaryStage.setTitle("pl.gajoch.layview.Main");
        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);

        Controller windowController = load.getController();
        windowController.setup(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

