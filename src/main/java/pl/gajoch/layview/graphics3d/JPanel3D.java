package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.gui.*;
import pl.gajoch.layview.scheduler.EventType;
import pl.gajoch.layview.scheduler.RepeatedEvent;
import pl.gajoch.layview.scheduler.Scheduler;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class JPanel3D extends MovableJPanel {
    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;
    private GLCanvas3DSurfaceViewer glcanvas;
    private RepeatedEvent timing;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    private volatile Scene3DOptions scene3DOptions = new Scene3DOptions(0.025, 0.025, 0.01, 0.1, new Vec3d(.1, .1, .1), 0.0, 30, new HintGradient(), new HintGradient());

    @Override
    public void fixCenter(Rectangle position) {
        glcanvas.reshape(0, 0, (int) position.getWidth(), (int) position.getHeight());
    }

    public JPanel3D(int width, int height) {
        super(width, height);

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

        glcanvas = new GLCanvas3DSurfaceViewer(capabilities);
        glcanvas.setOptions(scene3DOptions);
        glcanvas.addGLEventListener(glcanvas);
        glcanvas.setSize(width, height);

        glcanvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    openContextMenu(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        SwingUtilities.invokeLater(() ->
                this.add(glcanvas));

        timing = new RepeatedEvent(EventType.UPDATE3D, 10, 0) {
            @Override
            public void dispatch() {
                glcanvas.display();
            }

            @Override
            public void reset() {
                glcanvas.reset();
            }
        };

        Scheduler.schedule(timing);

        /*final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();*/

    }

    private void onOptionsChanged(Scene3DOptions newValue) {
        System.out.println("Recalculate!");

        glcanvas.setOptions(newValue);

        scene3DOptions = newValue;

        scene3DOptions.gradient1.clear();
        scene3DOptions.gradient2.clear();


        scene3DOptions.gradient1.setReference(new Vec3d(1, 0, 0));
        scene3DOptions.gradient1.add(new GradientPoint(-1.0, Color.RED));
        scene3DOptions.gradient1.add(new GradientPoint(0, Color.WHITE));
        scene3DOptions.gradient1.add(new GradientPoint(1.0, Color.BLUE));

        scene3DOptions.gradient2.setReference(new Vec3d(0, 1, 0));
        scene3DOptions.gradient2.add(new GradientPoint(-1.0, Color.GREEN));
        scene3DOptions.gradient2.add(new GradientPoint(1.0, Color.GOLD));

        glcanvas.presenter.gradients.clear();
        glcanvas.presenter.gradients.add(scene3DOptions.gradient1);
        //glcanvas.presenter.gradients.add(scene3DOptions.gradient2);

        glcanvas.presenter.gradientsHintReset();
        glcanvas.presenter.gradientsHintCalculate();

        scene3DOptions.gradient1.setMaxVector(scene3DOptions.gradient1.getHintMax());
        scene3DOptions.gradient1.setMinVector(scene3DOptions.gradient1.getHintMin());

        scene3DOptions.gradient2.setMaxVector(scene3DOptions.gradient2.getHintMax());
        scene3DOptions.gradient2.setMinVector(scene3DOptions.gradient2.getHintMin());

        //System.out.println("G1: MAX: "+gradient1.getHintMax()+" MIN: "+gradient1.getHintMin());
        //System.out.println("G2: MAX: "+gradient2.getHintMax()+" MIN: "+gradient2.getHintMin());

        glcanvas.presenter.GradientsApply();


        System.out.print("GRAD1: MAX: " + newValue.gradient1.getHintMax() + "  MIN: " + newValue.gradient1.getHintMin() + "\r\n");
        System.out.print("GRAD2: MAX: " + newValue.gradient2.getHintMax() + "  MIN: " + newValue.gradient2.getHintMin() + "\r\n");

        System.out.println("END RECALCULATE");
        //tu masz hinty policzone także
    }

    private void onFileSelect() {
        Platform.runLater(() -> {
            Scheduler.remove(timing);
            FileInput omfDatas = fileInputSelector.exec(files);


            glcanvas.presenter.surfaces.clear();

            omfDatas.omfDataList.forEach(surfaceData -> {

                SurfacePointsList currentSurface = new SurfacePointsList(omfDatas.threshold);
                currentSurface.addAll(surfaceData.points);
                glcanvas.presenter.surfaces.add(currentSurface);

            });

            if (!omfDatas.omfDataList.isEmpty()) {
                scene3DOptions.boxProperties.dimensions.set(
                        omfDatas.omfDataList.get(0).xStepSize,
                        omfDatas.omfDataList.get(0).yStepSize,
                        omfDatas.omfDataList.get(0).zStepSize
                );
            }

            scene3DOptions.vectorProperties.lenScale = 0.3 * scene3DOptions.boxProperties.dimensions.length() / glcanvas.presenter.getMaxVectorLength();
            scene3DOptions.vectorProperties.tipLen = 0.125 * scene3DOptions.boxProperties.dimensions.length();
            scene3DOptions.vectorProperties.tipRadius = 0.125 * scene3DOptions.boxProperties.dimensions.length();
            scene3DOptions.vectorProperties.radius = 0.05 * scene3DOptions.boxProperties.dimensions.length();

            scene3DOptions.globalScale = 0;
            //TODO: obliczanie skali

            System.out.println("max_len: " + glcanvas.presenter.getMaxVectorLength());
            System.out.println("len_scale: " + scene3DOptions.vectorProperties.lenScale);

            onOptionsChanged(scene3DOptions);

            timing = new RepeatedEvent(EventType.UPDATE3D, (int)(1e6 / scene3DOptions.FPS), glcanvas.presenter.surfaces.size()) {
                @Override
                public void dispatch() {
                    glcanvas.display();
                }

                @Override
                public void reset() {
                    glcanvas.reset();
                }
            };
            Scheduler.schedule(timing);
            System.out.println("Added surfaces: " + glcanvas.presenter.surfaces.size());
        });


    }
}
