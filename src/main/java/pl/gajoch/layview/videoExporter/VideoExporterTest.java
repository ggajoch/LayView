package pl.gajoch.layview.videoExporter;

//import pl.gajoch.layview.graphics3d.GLCanvas3DSurfaceViewer;
import pl.gajoch.layview.graphics2d.GLCanvas2DPlotViewer;
import pl.gajoch.layview.graphics2d.PlotPoint;
import pl.gajoch.layview.scheduler.EventType;
import pl.gajoch.layview.scheduler.RepeatedEvent;
import pl.gajoch.layview.scheduler.Scheduler;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Locale;

public class VideoExporterTest {
    static GLCanvas2DPlotViewer glcanvas;
    static JFrame frame;

    private static VideoExporter videoExporter;

    public static void main(String[] args) {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        glcanvas = new GLCanvas2DPlotViewer(capabilities);
        glcanvas.addGLEventListener(glcanvas);
        glcanvas.setSize(700, 700);
        frame = new JFrame("LayVIEW development preview");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setSize(700,700);
        frame.setVisible(true);

        videoExporter = new VideoExporter(frame,"C:\\Users\\Piotr\\Desktop\\tmp\\OBRAZKEN","C:\\Users\\Piotr\\Desktop\\tmp",30,0,0);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if(e.getKeyChar()=='q'){
                    System.out.println("Reset");
                    videoExporter.reset();
                }
                if(e.getKeyChar()=='w'){
                    System.out.println("Frame save");
                    videoExporter.saveSnapshot();
                }
                if(e.getKeyChar()=='e'){
                    System.out.println("Render");
                    videoExporter.closeVideo();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        /*final FPSAnimator animator = new FPSAnimator(canvasCamera, 300, true);
        animator.start();*/

        for (double angle = -Math.PI * 2; angle < Math.PI * 2; angle += 0.1) {
            glcanvas.presenter.plotPointsList.add(new PlotPoint(new Point2D.Double(angle/2 / Math.PI*100, (Math.sin(angle) + Math.sin(angle * 2))*70)));
        }

        Scheduler.schedule(new RepeatedEvent(EventType.UPDATE2D, (int) 1e6 / 24, glcanvas.presenter.plotPointsList.size()) {
            @Override
            public void dispatch() {
                glcanvas.display();
                videoExporter.saveSnapshot();
            }

            @Override
            public void reset() {
                glcanvas.reset();
                videoExporter.reset();
            }
        });



        while (true) {
            Scheduler.start();
            System.out.println("DONE");
        }

    }
}
