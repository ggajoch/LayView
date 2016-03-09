package pl.gajoch.layview.graphics3d;

import com.jogamp.opengl.util.FPSAnimator;
import com.sun.javafx.geom.Vec3d;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.gui.*;
import pl.gajoch.layview.scheduler.EventType;
import pl.gajoch.layview.scheduler.RepeatedEvent;
import pl.gajoch.layview.scheduler.Scheduler;
import pl.gajoch.layview.utils.OMFData;
import pl.gajoch.layview.utils.OMFParser;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JPanel3D extends MovableJPanel {
    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;
    private GLCanvas3DSurfaceViewer glcanvas;
    private RepeatedEvent timing;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    private volatile Scene3DOptions scene3DOptions = new Scene3DOptions(0.025, 0.025, 0.01, 0.1, new Vec3d(.1, .1, .1), 1.0, 30, new HintGradient(), new HintGradient());

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

        /*scene3DOptions = newValue;

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
        //tu masz hinty policzone także*/
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

            scene3DOptions.vectorProperties.lenScale = 0.00000001 / glcanvas.presenter.getMaxVectorLength();
            scene3DOptions.vectorProperties.tipLen = 0.00025 / glcanvas.presenter.getMaxVectorLength();
            scene3DOptions.vectorProperties.tipRadius = 0.00025 / glcanvas.presenter.getMaxVectorLength();
            scene3DOptions.vectorProperties.radius = 0.0001 / glcanvas.presenter.getMaxVectorLength();

            System.out.println("max_len: " + glcanvas.presenter.getMaxVectorLength());
            System.out.println("len_scale: " + scene3DOptions.vectorProperties.lenScale);

            onOptionsChanged(scene3DOptions);

            timing = new RepeatedEvent(EventType.UPDATE3D, (int) 1e6 / 24, glcanvas.presenter.surfaces.size()) {
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
