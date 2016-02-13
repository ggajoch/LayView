package pl.gajoch.layview;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import pl.gajoch.layview.gui.Controller;
import pl.gajoch.layview.videoExporter.videoExport;

import javax.imageio.ImageIO;
import javax.media.MediaLocator;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main extends Application {
    int number = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("gui/Main.fxml"));
        Parent loader = load.load();

        primaryStage.setTitle("pl.gajoch.layview.Main");
        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case S:
                    WritableImage image = new WritableImage(768, 768);
                    loader.snapshot(null,image);
                    File file = new File("C:\\Users\\Piotr\\Desktop\\tmp\\OBRAZKEN\\plik"+number+".jpeg");
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpeg", file);
                    }catch (IOException e) {
//                             TODO: handle exception here
                    }
                    number++;
                    break;
                case V:
                    videoExport exporter = new videoExport();
                    Vector inputFiles = new Vector();

                    for(int i = 0 ; i < number ; i++) inputFiles.add(new String("C:\\Users\\Piotr\\Desktop\\tmp\\OBRAZKEN\\plik"+i+".jpeg"));

                    exporter.createMovie("C:\\Users\\Piotr\\Desktop\\tmp\\OBRAZKEN\\00test.mov",768,768,10,inputFiles);
                    break;
            }
        });

        Controller windowController = load.getController();
        windowController.setup(primaryStage);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

