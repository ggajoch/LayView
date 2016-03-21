package pl.gajoch.layview.graphics2d;

import com.sun.javaws.exceptions.InvalidArgumentException;
import pl.gajoch.layview.graphics2d.options.PlotOptions;
import pl.gajoch.layview.utils.performance.TrigonometricTab;
import pl.gajoch.layview.utils.RichTextRenderer;

import javax.media.opengl.GL2;
import java.awt.*;

public class PlotPresenter {
    public PlotOptions options;

    private Rectangle boundaries;

    private TrigonometricTab trig;
    public PlotPointsList plotPointsList;

    public PlotPresenter(PlotOptions options) {
        this.options = options;
        trig = new TrigonometricTab(options.divisions);
        plotPointsList = new PlotPointsList();
        boundaries = new Rectangle();
    }

    public void setBounds(int x, int y, int w, int h) {
        boundaries.setBounds(x, y, w, h);
    }

    public void setOptions(PlotOptions options) {
        this.options = options;
        trig = new TrigonometricTab(options.divisions);
    }

    public void draw(GL2 gl, int frame) {
        drawGrids(gl);
        try {
            if (options.isLine) {
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
        RichTextRenderer textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.titleSize));

        textRenderer.beginRendering((int)boundaries.getWidth(), (int)boundaries.getHeight());
        textRenderer.setColor(options.titleColor);

        textRenderer.draw(options.title,
                (int)(map(0,-100 - options.margins.getX(), 100 + options.margins.getY(),0,boundaries.getWidth())-textRenderer.getStringWidth(options.title)/2),
                (int)(map(100 + options.margins.getHeight()/2,-100 - options.margins.getWidth(), 100 + options.margins.getHeight(),0,boundaries.getHeight())-options.titleSize/2));

        textRenderer.endRendering();


        //X-AXIS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.xAxisOptions.labelSize));

        textRenderer.beginRendering((int)boundaries.getWidth(), (int)boundaries.getHeight());
        textRenderer.setColor(options.xAxisOptions.labelColor);

        textRenderer.draw(options.xAxisOptions.label,
                (int)(map(0,-100 - options.margins.getX(), 100 + options.margins.getY(),0,boundaries.getWidth())-textRenderer.getStringWidth(options.xAxisOptions.label)/2),
                (int)(map(-100 - options.margins.getWidth()*0.7,-100 - options.margins.getWidth(), 100 + options.margins.getHeight(),0,boundaries.getHeight())-options.xAxisOptions.labelSize/2));

        textRenderer.endRendering();

        gl.glColor3d(options.xAxisOptions.frameColor.getRed(), options.xAxisOptions.frameColor.getGreen(), options.xAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.xAxisOptions.frameWidth);

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-100, -100);
        gl.glVertex2d(100, -100);
        gl.glEnd();


        //X-TICKS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.xAxisOptions.numberSize));

        textRenderer.beginRendering((int)boundaries.getWidth(), (int)boundaries.getHeight());
        textRenderer.setColor(options.xAxisOptions.labelColor);

