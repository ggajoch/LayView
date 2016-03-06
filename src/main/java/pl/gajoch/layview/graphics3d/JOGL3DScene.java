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

public class JOGL3DScene extends MovableSubScene {
    private FileInputSelector fileInputSelector;
    private Scene3DOptionsEditor scene3DOptionsEditor;

    private FileInput files;
    SimpleObjectProperty<Scene3DOptions> optionsProperty;

    private volatile Scene3DOptions scene3DOptions = new Scene3DOptions(0.025, 0.025, 0.01, 0.1, new Vec3d(.1, .1, .1), 1.0, 30, new HintGradient(), new HintGradient());


    public JOGL3DScene(GraphicsWindowManager parent, int width, int height) {
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

        GLCanvas3DCamera glcanvas = new GLCanvas3DCamera(capabilities);
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
                this.scene.add(glcanvas));

        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();
    }

    private void onOptionsChanged(Scene3DOptions newValue) {
        System.out.println("Recalculate!");

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


           /* surfaces.clear();
            omfDatas.stream().forEach(surfaceData -> {

            GradientSurfacePointsList currentSurface = new GradientSurfacePointsList();
            currentSurface.addAll(surfaceData.points);
            surfaces.add(currentSurface);

            });

            onOptionsChanged(scene3DOptions);
            frameCount = 0;*/
            //        timer.start();
        });
    }
}
