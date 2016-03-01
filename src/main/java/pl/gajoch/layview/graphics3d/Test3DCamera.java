package pl.gajoch.layview.graphics3d;


import java.awt.event.*;

import com.jogamp.opengl.util.*;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;

public class Test3DCamera {

    static GLCanvas3DCamera canvasCamera;
    static JFrame frame;

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        canvasCamera = new GLCanvas3DCamera(capabilities);
        Test3DCamera cube = new Test3DCamera();
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