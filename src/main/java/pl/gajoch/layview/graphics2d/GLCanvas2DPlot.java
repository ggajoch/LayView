package pl.gajoch.layview.graphics2d;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.threed.CardanEulerSingularityException;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import pl.gajoch.layview.graphics3d.SurfacePoint;
import pl.gajoch.layview.graphics3d.SurfacePointsList;
import pl.gajoch.layview.graphics3d.SurfacesPresenter;
import pl.gajoch.layview.gui.GradientPoint;
import pl.gajoch.layview.gui.HintGradient;
import pl.gajoch.layview.gui.Scene3DOptions;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.awt.event.*;

/**
 * Created by Piotr on 02/03/2016.
 */
public class GLCanvas2DPlot extends GLCanvas implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private boolean isVectors = false;


    private SurfacesPresenter presenter;

    private Vec3d mousePos, mouseOld, mouseDelta;

    private Vec3d angle, offset;
    private double scale;


    private static final double SCROLL_SCALE = .01;
    private static final double ROTATE_SCALE = .01;
    private static final double MOVE_SCALE = .01;

    public GLCanvas2DPlot(GLCapabilities capabilities) {
        super(capabilities);
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicked");
        //System.out.println(e.getClickCount());
        if (e.getClickCount() == 2) {
            isVectors = !isVectors;
        }
    }

    public void mouseEntered(MouseEvent e) {
        //System.out.println("Entered");
    }

    public void mouseExited(MouseEvent e) {
        //System.out.println("Exited");
    }

    public void mouseMoved(MouseEvent e) {
        //System.out.println("EMoved");
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        double modifier = 1.0;
        if (e.isControlDown()) {
            modifier = 0.1;
        }
        if (e.isShiftDown()) {
            modifier = 10;
        }
        scale -= SCROLL_SCALE * modifier * e.getWheelRotation();

        //cameraScale.set(Math.pow(10, scale));
    }

    public void mousePressed(MouseEvent e) {
        mousePos.set(e.getX(), e.getY(), 0);
        if ((e.getModifiers() & e.BUTTON2_MASK) != 0) {
            angle.set(0, 0, 0);
            offset.set(0, 0, 0);
            scale = 0;
        }
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Released");
    }

    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        render(drawable);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glEnable(GL2.GL_LINE_SMOOTH);
        gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);

        mousePos = new Vec3d();
        mouseOld = new Vec3d();
        mouseDelta = new Vec3d();

        angle = new Vec3d();
        offset = new Vec3d();

        scale = 0;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);


    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;
        final double h = (float) width / (float) height;
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-2 * h, 2 * h, -2, 2, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);


    }

    private long last = 0, now;
    private int frameCt = 0;

    private void render(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        /*now = System.nanoTime();
        System.out.println(1e9f / ((float) (now - last)));
        last = now;*/
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        gl.glLoadIdentity();
        //gl.glTranslatef(0f, 0f, -5.0f);

        gl.glColor3d(0, 1, 0);

        /*gl.glBegin(GL2.GL_QUADS);

        gl.glVertex2d(-1,-1);
        gl.glVertex2d(1,-1);
        gl.glVertex2d(1,1);
        gl.glVertex2d(-1,1);

        gl.glEnd();*/

        gl.glLineWidth(3);

        gl.glBegin(GL2.GL_LINE_STRIP);

        for (double angle = -Math.PI * 2; angle < Math.PI * 2; angle += 0.1) {
            gl.glVertex2d(angle / 3, Math.sin(angle));
        }

        gl.glEnd();

        gl.glFlush();
    }
}
