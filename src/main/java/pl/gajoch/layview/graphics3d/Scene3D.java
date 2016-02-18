package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import pl.gajoch.layview.gui.*;
import pl.gajoch.layview.utils.OMFData;
import pl.gajoch.layview.utils.OMFParser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Scene3D extends CameraSubScene {
    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    private UniformScale globalScale = new UniformScale();

    final HintGradient grad1 = new HintGradient();
    final HintGradient grad2 = new HintGradient();

    private Scene3DOptions scene3DOptions = new Scene3DOptions(1.5e-10, 2.0e-10, 1.0e-10, 1.0e-15, 1e10, 0, grad1, grad2);

    private AnimationTimer timer;

    ArrayList<GradientSurfacePointsList> surfaces = new ArrayList<>();

    ArrayList<Group> renderedSurfaces = new ArrayList<>();

    private long lastTime = 0;
    private int frameCount = 0;


    /*Vector vector = new Vector(new SurfacePoint(new Vec3d(0,0,0),new Vec3d(1,0,0), Color.DARKGREEN), new VectorProperties());
    Vector vector2 = new Vector(new SurfacePoint(new Vec3d(10,0,0),new Vec3d(0,10,0), Color.DARKGREEN), new VectorProperties());*/

    ArrayList<Vector> vectors = new ArrayList<>();

    public Scene3D(GraphicsWindowManager parent, double width, double height) {
        super(parent, width, height);

        fileInputSelector = new FileInputSelector();
        scene3DOptionsEditor = new Scene3DOptionsEditor();
        files = new FileInput();

        timer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                long currentTime = System.nanoTime();
                if (currentTime > lastTime + 0.3e8) {
                    long t0 = System.nanoTime();
                    lastTime = currentTime;
                    /*if (renderedSurfaces.size() == 0) return;
                    renderedSurfaces.get(frameCount).setVisible(false);
                    frameCount++;
                    if (frameCount >= renderedSurfaces.size()) frameCount = 0;
                    renderedSurfaces.get(frameCount).setVisible(true);
                    System.out.println("t="+(System.nanoTime()-t0)+"ns");*/
                    int i = 0;
                    for(Vector vector:vectors) {
                        i++;
                        vector.updateValue(new Vec3d(0, 1e6 * Math.sin(currentTime / 1e9)*i/vectors.size(), 0));
                    }
                }
            }
        };

        for (double x = -1e-8; x <= 1e-8; x += 1e-9) {
            for (double y = -1e-8; y <= 1e-8; y += 1e-9) {
                for (double z = -1e-8; z <= 1e-8; z += 1e-9) {
                    //if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= 1e-8) {
                        vectors.add(new Vector(new SurfacePoint(new Vec3d(x, y, z), new Vec3d(x * 1e14, y * 1e14, z * 1e14), Color.GREEN), scene3DOptions.vectorProperties));
                    //}
                }
            }
        }

        System.out.println("SIZE: "+vectors.size());

        timer.start();


        optionsProperty = new SimpleObjectProperty<>(
                scene3DOptions);

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

        this.elements.getTransforms().add(globalScale);

        globalScale.set(scene3DOptions.globalScale);

        //generateExample();

        //this.elements.getChildren().addAll(new VectorSurface(surface, new VectorProperties()));

        //onOptionsChanged(scene3DOptions);

        this.elements.getChildren().setAll(vectors);

    }

    private void onOptionsChanged(Scene3DOptions newValue) {
        System.out.println("Recalculate!");

        scene3DOptions = newValue;

        globalScale.set(newValue.globalScale);

        renderedSurfaces.clear();


        boolean isFirst = true;
        for (GradientSurfacePointsList surface : surfaces) {

            surface.gradients.clear();
            surface.gradients.add(newValue.gradient1);
            surface.gradients.add(newValue.gradient2);
            if (isFirst) {
                surface.GradientsHintReset();
                isFirst = false;
            }
            surface.GradientsHintCalculate();
            surface.GradientsApply();
            VectorSurface localSurface = new VectorSurface(surface, newValue.vectorProperties);
            localSurface.setVisible(false);
            renderedSurfaces.add(localSurface);

            System.out.print(".");
        }

        System.out.print("GRAD1: MAX: " + newValue.gradient1.getHintMax() + "  MIN: " + newValue.gradient1.getHintMin() + "\r\n");
        System.out.print("GRAD2: MAX: " + newValue.gradient2.getHintMax() + "  MIN: " + newValue.gradient2.getHintMin() + "\r\n");

        //this.elements.getChildren().setAll(new VectorSurface(surfaces.get(0), newValue.vectorProperties));

        this.elements.getChildren().setAll(renderedSurfaces);
        //this.elements.getChildren().remove(0,1);
        System.out.println("END RECALCULATE");
        //after end recalculate displaying grad1 offset points... WHY and WHERE
        //tu masz hinty policzone także
    }

    private void onFileSelect() {
        timer.stop();
        List<OMFData> omfDatas = fileInputSelector.
                exec(files).
                stream().
                map(file -> new OMFParser().parseFile(file)).
                collect(Collectors.toList());

        /*omfDatas.stream().findFirst().get().points.forEach(surfacePoint -> {
            System.out.println("point: " + surfacePoint.position);
        });*/

        /*surface.clear();
        surface.addAll(omfDatas.stream().findFirst().get().points);*/


        surfaces.clear();
        omfDatas.stream().forEach(surfaceData -> {

            GradientSurfacePointsList currentSurface = new GradientSurfacePointsList();
            currentSurface.addAll(surfaceData.points);
            surfaces.add(currentSurface);

        });

        onOptionsChanged(scene3DOptions);

        frameCount = 0;
        timer.start();
    }

    private void generateExample() {
        //deal with dat junk

        grad1.setReference(new Vec3d(1, 0, 0));

        grad2.setReference(new Vec3d(0, 0, 1));

        grad1.add(new GradientPoint(-1.0, Color.BLUE));
        grad1.add(new GradientPoint(0, Color.WHITE));
        grad1.add(new GradientPoint(1.0, Color.RED));

        grad2.add(new GradientPoint(0, Color.WHITE));
        grad2.add(new GradientPoint(1.0, Color.GREEN));

        GradientSurfacePointsList surface = new GradientSurfacePointsList();

        surface.gradients.add(grad1);
        surface.gradients.add(grad2);

        for (double x = -1e-8; x <= 1e-8; x += 1e-9) {
            for (double y = -1e-8; y <= 1e-8; y += 1e-9) {
                for (double z = 0; z <= 1e-8; z += 1e-9) {
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= 1e-8) {
                        surface.add(new SurfacePoint(new Vec3d(x, y, z), new Vec3d(x * 1e14, y * 1e14, z * 1e14), new Color(y / 200 + 0.5, 0, -y / 200 + 0.5, 1)));
                    }
                }
            }
        }

        surface.GradientsHintReset();
        surface.GradientsHintCalculate();

        System.out.print("GRAD1: MAX: " + grad1.getHintMax() + "  MIN: " + grad1.getHintMin() + "\r\n");
        System.out.print("GRAD2: MAX: " + grad2.getHintMax() + "  MIN: " + grad2.getHintMin() + "\r\n");

        grad1.setMaxVector(grad1.getHintMax());
        grad1.setMinVector(grad1.getHintMin());
        grad2.setMaxVector(grad2.getHintMax());
        grad2.setMinVector(grad2.getHintMin());

        surface.GradientsApply();

        surfaces.clear();
        surfaces.add(surface);

    }
}
