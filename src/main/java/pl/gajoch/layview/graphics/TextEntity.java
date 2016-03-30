package pl.gajoch.layview.graphics;

import com.sun.javafx.geom.Vec2d;

import java.awt.*;

public class TextEntity {
    public int frameStart, frameStop;
    Font font;
    Vec2d position;
    String text;
    Color color;

    public TextEntity(String text, Font font, Color color, Vec2d position, int frameStart, int frameStop) {
        this.text = text;
        this.font = font;
        this.position = position;
        this.frameStart = frameStart;
        this.frameStop = frameStop;
        this.color = color;
    }
}
