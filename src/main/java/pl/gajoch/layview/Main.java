package pl.gajoch.layview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pl.gajoch.layview.gui.Controller;
import pl.gajoch.layview.scheduler.Scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    Timer timer;
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
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                while(true) {
                    Scheduler.start();
                    //System.out.println("DONE");
                }
            }
        }, 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

