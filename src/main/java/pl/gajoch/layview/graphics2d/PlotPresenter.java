package pl.gajoch.layview.graphics2d;

import com.sun.javaws.exceptions.InvalidArgumentException;
import pl.gajoch.layview.graphics2d.options.PlotOptions;
import pl.gajoch.layview.utils.RichTextRenderer;
import pl.gajoch.layview.utils.performance.Map;
import pl.gajoch.layview.utils.performance.TrigonometricTab;

import javax.media.opengl.GL2;
import java.awt.*;

public class PlotPresenter {
    public PlotOptions options;
    public PlotPointsList plotPointsList;
    private Rectangle boundaries;
    private TrigonometricTab trig;

    public PlotPresenter(PlotOptions options) {
        this.options = options;
        trig = new TrigonometricTab(options.lineOptions.divisions);
        plotPointsList = new PlotPointsList();
        boundaries = new Rectangle();
    }

    public void setBounds(int x, int y, int w, int h) {
        boundaries.setBounds(x, y, w, h);
    }

    public void setOptions(PlotOptions options) {
        this.options = options;
        trig = new TrigonometricTab(options.lineOptions.divisions);
    }

    public void draw(GL2 gl, int frame) {
        drawGrids(gl);
        try {
            if (options.lineOptions.isLine) {
                drawLine(gl, frame);
            } else {
                drawPoints(gl, frame);
            }
        } catch (InvalidArgumentException ex) {
            ex.printStackTrace();
        }
        drawAxes(gl);
    }

