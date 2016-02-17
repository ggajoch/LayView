package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import pl.gajoch.layview.gui.*;
import pl.gajoch.layview.utils.OMFData;
import pl.gajoch.layview.utils.OMFParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scene3D extends CameraSubScene {
    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    GradientSurfacePointsList surface = new GradientSurfacePointsList();

    public Scene3D(GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);

        fileInputSelector = new FileInputSelector();
        scene3DOptionsEditor = new Scene3DOptionsEditor();
        files = new FileInput();

        final HintGradient grad1 = new HintGradient();
        final HintGradient grad2 = new HintGradient();


        optionsProperty = new SimpleObjectProperty<>(
                new Scene3DOptions(2, 2, 1, 1, 0, 0, grad1, grad2));

        optionsProperty.addListener((observable, oldValue, newValue) -> {
            onOptionsChanged(newValue);
        });

        List<MenuItem> menu = new ArrayList<>();

        MenuItem item1 = new MenuItem("File select...");
        item1.setOnAction(e -> {
            onFileSelect();
        });
        menu.add(item1);

        MenuItem item2 = new MenuItem("Options...");
        item2.setOnAction(e -> scene3DOptionsEditor.exec(optionsProperty));
        menu.add(item2);

        this.generateContextMenu(menu);

        grad1.setReference(new Vec3d(1,0,0));

        grad2.setReference(new Vec3d(0,0,1));

        grad1.add(new GradientPoint(-1.0, Color.BLUE));
        grad1.add(new GradientPoint(0,Color.WHITE));
        grad1.add(new GradientPoint(1.0,Color.RED));

        grad2.add(new GradientPoint(0,Color.WHITE));
        grad2.add(new GradientPoint(1.0,Color.GREEN));

        surface.gradients.add(grad1);
        surface.gradients.add(grad2);

        for (double x = -100; x <= 100; x += 10) {
            for (double y = -100; y <= 100; y += 10) {
                for (double z = 0; z <= 100; z += 10) {
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= 100) {
                        surface.add(new SurfacePoint(new Vec3d(x, y, z), new Vec3d(x / 10, y / 10, z / 10), new Color(y / 200 + 0.5, 0, -y / 200 + 0.5, 1)));
                    }
                }
            }
        }

        surface.GradientsHintReset();
        surface.GradientsHintCalculate();

        System.out.print("GRAD1: MAX: "+grad1.getHintMax()+"  MIN: "+grad1.getHintMin()+"\r\n");
        System.out.print("GRAD2: MAX: "+grad2.getHintMax()+"  MIN: "+grad2.getHintMin()+"\r\n");

        grad1.setMaxVector(grad1.getHintMax());
        grad1.setMinVector(grad1.getHintMin());
        grad2.setMaxVector(grad2.getHintMax());
        grad2.setMinVector(grad2.getHintMin());

        surface.GradientsApply();
        this.elements.getChildren().addAll(new VectorSurface(surface, new VectorProperties()));
    }

    private void onOptionsChanged(Scene3DOptions newValue) {
        System.out.println("Recalculate!");

        surface.gradients.clear();
        surface.gradients.add(newValue.gradient1);
        surface.gradients.add(newValue.gradient2);

        surface.GradientsHintReset();
        surface.GradientsHintCalculate();

        surface.GradientsApply();

        this.elements.getChildren().setAll(new VectorSurface(surface, newValue.vectorProperties));
        //this.elements.getChildren().remove(0,1);
        System.out.println("END RECALCULATE");
        //after end recalculate displaying grad1 offset points... WHY and WHERE
        //tu masz hinty policzone także
    }

    private void onFileSelect() {
        List<OMFData> omfDatas = fileInputSelector.
                exec(files).
                stream().
                map(file -> new OMFParser().parseFile(file)).
                collect(Collectors.toList());

        // what next?
        GradientSurfacePointsList surf = new GradientSurfacePointsList();
        HintGradient gradient1 = new HintGradient();
        HintGradient gradient2 = new HintGradient();

        gradient1.setReference(new Vec3d(1,0,0));

        gradient2.setReference(new Vec3d(0,0,1));

        gradient1.add(new GradientPoint(-1.0, Color.BLUE));
        gradient1.add(new GradientPoint(0,Color.WHITE));
        gradient1.add(new GradientPoint(1.0,Color.RED));

        gradient2.add(new GradientPoint(0,Color.WHITE));
        gradient2.add(new GradientPoint(1.0,Color.GREEN));

        surf.gradients.add(gradient1);
        surf.gradients.add(gradient2);

        omfDatas.stream().findFirst().get().points.forEach(surfacePoint -> {
            System.out.println("point: " + surfacePoint.position);
        });
        surf.GradientsHintReset();
        surf.GradientsHintCalculate();

        System.out.print("GRAD1: MAX: "+gradient1.getHintMax()+"  MIN: "+gradient1.getHintMin()+"\r\n");
        System.out.print("GRAD2: MAX: "+gradient2.getHintMax()+"  MIN: "+gradient2.getHintMin()+"\r\n");

        gradient1.setMaxVector(gradient1.getHintMax());
        gradient1.setMinVector(gradient1.getHintMin());
        gradient2.setMaxVector(gradient2.getHintMax());
        gradient2.setMinVector(gradient2.getHintMin());

        surf.GradientsApply();

        surf.addAll(omfDatas.stream().findFirst().get().points);

        this.elements.getChildren().addAll(new VectorSurface(surf));
    }
}
