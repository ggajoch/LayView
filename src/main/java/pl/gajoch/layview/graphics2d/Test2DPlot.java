package pl.gajoch.layview.graphics2d;

import com.jogamp.opengl.util.FPSAnimator;
import pl.gajoch.layview.graphics3d.GLCanvas3DCamera;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Piotr on 02/03/2016.
 */
public class Test2DPlot {
    static GLCanvas2DPlot canvasCamera;
    static JFrame frame;

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        canvasCamera = new GLCanvas2DPlot(capabilities);
        canvasCamera.addGLEventListener(canvasCamera);
        canvasCamera.setSize(700, 700);
        frame = new JFrame("LayVIEW development preview");
        frame.getContentPane().add(canvasCamera);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        final FPSAnimator animator = new FPSAnimator(canvasCamera, 300, true);
        animator.start();

    }
}
