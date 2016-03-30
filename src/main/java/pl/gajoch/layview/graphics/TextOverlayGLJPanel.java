package pl.gajoch.layview.graphics;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.sun.javafx.geom.Vec2d;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLJPanel;
import java.awt.*;
import java.util.ArrayList;

public class TextOverlayGLJPanel extends GLJPanel {
    public ArrayList<TextEntity> textEntities;

    public TextOverlayGLJPanel(GLCapabilities capabilities) {
        super(capabilities);
        textEntities = new ArrayList<>();

        textEntities.add(new TextEntity("Startujemy", new Font("SansSerif", Font.BOLD, 20), Color.BLACK, new Vec2d(0, 0), 0, 20));

        textEntities.add(new TextEntity("Test", new Font("SansSerif", Font.BOLD, 20), Color.BLACK, new Vec2d(150, 0), 0, 20));
    }

    protected void overlayProcess(int frame) {

        for (TextEntity entity : textEntities) {
            if (frame >= entity.frameStart && frame <= entity.frameStop) {
                TextRenderer renderer;
                renderer = new TextRenderer(entity.font);
                renderer.beginRendering(this.getWidth(), this.getHeight());
                renderer.setColor(entity.color);
                renderer.draw3D(entity.text, (int) entity.position.x, (int) entity.position.y, 0, 1);
                renderer.endRendering();
            }
        }
    }
}