        for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
            double mapX = map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
            String tick = String.format("%.0f",x);
            textRenderer.draw(tick,
                    (int)(map(mapX,-100 - options.margins.getX(), 100 + options.margins.getY(),0,boundaries.getWidth())-textRenderer.getStringWidth(tick)/2),
                    (int)(map(-100 - options.margins.getWidth()*0.3,-100 - options.margins.getWidth(), 100 + options.margins.getHeight(),0,boundaries.getHeight())-options.xAxisOptions.numberSize/2));
        }

        textRenderer.endRendering();

        gl.glColor3d(options.xAxisOptions.tickColor.getRed(), options.xAxisOptions.tickColor.getGreen(), options.xAxisOptions.tickColor.getBlue());
        gl.glLineWidth(options.xAxisOptions.tickWidth);




        gl.glBegin(GL2.GL_LINES);
        for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
            double mapX = map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
            gl.glVertex2d(mapX, -100 - options.xAxisOptions.tickLength / 2);
            gl.glVertex2d(mapX, -100 + options.xAxisOptions.tickLength / 2);
        }
        gl.glEnd();


        //Y-AXIS
        textRenderer = new RichTextRenderer(new Font("Arial Narrow", Font.BOLD, options.yAxisOptions.labelSize));

        textRenderer.beginRendering((int)boundaries.getWidth(), (int)boundaries.getHeight());
        textRenderer.setColor(options.yAxisOptions.labelColor);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glRotated(90,0,0,1);

        textRenderer.draw(options.yAxisOptions.label,
                (int)(map(0,-100 - options.margins.getWidth(), 100 + options.margins.getHeight(),0,boundaries.getHeight())-textRenderer.getStringWidth(options.yAxisOptions.label)/2),
                -(int)(map(-100 - options.margins.getX()*0.8,-100 - options.margins.getX(), 100 + options.margins.getWidth(),0,boundaries.getWidth())+options.yAxisOptions.labelSize/2));

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

        textRenderer.beginRendering((int)boundaries.getWidth(), (int)boundaries.getHeight());
        textRenderer.setColor(options.yAxisOptions.labelColor);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glRotated(90,0,0,1);

        for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
            double mapX = map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
            String tick = String.format("%.0f", x);
            textRenderer.draw(tick,
                    (int) (map(mapX, -100 - options.margins.getWidth(), 100 + options.margins.getHeight(), 0, boundaries.getHeight()) - textRenderer.getStringWidth(tick) / 2),
                    -(int) (map(-100 - options.margins.getX() * 0.3, -100 - options.margins.getX(), 100 + options.margins.getWidth(), 0, boundaries.getWidth()) + options.yAxisOptions.numberSize / 2));
        }

        textRenderer.endRendering();
        gl.glPopMatrix();

        gl.glColor3d(options.yAxisOptions.frameColor.getRed(), options.yAxisOptions.frameColor.getGreen(), options.yAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.frameWidth);

        gl.glColor3d(options.yAxisOptions.tickColor.getRed(), options.yAxisOptions.tickColor.getGreen(), options.yAxisOptions.tickColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.tickWidth);

        gl.glBegin(GL2.GL_LINES);
        for (double y = options.yAxisOptions.min; y <= options.yAxisOptions.max; y += options.yAxisOptions.tickIncrement) {
            gl.glVertex2d(-100 - options.yAxisOptions.tickLength / 2, map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
            gl.glVertex2d(-100 + options.yAxisOptions.tickLength / 2, map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
        }
        gl.glEnd();
    }

    private void drawGrids(GL2 gl) {
        if (options.xAxisOptions.drawGrids) {
            gl.glColor3d(options.xAxisOptions.tickColor.getRed(), options.xAxisOptions.tickColor.getGreen(), options.xAxisOptions.tickColor.getBlue());
            gl.glLineWidth(options.xAxisOptions.tickWidth);

            gl.glBegin(GL2.GL_LINES);
            for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
                gl.glVertex2d(map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100), -100);
                gl.glVertex2d(map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100), 100);
            }
            gl.glEnd();
        }

        if (options.yAxisOptions.drawGrids) {
            gl.glColor3d(options.yAxisOptions.tickColor.getRed(), options.yAxisOptions.tickColor.getGreen(), options.yAxisOptions.tickColor.getBlue());
            gl.glLineWidth(options.yAxisOptions.tickWidth);

            gl.glBegin(GL2.GL_LINES);
            for (double y = options.yAxisOptions.min; y <= options.yAxisOptions.max; y += options.yAxisOptions.tickIncrement) {
                gl.glVertex2d(-100, map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
                gl.glVertex2d(100, map(y, options.yAxisOptions.min, options.yAxisOptions.max, -100, 100));
            }
            gl.glEnd();
        }
    }

    private void drawPoints(GL2 gl, int frame) throws InvalidArgumentException {
        if (frame < 0)
            throw new InvalidArgumentException(new String[]{"Frame number must be positive!"});
        gl.glColor3d(options.color.getRed(), options.color.getGreen(), options.color.getBlue());
        plotPointsList.subList(0, frame).forEach(plotPoint -> drawPoint(gl, plotPoint));
    }

    private void drawPoint(GL2 gl, PlotPoint point) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        double x = map(point.getX(), options.xAxisOptions.min, options.xAxisOptions.max, -100, 100);
        double y = map(point.getY(), options.yAxisOptions.min, options.yAxisOptions.max, -100, 100);
        gl.glVertex2d(x, y);

        for (int division = 0; division <= options.divisions; division++) {
            gl.glVertex2d(x + trig.sin(division) * options.symbolRadius * boundaries.getHeight()/boundaries.getWidth(),
                    y + trig.cos(division) * options.symbolRadius);
        }

        gl.glEnd();
    }

    private void drawLine(GL2 gl, int frame) throws InvalidArgumentException {
        if (frame < 0)
            throw new InvalidArgumentException(new String[]{"Frame number must be positive!"});

        gl.glLineWidth(options.width);
        gl.glColor3d(options.color.getRed(), options.color.getGreen(), options.color.getBlue());
        gl.glBegin(GL2.GL_LINE_STRIP);

        plotPointsList.subList(0, frame).forEach(point -> gl.glVertex2d(
                map(point.getX(), options.xAxisOptions.min, options.xAxisOptions.max, -100, 100),
                map(point.getY(), options.yAxisOptions.min, options.yAxisOptions.max, -100, 100)));

        gl.glEnd();
    }

    private double map(double x, double inMin, double inMax, double outMin, double outMax) {
        x = (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
        double absMax = Math.max(outMax, outMin);
        double absMin = Math.min(outMax, outMin);
        if (x > absMax) x = absMax;
        if (x < absMin) x = absMin;
        return x;
    }

}
