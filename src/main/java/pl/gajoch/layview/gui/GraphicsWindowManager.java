package pl.gajoch.layview.gui;

import com.sun.javafx.geom.Vec3d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.gajoch.layview.graphics2d.LineGraph;
import pl.gajoch.layview.graphics3d.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GraphicsWindowManager {
    Pane pane3D;
    ArrayList<MovableSubScene> subScenes;
    SimpleObjectProperty<MovableSubScene> actual_scene;
    SubScene subScene;
    Stage stage;

    public void clicked(MovableSubScene scene) {
        if (actual_scene.getValue() != null && actual_scene.getValue() == scene) {
            actual_scene.setValue(null);
        } else {
            actual_scene.setValue(scene);
        }
    }
    GraphicsWindowManager(Stage stage, SubScene subScene) {
        this.stage = stage;
        this.subScene = subScene;
        actual_scene = new SimpleObjectProperty<>();
        subScenes = new ArrayList<>();

        actual_scene.addListener((observable, oldValue, newValue) -> {
            try {
                recalculate();
            } catch (NumberFormatException ignored) {
            }
        });

        subScene.setOnMouseClicked(event -> {
            if( event.isStillSincePress() ) {
                actual_scene.setValue(null);
            }
        });

        pane3D = new Pane();
        subScene.setRoot(pane3D);
    }

    private void recalculate() {
        pane3D.getChildren().clear();
        pane3D.getChildren().addAll(
                subScenes.stream()
                        .map(scene -> scene.scene)
                        .collect(Collectors.toList()));
    }

    private void recalculateWindowSize() {
        try {
            double maxWidth = subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().xOffset + sc.getPosition().xSize)
                    .max().getAsDouble();

            double maxHeight = subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().yOffset + sc.getPosition().ySize)
                    .max().getAsDouble();

            subScene.setWidth(maxWidth);
            subScene.setHeight(maxHeight);
            System.out.println("size: " + maxWidth + maxHeight);
        } catch (NoSuchElementException ignored) {
            subScene.setWidth(0);
            subScene.setHeight(0);
        }
        stage.sizeToScene();
    }

    private void addSizeRecalculations(MovableSubScene scene) {
        ChangeListener<? super Number> handler = (observable1, oldValue1, newValue1) -> {
            recalculate();
            recalculateWindowSize();
        };

        scene.scene.widthProperty().addListener(handler);
        scene.scene.heightProperty().addListener(handler);
        scene.scene.layoutXProperty().addListener(handler);
        scene.scene.layoutYProperty().addListener(handler);
    }

    public void add() {
        GradientView view = new GradientView(this, 100, 100);
        addSizeRecalculations(view);
        subScenes.add(view);
        LineGraph line = new LineGraph();
        view.scene.rootProperty().setValue(line);
        recalculate();
        recalculateWindowSize();
    }

    public void add3D() {
        CameraSubScene scene = new Scene3D(this,600,600);
        GradientSurfacePointsList surface = new GradientSurfacePointsList();

        HintGradient grad1 = new HintGradient();
        HintGradient grad2 = new HintGradient();

        grad1.setReference(new Vec3d(1,0,0));

        grad2.setReference(new Vec3d(0,0,1));

        grad1.add(new GradientPoint(-1.0,Color.BLUE));
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

        addSizeRecalculations(scene);
        surface.GradientsHintReset();
        surface.GradientsHintCalculate();

        System.out.print("GRAD1: MAX: "+grad1.getHintMax()+"  MIN: "+grad1.getHintMin()+"\r\n");
        System.out.print("GRAD2: MAX: "+grad2.getHintMax()+"  MIN: "+grad2.getHintMin()+"\r\n");

        grad1.setMaxVector(grad1.getHintMax());
        grad1.setMinVector(grad1.getHintMin());
        grad2.setMaxVector(grad2.getHintMax());
        grad2.setMinVector(grad2.getHintMin());

        surface.GradientsApply();


        scene.elements.getChildren().addAll(new VectorSurface(surface));
        subScenes.add(scene);
        recalculate();
        recalculateWindowSize();
    }

    public void del(MovableSubScene scene) {
        if( subScenes.contains(scene) ) {
            subScenes.remove(scene);
        }
        recalculate();
        recalculateWindowSize();
    }
}