    private void drawAxes(GL2 gl) {


        //Plot TITLE
        RichTextRenderer textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.titleOptions.title.size));

        textRenderer.beginRendering((int) boundaries.getWidth(), (int) boundaries.getHeight());
        textRenderer.setColor(options.titleOptions.title.color);

        textRenderer.draw(options.titleOptions.title.text,
                (int) (Map.map(0, -100 - options.plotAreaOptions.margins.getX(), 100 + options.plotAreaOptions.margins.getY(), 0, boundaries.getWidth()) - textRenderer.getStringWidth(options.titleOptions.title.text) / 2),
                (int) (Map.map(100 + options.plotAreaOptions.margins.getHeight() / 2, -100 - options.plotAreaOptions.margins.getWidth(), 100 + options.plotAreaOptions.margins.getHeight(), 0, boundaries.getHeight()) - options.titleOptions.title.size / 2));

        textRenderer.endRendering();


        //X-AXIS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.xAxisOptions.label.size));

        textRenderer.beginRendering((int) boundaries.getWidth(), (int) boundaries.getHeight());
        textRenderer.setColor(options.xAxisOptions.label.color);

        textRenderer.draw(options.xAxisOptions.label.text,
                (int) (Map.map(0, -100 - options.plotAreaOptions.margins.getX(), 100 + options.plotAreaOptions.margins.getY(), 0, boundaries.getWidth()) - textRenderer.getStringWidth(options.xAxisOptions.label.text) / 2),
                (int) (Map.map(-100 - options.plotAreaOptions.margins.getWidth() * 0.7, -100 - options.plotAreaOptions.margins.getWidth(), 100 + options.plotAreaOptions.margins.getHeight(), 0, boundaries.getHeight()) - options.xAxisOptions.label.size / 2));

        textRenderer.endRendering();

        gl.glColor3d(options.xAxisOptions.frameColor.getRed(), options.xAxisOptions.frameColor.getGreen(), options.xAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.xAxisOptions.frameWidth);

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-100, -100);
        gl.glVertex2d(100, -100);
        gl.glEnd();


        //X-TICKS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.xAxisOptions.numberSize));

        textRenderer.beginRendering((int) boundaries.getWidth(), (int) boundaries.getHeight());
        textRenderer.setColor(options.xAxisOptions.label.color);

        for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
            double mapX = Map.map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
            String tick = String.format("%.0f", x);
            textRenderer.draw(tick,
                    (int) (Map.map(mapX, -100 - options.plotAreaOptions.margins.getX(), 100 + options.plotAreaOptions.margins.getY(), 0, boundaries.getWidth()) - textRenderer.getStringWidth(tick) / 2),
                    (int) (Map.map(-100 - options.plotAreaOptions.margins.getWidth() * 0.3, -100 - options.plotAreaOptions.margins.getWidth(), 100 + options.plotAreaOptions.margins.getHeight(), 0, boundaries.getHeight()) - options.xAxisOptions.numberSize / 2));
        }

        textRenderer.endRendering();

        gl.glColor3d(options.xAxisOptions.tickColor.getRed(), options.xAxisOptions.tickColor.getGreen(), options.xAxisOptions.tickColor.getBlue());
        gl.glLineWidth(options.xAxisOptions.tickWidth);


        gl.glBegin(GL2.GL_LINES);
        for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
            double mapX = Map.map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
            gl.glVertex2d(mapX, -100 - options.xAxisOptions.tickLength / 2);
            gl.glVertex2d(mapX, -100 + options.xAxisOptions.tickLength / 2);
        }
        gl.glEnd();


        //Y-AXIS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.yAxisOptions.label.size));

        textRenderer.beginRendering((int) boundaries.getWidth(), (int) boundaries.getHeight());
        textRenderer.setColor(options.yAxisOptions.label.color);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glRotated(90, 0, 0, 1);

        textRenderer.draw(options.yAxisOptions.label.text,
                (int) (Map.map(0, -100 - options.plotAreaOptions.margins.getWidth(), 100 + options.plotAreaOptions.margins.getHeight(), 0, boundaries.getHeight()) - textRenderer.getStringWidth(options.yAxisOptions.label.text) / 2),
                -(int) (Map.map(-100 - options.plotAreaOptions.margins.getX() * 0.8, -100 - options.plotAreaOptions.margins.getX(), 100 + options.plotAreaOptions.margins.getWidth(), 0, boundaries.getWidth()) + options.yAxisOptions.label.size / 2));

        textRenderer.endRendering();
        gl.glPopMatrix();

        gl.glColor3d(options.yAxisOptions.frameColor.getRed(), options.yAxisOptions.frameColor.getGreen(), options.yAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.frameWidth);

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-100, -100);
        gl.glVertex2d(-100, 100);
        gl.glEnd();


        //Y_TICKS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.yAxisOptions.numberSize));

        textRenderer.beginRendering((int) boundaries.getWidth(), (int) boundaries.getHeight());
        textRenderer.setColor(options.yAxisOptions.label.color);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glRotated(90, 0, 0, 1);

        for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
            double mapX = Map.map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
            String tick = String.format("%.0f", x);
            textRenderer.draw(tick,
                    (int) (Map.map(mapX, -100 - options.plotAreaOptions.margins.getWidth(), 100 + options.plotAreaOptions.margins.getHeight(), 0, boundaries.getHeight()) - textRenderer.getStringWidth(tick) / 2),
                    -(int) (Map.map(-100 - options.plotAreaOptions.margins.getX() * 0.3, -100 - options.plotAreaOptions.margins.getX(), 100 + options.plotAreaOptions.margins.getWidth(), 0, boundaries.getWidth()) + options.yAxisOptions.numberSize / 2));
        }

        textRenderer.endRendering();
        gl.glPopMatrix();

        gl.glColor3d(options.yAxisOptions.frameColor.getRed(), options.yAxisOptions.frameColor.getGreen(), options.yAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.frameWidth);

        gl.glColor3d(options.yAxisOptions.tickColor.getRed(), options.yAxisOptions.tickColor.getGreen(), options.yAxisOptions.tickColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.tickWidth);

        gl.glBegin(GL2.GL_LINES);
        for (double y = options.yAxisOptions.min; y <= options.yAxisOptions.max; y += options.yAxisOptions.tickIncrement) {
            gl.glVertex2d(-100 - options.yAxisOptions.tickLength / 2, Map.map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
            gl.glVertex2d(-100 + options.yAxisOptions.tickLength / 2, Map.map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
        }
        gl.glEnd();
    }

    private void drawGrids(GL2 gl) {
        if (options.xAxisOptions.drawGrids) {
            gl.glColor3d(options.xAxisOptions.tickColor.getRed(), options.xAxisOptions.tickColor.getGreen(), options.xAxisOptions.tickColor.getBlue());
            gl.glLineWidth(options.xAxisOptions.tickWidth);

            gl.glBegin(GL2.GL_LINES);
            for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
                gl.glVertex2d(Map.map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100), -100);
                gl.glVertex2d(Map.map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100), 100);
            }
            gl.glEnd();
        }

        if (options.yAxisOptions.drawGrids) {
            gl.glColor3d(options.yAxisOptions.tickColor.getRed(), options.yAxisOptions.tickColor.getGreen(), options.yAxisOptions.tickColor.getBlue());
            gl.glLineWidth(options.yAxisOptions.tickWidth);

            gl.glBegin(GL2.GL_LINES);
            for (double y = options.yAxisOptions.min; y <= options.yAxisOptions.max; y += options.yAxisOptions.tickIncrement) {
                gl.glVertex2d(-100, Map.map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
                gl.glVertex2d(100, Map.map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
            }
            gl.glEnd();
        }
    }

    private void drawPoints(GL2 gl, int frame) throws InvalidArgumentException {
        if (frame < 0)
            throw new InvalidArgumentException(new String[]{"Frame number must be positive!"});
        gl.glColor3d(options.lineOptions.color.getRed(), options.lineOptions.color.getGreen(), options.lineOptions.color.getBlue());
        plotPointsList.subList(0, frame).forEach(plotPoint -> drawPoint(gl, plotPoint));
    }

    private void drawPoint(GL2 gl, PlotPoint point) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        double x = Map.map(point.getX(), options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
        double y = Map.map(point.getY(), options.yAxisOptions.min, options.yAxisOptions.max, -100, 100);
        gl.glVertex2d(x, y);

        for (int division = 0; division <= options.lineOptions.divisions; division++) {
            gl.glVertex2d(x + trig.sin(division) * options.lineOptions.symbolRadius * boundaries.getHeight() / boundaries.getWidth(),
                    y + trig.cos(division) * options.lineOptions.symbolRadius);
        }

        gl.glEnd();
    }

    private void drawLine(GL2 gl, int frame) throws InvalidArgumentException {
        if (frame < 0)
            throw new InvalidArgumentException(new String[]{"Frame number must be positive!"});

        gl.glLineWidth(options.lineOptions.width);
        gl.glColor3d(options.lineOptions.color.getRed(), options.lineOptions.color.getGreen(), options.lineOptions.color.getBlue());
        gl.glBegin(GL2.GL_LINE_STRIP);

        plotPointsList.subList(0, frame).forEach(point -> gl.glVertex2d(
                Map.map(point.getX(), options.xAxisOptions.min, options.xAxisOptions.max, -100, 100),
                Map.map(point.getY(), options.yAxisOptions.min, options.yAxisOptions.max, -100, 100)));

        gl.glEnd();
    }

}
