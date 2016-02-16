package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import pl.gajoch.layview.gui.*;
import pl.gajoch.layview.utils.OMFData;
import pl.gajoch.layview.utils.OMFParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        optionsProperty = new SimpleObjectProperty<>(
                new Scene3DOptions(0, 0, 0, 0, 0, 0, new Gradient(), new Gradient()));

        optionsProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("Recalculate!");
        });


        List<MenuItem> menu = new ArrayList<>();

        MenuItem item1 = new MenuItem("File select...");
        item1.setOnAction(e -> {
            List<OMFData> omfDatas = fileInputSelector.
                    exec(files).
                    stream().
                    map(file -> new OMFParser().parseFile(file)).
                    collect(Collectors.toList());

            // what next?
            GradientSurfacePointsList surface = new GradientSurfacePointsList();
            HintGradient grad1 = new HintGradient();
            HintGradient grad2 = new HintGradient();

            grad1.setReference(new Vec3d(1,0,0));

            grad2.setReference(new Vec3d(0,0,1));

            grad1.add(new GradientPoint(-1.0, Color.BLUE));
            grad1.add(new GradientPoint(0,Color.WHITE));
            grad1.add(new GradientPoint(1.0,Color.RED));

            grad2.add(new GradientPoint(0,Color.WHITE));
            grad2.add(new GradientPoint(1.0,Color.GREEN));

            surface.gradients.add(grad1);
            surface.gradients.add(grad2);

            omfDatas.stream().findFirst().get().points.forEach(surfacePoint -> {
                System.out.println("point: " + surfacePoint.position);
            });
            surface.GradientsHintReset();
            surface.GradientsHintCalculate();

            System.out.print("GRAD1: MAX: "+grad1.getHintMax()+"  MIN: "+grad1.getHintMin()+"\r\n");
            System.out.print("GRAD2: MAX: "+grad2.getHintMax()+"  MIN: "+grad2.getHintMin()+"\r\n");

            grad1.setMaxVector(grad1.getHintMax());
            grad1.setMinVector(grad1.getHintMin());
            grad2.setMaxVector(grad2.getHintMax());
            grad2.setMinVector(grad2.getHintMin());

            surface.GradientsApply();

            surface.addAll(omfDatas.stream().findFirst().get().points);

        });
        menu.add(item1);

        MenuItem item2 = new MenuItem("Options...");
        item2.setOnAction(e -> scene3DOptionsEditor.exec(optionsProperty));
        menu.add(item2);

        this.generateContextMenu(menu);
    }
}
