package pl.gajoch.layview.graphics2d;

import com.sun.javafx.geom.Vec3d;
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
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class JPanel2D extends MovableJPanel {
    private FileInputSelector fileInputSelector;
    private FileInput files;

    SimpleObjectProperty<Scene2DOptions> optionsProperty;

    private volatile Scene2DOptions scene2DOptions = new Scene2DOptions(30, new PlotOptions());

    private GLCanvas2DPlotViewer glcanvas;
    private RepeatedEvent timing;

    @Override
    public void fixCenter(Rectangle position) {
        glcanvas.reshape(0, 0, (int) position.getWidth(), (int) position.getHeight());
    }

    public JPanel2D(int width, int height) {
        super(width, height);

        fileInputSelector = new FileInputSelector();
        files = new FileInput();

        optionsProperty = new SimpleObjectProperty<>(scene2DOptions);

        optionsProperty.addListener((observable, oldValue, newValue) -> {
            Scheduler.remove(timing);
            onOptionsChanged(newValue);
        });

        List<JMenuItem> menu = new ArrayList<>();

        JMenuItem item1 = new JMenuItem("File select...");
        item1.addActionListener(e -> {
        });
        menu.add(item1);

        this.generateContextMenu(menu);

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        glcanvas = new GLCanvas2DPlotViewer(capabilities);
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

        for (double angle = -Math.PI * 2; angle < Math.PI * 2; angle += 0.1) {
            glcanvas.presenter.plotPointsList.add(new PlotPoint(new Point2D.Double(angle / Math.PI * 50, (Math.sin(angle) + Math.sin(angle * 2)) * 100)));
        }

        SwingUtilities.invokeLater(() ->
                this.add(glcanvas));

        timing = new RepeatedEvent(EventType.UPDATE2D, (int) 1e6 / 24, glcanvas.presenter.plotPointsList.size()) {
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

        /*final FPSAnimator animator = new FPSAnimator(glcanvas, 10, true);
        animator.start();*/
    }

    private void onOptionsChanged(Scene2DOptions newValue) {
        System.out.println("Recalculate!");

        glcanvas.setOptions(newValue);

        scene2DOptions = newValue;

        /*glcanvas.presenter.gradients.clear();
        glcanvas.presenter.gradients.add(scene3DOptions.gradient1);
        //glcanvas.presenter.gradients.add(scene3DOptions.gradient2);

        glcanvas.presenter.gradientsHintReset();
        glcanvas.presenter.gradientsHintCalculate();

        glcanvas.presenter.gradientsApply();

        System.out.print("GRAD1: MAX: " + newValue.gradient1.getHintMax() + "  MIN: " + newValue.gradient1.getHintMin() + "\r\n");
        System.out.print("GRAD2: MAX: " + newValue.gradient2.getHintMax() + "  MIN: " + newValue.gradient2.getHintMin() + "\r\n");*/

        System.out.println("END RECALCULATE");

        timing = new RepeatedEvent(EventType.UPDATE2D, (int) (1e6 / scene2DOptions.FPS), glcanvas.presenter.plotPointsList.size()) {
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
        //tu masz hinty policzone także
    }
}
