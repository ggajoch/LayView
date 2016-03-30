package pl.gajoch.layview.graphics;

import com.jogamp.opengl.util.awt.TextRenderer;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLJPanel;
import java.awt.*;

public class TextOverlayGLJPanel extends GLJPanel {
    public TextOverlayGLJPanel(GLCapabilities capabilities) {
        super(capabilities);
    }

    protected void overlayProcess(int frame) {
        TextRenderer renderer;
        renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 36));
        renderer.beginRendering(this.getWidth(), this.getHeight());
        // optionally set the color
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        renderer.draw3D("PIOTR", 0, 0, 0, 1);
        // ... more draw commands, color changes, etc.
        renderer.endRendering();

    }
}
