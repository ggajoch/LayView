package pl.gajoch.layview.graphics2d;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class PlotPresenter {
    public PlotOptions options;

    public ArrayList<PlotPoint> points;

    public PlotPresenter(PlotOptions options){
        this.options = options;
        points = new ArrayList<>();
    }

    public void draw(GL2 gl, int frame){
        if(options.isLine){
            drawLine(gl, frame);
        }else{

        }
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
