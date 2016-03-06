package pl.gajoch.layview.graphics3d;

import com.jogamp.opengl.util.FPSAnimator;
import com.sun.javafx.geom.Vec3d;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingNode;
import javafx.scene.Group;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pl.gajoch.layview.gui.*;
import pl.gajoch.layview.utils.OMFData;
import pl.gajoch.layview.utils.OMFParser;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JOGLScene extends MovableSubScene {
    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    private UniformScale globalScale = new UniformScale();

    final HintGradient grad1 = new HintGradient();
    final HintGradient grad2 = new HintGradient();

    private volatile Scene3DOptions scene3DOptions = new Scene3DOptions(1.5e-10, 2.0e-10, 1.0e-10, 1.0e-15, new Vec3d(1, 1, 1), 1e10, 0, grad1, grad2);

    ArrayList<GradientSurfacePointsList> surfaces = new ArrayList<>();

    ArrayList<Group> renderedSurfaces = new ArrayList<>();

    private long lastTime = 0;
    private int frameCount = 0;


    /*Vector vector = new Vector(new SurfacePoint(new Vec3d(0,0,0),new Vec3d(1,0,0), Color.DARKGREEN), new VectorProperties());
    Vector vector2 = new Vector(new SurfacePoint(new Vec3d(10,0,0),new Vec3d(0,10,0), Color.DARKGREEN), new VectorProperties());*/

    ArrayList<Vector> vectors = new ArrayList<>();


    GLCanvas glcanvas;


    public JOGLScene(GraphicsWindowManager parent, int width, int height) {
        super(parent, width, height);

        fileInputSelector = new FileInputSelector();
        scene3DOptionsEditor = new Scene3DOptionsEditor();
        files = new FileInput();

        optionsProperty = new SimpleObjectProperty<>(
                scene3DOptions);

        optionsProperty.addListener((observable, oldValue, newValue) -> {
            onOptionsChanged(newValue);
        });

        List<JMenuItem> menu = new ArrayList<>();

        JMenuItem item1 = new JMenuItem("File select...");
        item1.addActionListener(e -> {
            onFileSelect();
        });
        menu.add(item1);

        JMenuItem item2 = new JMenuItem("Options...");
        item2.addActionListener(e -> {
            Platform.runLater(() -> {
                scene3DOptionsEditor.exec(optionsProperty);
            });
        });
        menu.add(item2);

        this.generateContextMenu(menu);

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        glcanvas = new GLCanvas(capabilities);


        SimpleJOGL cube = new SimpleJOGL();
        glcanvas.addGLEventListener(cube);
        glcanvas.setSize(width, height);
        //glcanvas.setAutoSwapBufferMode(true);

        glcanvas.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                cube.mouseWheelMoved(e);
            }
        });

        glcanvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                cube.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                cube.mouseMoved(e);
            }
        });

        glcanvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("clicked!");
                System.out.println(me.getButton());
                if (me.getButton() == MouseEvent.BUTTON3) {
                    openContextMenu(me);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("B");
                cube.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("C");
                cube.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("D");
                cube.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("E");
                cube.mouseExited(e);
            }
        });

        SwingUtilities.invokeLater(() ->
            this.scene.add(glcanvas));

        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();
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

//        this.elements.getChildren().setAll(renderedSurfaces);
        //this.elements.getChildren().remove(0,1);
        System.out.println("END RECALCULATE");
        //after end recalculate displaying grad1 offset points... WHY and WHERE
        //tu masz hinty policzone także
    }

    private void onFileSelect() {
//        timer.stop();
        Platform.runLater(() -> {
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
            //        timer.start();
        });
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
