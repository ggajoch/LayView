package pl.gajoch.layview.graphics3d;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.paint.Color;
import pl.gajoch.layview.graphics3d.options.Gradient;
import pl.gajoch.layview.graphics3d.options.GradientPoint;
import pl.gajoch.layview.graphics3d.options.HintGradient;
import pl.gajoch.layview.graphics3d.options.Scene3DOptions;
import pl.gajoch.layview.utils.performance.Map;
import pl.gajoch.layview.utils.performance.TrigonometricTab;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class SurfacesPresenter {
    public ArrayList<SurfacePointsList> surfaces;

    public ArrayList<HintGradient> gradients;

    public Scene3DOptions options;
    private TrigonometricTab trig;
    private int lastDivisions;

    public SurfacesPresenter(Scene3DOptions options) {
        this.options = options;
        surfaces = new ArrayList<>();
        gradients = new ArrayList<>();
        trig = new TrigonometricTab(options.vectorProperties.divisions);
        lastDivisions = options.vectorProperties.divisions;
    }

    private void drawVector(GL2 gl, SurfacePoint point) {
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
                gl.glVertex3d(0, 0, point.vector.length() * options.vectorProperties.lenScale + options.vectorProperties.tipLen);//top point of the cone


                sinPrev = options.vectorProperties.tipRadius * trig.sin(-1);
                cosPrev = options.vectorProperties.tipRadius * trig.cos(-1);

                for (int division = 0; division <= options.vectorProperties.divisions; division++) {
                    sin = options.vectorProperties.tipRadius * trig.sin(division);
                    cos = options.vectorProperties.tipRadius * trig.cos(division);

                    gl.glNormal3d(-options.vectorProperties.tipLen * (cos - cosPrev) / options.vectorProperties.lenScale,
                            -options.vectorProperties.tipLen * (sinPrev - sin) / options.vectorProperties.lenScale,
                            -(sinPrev * cos - cosPrev * sin) / options.vectorProperties.lenScale);

                    gl.glVertex3d(sin, cos, point.vector.length() * options.vectorProperties.lenScale);

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
                    gl.glVertex3d(options.vectorProperties.tipRadius * trig.sin(division),
                            options.vectorProperties.tipRadius * trig.cos(division),
                            point.vector.length() * options.vectorProperties.lenScale);

                }
                gl.glEnd();
            }

            //drawing Cylinder walls
            {
                gl.glBegin(GL2.GL_QUAD_STRIP);
                sin = options.vectorProperties.radius * trig.sin(0);
                cos = options.vectorProperties.radius * trig.cos(0);
                //TODO: revise setting of Normal3d
                for (int division = 0; division <= options.vectorProperties.divisions + 1; division++) {
                    sinPrev = options.vectorProperties.radius * trig.sin(division);
                    cosPrev = options.vectorProperties.radius * trig.cos(division);

                    if (division > 0) {
                        gl.glVertex3d(sin, cos, point.vector.length() * options.vectorProperties.lenScale);
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
                    gl.glVertex3d(options.vectorProperties.radius * trig.sin(division),
                            options.vectorProperties.radius * trig.cos(division),
                            0);

                }
                gl.glEnd();
            }


            gl.glPopMatrix();
        }
        gl.glPopMatrix();
    }

    public void drawVectors(GL2 gl, int frame) {
        //TODO: maybe do it better?
        if (lastDivisions != options.vectorProperties.divisions) {
            trig = new TrigonometricTab(options.vectorProperties.divisions);
            lastDivisions = options.vectorProperties.divisions;
        }
        if (frame >= surfaces.size()) return;
        for (SurfacePoint point : surfaces.get(frame).points) {
            drawVector(gl, point);
        }
    }

    private void drawBox(GL2 gl, SurfacePoint point) {
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(point.color.getRed(), point.color.getGreen(), point.color.getBlue());

        //right
        gl.glNormal3d(1, 0, 0);
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
        gl.glNormal3d(-1, 0, 0);
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
        gl.glNormal3d(0, 1, 0);
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
        gl.glNormal3d(0, -1, 0);
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
        gl.glNormal3d(0, 0, -1);
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
        gl.glNormal3d(0, 0, 1);
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

    public void draw(GL2 gl, int frame) {
        if (options.isVectors) {
            drawVectors(gl, frame);
        } else {
            drawBoxes(gl, frame);
        }
    }

    public void gradientsHintReset() {
        if (gradients.isEmpty() || surfaces.isEmpty()) return;
        if (surfaces.get(0).points.isEmpty()) return;
        for (HintGradient gradient : gradients) {
            if (!gradient.isValid()) continue;
            gradient.setHintMax(lenProjection(surfaces.get(0).points.get(0), gradient));
            gradient.setHintMin(lenProjection(surfaces.get(0).points.get(0), gradient));
        }
    }

    public void gradientsHintCalculate() {
        //if (gradients.isEmpty() || surfaces.isEmpty()) return;
        for (HintGradient gradient : gradients) {
            if (!gradient.isValid()) continue;
            for (SurfacePointsList surface : surfaces) {
                for (SurfacePoint point : surface.points) {
                    gradient.setHintMax(Math.max(gradient.getHintMax(), lenProjection(point, gradient)));
                    gradient.setHintMin(Math.min(gradient.getHintMin(), lenProjection(point, gradient)));
                }
            }
        }
    }

    public void gradientsApply() {
        //if (gradients.isEmpty() || surfaces.isEmpty()) return;
        for (SurfacePointsList surface : surfaces) {
            for (SurfacePoint point : surface.points) {
                int i = 0;
                for (Gradient gradient : gradients) {
                    if (!gradient.isValidStrict()) continue;
                    Color toAppend = colorMap(point, gradient);
                    point.color = colorWeightedMix(point.color, toAppend, i);
                    i++;
                }
            }
        }
    }

    public double getMaxVectorLength() {
        if (surfaces.isEmpty()) return 0;
        if (surfaces.get(0).points.isEmpty()) return 0;
        double max = surfaces.get(0).points.get(0).vector.length();
        for (SurfacePointsList surface : surfaces) {
            for (SurfacePoint point : surface.points) {
                max = Math.max(point.vector.length(), max);
            }
        }
        return max;
    }

    public Vec3d getMinPos() {
        if (surfaces.isEmpty()) return new Vec3d();
        if (surfaces.get(0).points.isEmpty()) return new Vec3d();
        Vec3d min = new Vec3d(surfaces.get(0).points.get(0).position);
        for (SurfacePointsList surface : surfaces) {
            min.x = Math.min(min.x, surface.min.position.x);
            min.y = Math.min(min.y, surface.min.position.y);
            min.z = Math.min(min.z, surface.min.position.z);
        }
        return min;
    }

    public Vec3d getMaxPos() {
        if (surfaces.isEmpty()) return new Vec3d();
        if (surfaces.get(0).points.isEmpty()) return new Vec3d();
        Vec3d max = new Vec3d(surfaces.get(0).points.get(0).position);
        for (SurfacePointsList surface : surfaces) {
            max.x = Math.max(max.x, surface.max.position.x);
            max.y = Math.max(max.y, surface.max.position.y);
            max.z = Math.max(max.z, surface.max.position.z);
        }
        return max;
    }

    private Color colorWeightedMix(Color original, Color toAppend, double weight) {
        double red = (original.getRed() * weight + toAppend.getRed()) / (weight + 1);
        double green = (original.getGreen() * weight + toAppend.getGreen()) / (weight + 1);
        double blue = (original.getBlue() * weight + toAppend.getBlue()) / (weight + 1);
        return new Color(red, green, blue, 1.0);
    }

    private Color colorMap(SurfacePoint point, Gradient gradient) {
        if (!gradient.isValidStrict()) return new Color(0, 0, 0, 1);
        GradientPoint lower, upper;

        lower = upper = new GradientPoint(0, Color.BLACK);

        double len = Map.map(
                lenProjection(point, gradient),
                gradient.getMinVector(),
                gradient.getMaxVector(),
                -1.0,
                1.0);

        int iterator = 0;
        for (GradientPoint innerPoint : gradient.getPoints()) {
            if (iterator > 0 && iterator < gradient.getPoints().size()) {
                upper = innerPoint;
                if (len < innerPoint.getOffset()) break;
            }
            if (iterator < gradient.getPoints().size() - 1) lower = innerPoint;
            iterator++;
        }


        double red = Map.map(
                len,
                lower.getOffset(),
                upper.getOffset(),
                lower.getColor().getRed(),
                upper.getColor().getRed());
        double green = Map.map(
                len,
                lower.getOffset(),
                upper.getOffset(),
                lower.getColor().getGreen(),
                upper.getColor().getGreen());
        double blue = Map.map(
                len,
                lower.getOffset(),
                upper.getOffset(),
                lower.getColor().getBlue(),
                upper.getColor().getBlue());
        return new Color(red, green, blue, 1.0);
    }

    private double lenProjection(SurfacePoint point, Gradient gradient) {
        if (!gradient.isValid()) return 0;
        return point.vector.dot(gradient.getReference()) / gradient.getReference().dot(gradient.getReference());
    }
}
