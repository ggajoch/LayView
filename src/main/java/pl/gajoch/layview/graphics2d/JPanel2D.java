package pl.gajoch.layview.graphics2d;

import com.jogamp.opengl.util.FPSAnimator;
import pl.gajoch.layview.gui.*;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class JPanel2D extends MovableJPanel {
    private FileInputSelector fileInputSelector;
    private FileInput files;

    private GLCanvas2DPlotViewer glcanvas;

    @Override
    public void fixCenter(Rectangle position) {
        glcanvas.reshape((int)position.getX(), (int)position.getY(), (int)position.getWidth(), (int)position.getHeight());
    }

    public JPanel2D(int width, int height) {
        super(width, height);

        fileInputSelector = new FileInputSelector();
        files = new FileInput();

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

        SwingUtilities.invokeLater(() ->
                this.add(glcanvas));

        final FPSAnimator animator = new FPSAnimator(glcanvas, 10, true);
        animator.start();
    }
}
