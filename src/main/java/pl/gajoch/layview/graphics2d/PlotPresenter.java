package pl.gajoch.layview.graphics2d;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.graphics3d.TrigonometricTab;

import javax.media.opengl.GL2;

public class PlotPresenter {
    public PlotOptions options;

    private TrigonometricTab trig;
    public PlotPointsList plotPointsList;

    public PlotPresenter(PlotOptions options) {
        this.options = options;
        trig = new TrigonometricTab(options.divisions);
        plotPointsList = new PlotPointsList();
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

        gl.glColor3d(options.xAxisOptions.frameColor.getRed(), options.xAxisOptions.frameColor.getGreen(), options.xAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.xAxisOptions.frameWidth);

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-100,-100);
        gl.glVertex2d(100,-100);
        gl.glEnd();

        gl.glColor3d(options.xAxisOptions.tickColor.getRed(), options.xAxisOptions.tickColor.getGreen(), options.xAxisOptions.tickColor.getBlue());
        gl.glLineWidth(options.xAxisOptions.tickWidth);

        gl.glBegin(GL2.GL_LINES);
        for(double x = options.xAxisOptions.min ; x <= options.xAxisOptions.max ; x+= options.xAxisOptions.tickIncrement){
            gl.glVertex2d(map(x,options.xAxisOptions.min,options.xAxisOptions.max,-100,100),-100-options.xAxisOptions.tickLength/2);
            gl.glVertex2d(map(x,options.xAxisOptions.min,options.xAxisOptions.max,-100,100),-100+options.xAxisOptions.tickLength/2);
        }
        gl.glEnd();


        gl.glColor3d(options.yAxisOptions.frameColor.getRed(), options.yAxisOptions.frameColor.getGreen(), options.yAxisOptions.frameColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.frameWidth);

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-100,-100);
        gl.glVertex2d(-100,100);
        gl.glEnd();

        gl.glColor3d(options.yAxisOptions.tickColor.getRed(), options.yAxisOptions.tickColor.getGreen(), options.yAxisOptions.tickColor.getBlue());
        gl.glLineWidth(options.yAxisOptions.tickWidth);

        gl.glBegin(GL2.GL_LINES);
        for(double y = options.yAxisOptions.min ; y <= options.yAxisOptions.max ; y+= options.yAxisOptions.tickIncrement){
            gl.glVertex2d(-100-options.yAxisOptions.tickLength/2, map(y,options.yAxisOptions.min,options.yAxisOptions.max,-100,100));
            gl.glVertex2d(-100+options.yAxisOptions.tickLength/2, map(y,options.yAxisOptions.min,options.yAxisOptions.max,-100,100));
        }
        gl.glEnd();
    }

    private void drawGrids(GL2 gl) {
        if(options.xAxisOptions.drawGrids) {
            gl.glColor3d(options.xAxisOptions.tickColor.getRed(), options.xAxisOptions.tickColor.getGreen(), options.xAxisOptions.tickColor.getBlue());
            gl.glLineWidth(options.xAxisOptions.tickWidth);

            gl.glBegin(GL2.GL_LINES);
            for (double x = options.xAxisOptions.min; x <= options.xAxisOptions.max; x += options.xAxisOptions.tickIncrement) {
                gl.glVertex2d(map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100), -100);
                gl.glVertex2d(map(x, options.xAxisOptions.min, options.xAxisOptions.max, -100, 100), 100);
            }
            gl.glEnd();
        }

        if(options.yAxisOptions.drawGrids) {
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
        gl.glVertex2d(point.getX(), point.getY());

        for (int division = 0; division <= options.divisions; division++) {
            gl.glVertex2d(map(point.getX(),options.xAxisOptions.min,options.xAxisOptions.max,-100,100) + trig.sin(division) * options.symbolRadius,
                    map(point.getY(),options.yAxisOptions.min,options.yAxisOptions.max,-100,100) + trig.cos(division) * options.symbolRadius);
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
                map(point.getX(),options.xAxisOptions.min,options.xAxisOptions.max,-100,100),
                map(point.getY(),options.yAxisOptions.min,options.yAxisOptions.max,-100,100)));

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
