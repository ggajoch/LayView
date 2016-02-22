package pl.gajoch.layview.graphics3d;

import pl.gajoch.layview.gui.Scene3DOptions;

import javax.media.opengl.GL2;
import java.util.ArrayList;

/**
 * Created by Piotr on 21/02/2016.
 */
public class SurfacesPresenter {
    public ArrayList<SurfacePointsList> surfaces;
    private Scene3DOptions options;

    public SurfacesPresenter(Scene3DOptions options) {
        this.options = options;
        surfaces = new ArrayList<>();
    }

    private void drawVector(GL2 gl, SurfacePoint point) {
        final double angleJump = Math.PI * 2 / options.vectorProperties.divisions;

        //TODO: optimise sin and cos calculation -> table!!!
        gl.glColor3d(point.color.getRed(), point.color.getGreen(), point.color.getBlue());

        gl.glPushMatrix();
        gl.glTranslated(point.position.x, point.position.y, point.position.z);
        {
            gl.glPushMatrix();
            gl.glRotated(Math.toDegrees(Math.atan2(-point.vector.y, point.vector.z)),
                    1, 0, 0);//in plane Y-Z
            gl.glRotated(Math.toDegrees(Math.atan2(
                    point.vector.x,
                    Math.sqrt(Math.pow(point.vector.y, 2) + Math.pow(point.vector.z, 2)))),
                    0, 1, 0);//out of plane Y-Z

            double sin, cos, sinPrev, cosPrev;

            //drawing cone
            {
                gl.glBegin(GL2.GL_TRIANGLE_FAN);
                gl.glVertex3d(0, 0, point.vector.length() + options.vectorProperties.tipLen);//top point of the cone


                sinPrev = options.vectorProperties.tipRadius * Math.sin(-angleJump);
                cosPrev = options.vectorProperties.tipRadius * Math.cos(-angleJump);

                for (int division = 0; division <= options.vectorProperties.divisions; division++) {
                    sin = options.vectorProperties.tipRadius * Math.sin(angleJump * division);
                    cos = options.vectorProperties.tipRadius * Math.cos(angleJump * division);

                    gl.glNormal3d(-options.vectorProperties.tipLen * (cos - cosPrev),
                            -options.vectorProperties.tipLen * (sinPrev - sin),
                            -(sinPrev * cos - cosPrev * sin));
                    gl.glVertex3d(sin, cos, point.vector.length());

                    sinPrev = sin;
                    cosPrev = cos;
                }
                gl.glEnd();
            }

            //drawing cone cap
            {
                gl.glNormal3d(0, 0, -1);
                gl.glBegin(GL2.GL_POLYGON);
                for (int division = 0; division <= options.vectorProperties.divisions; division++) {
                    gl.glVertex3d(options.vectorProperties.tipRadius * Math.sin(angleJump * division),
                            options.vectorProperties.tipRadius * Math.cos(angleJump * division),
                            point.vector.length());

                }
                gl.glEnd();
            }

            //drawing Cylinder walls
            {
                gl.glBegin(GL2.GL_QUAD_STRIP);
                sin = options.vectorProperties.radius * Math.sin(angleJump);
                cos = options.vectorProperties.radius * Math.cos(angleJump);
                //TODO: revise setting of Normal3d
                for (int division = 0; division <= options.vectorProperties.divisions + 1; division++) {
                    sinPrev = options.vectorProperties.radius * Math.sin(angleJump * division);
                    cosPrev = options.vectorProperties.radius * Math.cos(angleJump * division);

                    if (division > 0) {
                        gl.glVertex3d(sin, cos, point.vector.length());
                        gl.glVertex3d(sin, cos, 0);
                    }
                    gl.glNormal3d(sin + sinPrev, cos + cosPrev, 0); //aka mean of nex 2 vectors
                    sin = sinPrev;
                    cos = cosPrev;
                }
                gl.glEnd();
            }

            //drawing cylinder bottom cap
            {
                gl.glNormal3d(0, 0, -1);
                gl.glBegin(GL2.GL_POLYGON);
                //gl.glVertex3d(0, 0, 0);//center of disk
                for (int division = 0; division <= options.vectorProperties.divisions; division++) {
                    gl.glVertex3d(options.vectorProperties.radius * Math.sin(angleJump * division),
                            options.vectorProperties.radius * Math.cos(angleJump * division),
                            0);

                }
                gl.glEnd();
            }


            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }

    public void drawVectors(GL2 gl, int frame) {
        if (frame >= surfaces.size()) return;
        for (SurfacePoint point : surfaces.get(frame).points) {
            drawVector(gl, point);
        }
    }

    private void drawBox(GL2 gl, SurfacePoint point) {
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(point.color.getRed(), point.color.getGreen(), point.color.getBlue());

        //right
        gl.glNormal3d(1,0,0);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);

        //left
        gl.glNormal3d(-1,0,0);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);

        //top
        gl.glNormal3d(0,1,0);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);


        //bottom
        gl.glNormal3d(0,-1,0);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);


        //back
        gl.glNormal3d(0,0,-1);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z - options.boxProperties.dimensions.z / 2);


        //front
        gl.glNormal3d(0,0,1);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y + options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x - options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);
        gl.glVertex3d(
                point.position.x + options.boxProperties.dimensions.x / 2,
                point.position.y - options.boxProperties.dimensions.y / 2,
                point.position.z + options.boxProperties.dimensions.z / 2);


        gl.glEnd();
    }

    public void drawBoxes(GL2 gl, int frame) {
        if (frame >= surfaces.size()) return;
        for (SurfacePoint point : surfaces.get(frame).points) {
            drawBox(gl, point);
        }
    }
}
