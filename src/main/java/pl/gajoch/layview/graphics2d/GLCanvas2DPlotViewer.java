package pl.gajoch.layview.graphics2d;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.sun.javafx.geom.Vec3d;
import pl.gajoch.layview.graphics2d.options.PlotOptions;
import pl.gajoch.layview.graphics2d.options.Scene2DOptions;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.awt.GLJPanel;
import java.awt.*;
import java.awt.event.*;

public class GLCanvas2DPlotViewer extends GLJPanel implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private TextRenderer renderer;

    public PlotPresenter presenter;

    private Vec3d mousePos, mouseOld, mouseDelta;

    private Vec3d angle, offset;
    private double scale;

    private Scene2DOptions options;


    private static final double SCROLL_SCALE = .01;
    private static final double ROTATE_SCALE = .01;
    private static final double MOVE_SCALE = .01;

    public GLCanvas2DPlotViewer(GLCapabilities capabilities) {
        super(capabilities);
        options = new Scene2DOptions(30, new PlotOptions());
        presenter = new PlotPresenter(options.plotOptions);
    }

    public void setOptions(Scene2DOptions options) {
        this.options = options;
        presenter.setOptions(options.plotOptions);
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicked");
        //System.out.println(e.getClickCount());
        if (e.getClickCount() == 2) {
            presenter.options.isLine = !presenter.options.isLine;
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

        renderer = new TextRenderer(new Font("Arial Narrow", Font.BOLD, 36));

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
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-100 - options.plotOptions.margins.getX(), 100 + options.plotOptions.margins.getY(),
                -100 - options.plotOptions.margins.getWidth(), 100 + options.plotOptions.margins.getHeight(), -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        presenter.setBounds(x, y, width, height);

    }

    private long last = 0, now;
    private int frameCt = 0;

    private void render(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        now = System.nanoTime();
        //System.out.println(1e9f / ((float) (now - last)));
        String fps = new String("FPS = " + String.format("%.2f", 1e9f / ((float) (now - last))));
        last = now;
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        gl.glLoadIdentity();

        gl.glColor3d(0, 1, 0);

        if (frameCt >= presenter.plotPointsList.size()) {
            frameCt = 0;
        }
        presenter.draw(gl, frameCt);

        frameCt++;


        /*renderer.beginRendering(this.getWidth(), this.getHeight());
        renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glRotated(45,0,0,1);
        renderer.draw(fps, 0, 0);
        renderer.endRendering();
        gl.glPopMatrix();*/

        gl.glFlush();
    }

    public void reset() {
        frameCt = 0;
    }
}
