package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
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

        HintGradient grad1 = new HintGradient();
        HintGradient grad2 = new HintGradient();

        VectorProperties vectorProperties = new VectorProperties();
        GradientSurfacePointsList surface = new GradientSurfacePointsList();

        optionsProperty = new SimpleObjectProperty<>(
                new Scene3DOptions(2, 2, 1, 1, 0, 0, grad1, grad2));

        optionsProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("Recalculate!");
            //why not vectorProperties = newValue.vectorProperties?
            vectorProperties.tipRadius = newValue.tipRadius;
            vectorProperties.lenScale = newValue.lenScale;
            vectorProperties.tipLen = newValue.tipLen;
            vectorProperties.radius = newValue.radius;

            surface.GradientsHintReset();
            surface.GradientsHintCalculate();

            System.out.println("MAKS SETTED:"+grad1.getMaxVector());//NOT WORKING

            System.out.print("GRAD1: MAX: "+grad1.getHintMax()+"  MIN: "+grad1.getHintMin()+"\r\n");
            System.out.print("GRAD2: MAX: "+grad2.getHintMax()+"  MIN: "+grad2.getHintMin()+"\r\n");

            grad1.setMaxVector(grad1.getHintMax());
            grad1.setMinVector(grad1.getHintMin());
            grad2.setMaxVector(grad2.getHintMax());
            grad2.setMinVector(grad2.getHintMin());

            surface.GradientsApply();

            this.elements.getChildren().clear();
            this.elements.getChildren().add(new VectorSurface(surface, vectorProperties));
            //this.elements.getChildren().remove(0,1);
            System.out.println("END RECALCULATE");
            //after end recalculate displaying grad1 offset points... WHY and WHERE
        });


        List<MenuItem> menu = new ArrayList<>();

        MenuItem item1 = new MenuItem("File select...");
        item1.setOnAction(e -> {
            files = fileInputSelector.exec(files);
            files.forEach(System.out::println);
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
        this.elements.getChildren().addAll(new VectorSurface(surface, vectorProperties));
    }
}
