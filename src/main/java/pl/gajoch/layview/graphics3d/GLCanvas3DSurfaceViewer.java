package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import org.apache.commons.math3.geometry.euclidean.threed.CardanEulerSingularityException;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import pl.gajoch.layview.graphics.TextOverlayGLJPanel;
import pl.gajoch.layview.graphics3d.options.HintGradient;
import pl.gajoch.layview.graphics3d.options.Scene3DOptions;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.event.*;

public class GLCanvas3DSurfaceViewer extends TextOverlayGLJPanel implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private static final double SCROLL_SCALE = .01;
    private static final double ROTATE_SCALE = .01;
    private final double MOVE_SCALE = 3.275;
    public SurfacesPresenter presenter;
    private Scene3DOptions options;
    private Vec3d mousePos, mouseOld, mouseDelta;
    private Vec3d angle, offset;
    private double scale;
    private int frameCt = 0;

    public GLCanvas3DSurfaceViewer(GLCapabilities capabilities) {
        super(capabilities);
        options = new Scene3DOptions(0.025, 0.025, 0.01, 0.1, new Vec3d(.1, .1, .1), 1.0, 30, new HintGradient(), new HintGradient(), true, true);
        presenter = new SurfacesPresenter(options);
    }

    public void setOptions(Scene3DOptions options) {
        this.options = options;
        presenter.options = options;
        //System.out.println(presenter.options.vectorProperties.lenScale);
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicked");
        //System.out.println(e.getClickCount());
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
        mouseOld.set(mousePos);
        mousePos.set(e.getX(), e.getY(), 0);
        mouseDelta.set(mousePos);
        mouseDelta.sub(mouseOld);

        if (e.isControlDown()) {
            mouseDelta.x = 0;
        }

        if (e.isShiftDown()) {
            mouseDelta.y = 0;
        }

        if ((e.getModifiers() & e.BUTTON1_MASK) != 0) {
            Rotation baseRotation = new Rotation(RotationOrder.XYZ, angle.x, angle.y, angle.z);
            //get base rotation (it is transform form neutral point to actual point of view)
            Rotation deltaRotation = new Rotation(
                    new Vector3D(0, 0),
                    new Vector3D(mouseDelta.x * ROTATE_SCALE, -mouseDelta.y * ROTATE_SCALE)
            );//get rotation according to mouse movement
            Rotation finalRotation = deltaRotation.applyTo(baseRotation);//sum both the rotations
            try {
                double angles[] = finalRotation.getAngles(RotationOrder.XYZ);

                angle.x = angles[0];
                angle.y = angles[1];
                angle.z = angles[2];

            } catch (CardanEulerSingularityException ex) {
                System.out.print("ROTATE ERROR\r\n");
            }
        }
        /*if((e.getModifiers() & e.BUTTON2_MASK) != 0){
            System.out.println("Middle");
        }*/
        if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {

            Rotation baseRotation = new Rotation(RotationOrder.ZXY, angle.x, angle.y, angle.z);//magic happens!
            Vector3D planeTranslate = baseRotation.applyInverseTo(
                    new Vector3D(mouseDelta.x * MOVE_SCALE / this.getHeight(), -mouseDelta.y * MOVE_SCALE / this.getHeight(), 0)
            );

            offset.x += planeTranslate.getX() * Math.pow(10, -scale - options.globalScale);
            offset.y += planeTranslate.getY() * Math.pow(10, -scale - options.globalScale);
            offset.z += planeTranslate.getZ() * Math.pow(10, -scale - options.globalScale);

            //TODO: deal with window resize to maintain 1:1 movement!!
        }
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
        gl.glShadeModel(GL2.GL_FLAT);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_NORMALIZE);

        mousePos = new Vec3d();
        mouseOld = new Vec3d();
        mouseDelta = new Vec3d();

        angle = new Vec3d();
        offset = new Vec3d();

        scale = 0 + options.globalScale;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);


    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        final GLU glu = new GLU();
        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private void render(GLAutoDrawable drawable) {


        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -5.0f);

        gl.glRotated(Math.toDegrees(angle.x), 0, 0, 1);
        gl.glRotated(Math.toDegrees(angle.y), 1, 0, 0);
        gl.glRotated(Math.toDegrees(angle.z), 0, 1, 0);

        gl.glScaled(Math.pow(10, scale + options.globalScale), Math.pow(10, scale + options.globalScale), Math.pow(10, scale + options.globalScale));

        gl.glTranslated(offset.x + options.initialTranslate.x, offset.y + options.initialTranslate.y, offset.z + options.initialTranslate.z);

        overlayProcess(frameCt);
        presenter.draw(gl, frameCt++);
        if (frameCt >= presenter.surfaces.size()) frameCt = 0;

        gl.glFlush();
    }

    public void reset() {
        frameCt = 0;
    }
}
