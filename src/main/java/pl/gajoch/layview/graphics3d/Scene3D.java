package pl.gajoch.layview.graphics3d;

import javafx.scene.control.MenuItem;
import pl.gajoch.layview.gui.FileInput;
import pl.gajoch.layview.gui.FileInputSelector;
import pl.gajoch.layview.gui.GraphicsWindowManager;

import java.util.ArrayList;
import java.util.List;

public class Scene3D extends CameraSubScene {

    private FileInputSelector fileInputSelector;
    private FileInput files;

    public Scene3D(GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);

        fileInputSelector = new FileInputSelector();
        files = new FileInput();

        MenuItem item1 = new MenuItem("File select...");
        item1.setOnAction(e -> {
            files = fileInputSelector.exec(files);
            files.forEach(System.out::println);
        });

        List<MenuItem> menu = new ArrayList<>();
        menu.add(item1);

        this.generateContextMenu(menu);
    }
}
