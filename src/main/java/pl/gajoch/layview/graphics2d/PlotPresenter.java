package pl.gajoch.layview.graphics2d;

import pl.gajoch.layview.graphics3d.TrigonometricTab;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class PlotPresenter {
    public PlotOptions options;

    private TrigonometricTab trig;
    private int lastDivisions;

    public ArrayList<PlotPoint> points;

    public PlotPresenter(PlotOptions options){
        this.options = options;
        points = new ArrayList<>();
        trig = new TrigonometricTab(options.divisions);
        lastDivisions = options.divisions;
    }

    public void draw(GL2 gl, int frame){
        drawAxes(gl);
        if(options.isLine){
            drawLine(gl, frame);
        }else{
            drawPoints(gl, frame);
        }
    }

    private void drawAxes(GL2 gl){

    }

    private void drawPoints(GL2 gl, int frame){
        if(frame>=0 && frame < points.size()){
            gl.glColor3d(options.color.getRed(),options.color.getGreen(),options.color.getBlue());
            for(int i=0 ; i < frame ; i++){
                drawPoint(gl, points.get(i));
            }
        }
    }

    private void drawPoint(GL2 gl, PlotPoint point){
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2d(point.value.x, point.value.y);

        for(int division = 0 ; division <= options.divisions ; division++){
            gl.glVertex2d(point.value.x + trig.sin(division)*options.symbolRadius,
                    point.value.y + trig.cos(division)*options.symbolRadius);
        }

        gl.glEnd();
    }

    private void drawLine(GL2 gl, int frame){
        if(frame>=0 && frame < points.size()){
            gl.glLineWidth(options.width);
            gl.glColor3d(options.color.getRed(),options.color.getGreen(),options.color.getBlue());
            gl.glBegin(GL2.GL_LINE_STRIP);
            for(int i=0 ; i < frame ; i++){
                PlotPoint point = points.get(i);
                gl.glVertex2d(point.value.x, point.value.y);
            }
            gl.glEnd();
        }
    }
}
