package pl.gajoch.layview.graphics2d;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.beans.property.SimpleObjectProperty;
import pl.gajoch.layview.graphics3d.TrigonometricTab;

import javax.media.opengl.GL2;

public class PlotPresenter {
    public SimpleObjectProperty<PlotOptions> options;

    private TrigonometricTab trig;
    public PlotPointsList plotPointsList;

    public PlotPresenter(SimpleObjectProperty<PlotOptions> options) {
        this.options = options;
        options.addListener((observable, oldValue, newValue) -> {
            trig = new TrigonometricTab(newValue.divisions);
        });
        plotPointsList = new PlotPointsList();
    }

    public void draw(GL2 gl, int frame) {
        drawAxes(gl);
        try {
            if (options.get().isLine) {
                drawLine(gl, frame);
            } else {
                drawPoints(gl, frame);
            }
        } catch (InvalidArgumentException ex) {
            ex.printStackTrace();
        }
    }

    private void drawAxes(GL2 gl) {

    }

    private void drawPoints(GL2 gl, int frame) throws InvalidArgumentException {
        if( frame < 0 )
            throw new InvalidArgumentException(new String[]{"Frame number must be positive!"});

        gl.glColor3d(options.get().color.getRed(), options.get().color.getGreen(), options.get().color.getBlue());
        plotPointsList.subList(0, frame).forEach(plotPoint -> drawPoint(gl, plotPoint));
    }

    private void drawPoint(GL2 gl, PlotPoint point) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2d(point.getX(), point.getY());

        for (int division = 0; division <= options.get().divisions; division++) {
            gl.glVertex2d(point.getX() + trig.sin(division) * options.get().symbolRadius,
                    point.getY() + trig.cos(division) * options.get().symbolRadius);
        }

        gl.glEnd();
    }

    private void drawLine(GL2 gl, int frame) throws InvalidArgumentException {
        if( frame < 0 )
            throw new InvalidArgumentException(new String[]{"Frame number must be positive!"});

        gl.glLineWidth(options.get().width);
        gl.glColor3d(options.get().color.getRed(), options.get().color.getGreen(), options.get().color.getBlue());
        gl.glBegin(GL2.GL_LINE_STRIP);

        plotPointsList.subList(0, frame).forEach(plotPoint -> gl.glVertex2d(plotPoint.getX(), plotPoint.getY()));

        gl.glEnd();
    }
}
