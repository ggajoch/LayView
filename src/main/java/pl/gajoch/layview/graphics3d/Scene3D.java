package pl.gajoch.layview.graphics3d;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.MenuItem;
import pl.gajoch.layview.gui.*;

import java.util.ArrayList;
import java.util.List;

public class Scene3D extends CameraSubScene {

    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    public Scene3D(GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);

        fileInputSelector = new FileInputSelector();
        scene3DOptionsEditor = new Scene3DOptionsEditor();
        files = new FileInput();
        optionsProperty = new SimpleObjectProperty<>(new Scene3DOptions(0, 0, 0, 0, 0, 0));

        optionsProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("Recalculate!");
        });


        List<MenuItem> menu = new ArrayList<>();

        MenuItem item1 = new MenuItem("File select...");
        item1.setOnAction(e -> {
            files = fileInputSelector.exec(files);
            files.forEach(System.out::println);
        });
        menu.add(item1);

        MenuItem item2 = new MenuItem("Options...");
        item2.setOnAction(e -> {
            scene3DOptionsEditor.exec(optionsProperty);
        });
        menu.add(item2);

        this.generateContextMenu(menu);
    }
}
