package pl.gajoch.layview.graphics3d;

import javafx.scene.paint.Color;
import pl.gajoch.layview.gui.Gradient;
import pl.gajoch.layview.gui.GradientPoint;
import pl.gajoch.layview.gui.HintGradient;

import java.util.ArrayList;

/**
 * Created by Piotr on 15/02/2016.
 */
public class GradientSurfacePointsList extends SurfacePointsList {
    public ArrayList<HintGradient> gradients;

    public GradientSurfacePointsList() {
        super();
        gradients = new ArrayList<>();
    }

    public void GradientsHintReset() {
        if (gradients.isEmpty() || points.isEmpty()) return;
        for (HintGradient gradient : gradients) {
            gradient.setHintMax(lenProjection(points.get(0), gradient));
            gradient.setHintMin(lenProjection(points.get(0), gradient));
        }
    }

    public void GradientsHintCalculate() {
        if (gradients.isEmpty() || points.isEmpty()) return;
        for (HintGradient gradient : gradients) {
            for (SurfacePoint point : points) {
                gradient.setHintMax(Math.max(gradient.getHintMax(), lenProjection(point, gradient)));
                gradient.setHintMin(Math.min(gradient.getHintMin(), lenProjection(point, gradient)));
            }
        }
    }

    public void GradientsApply() {
        if (gradients.isEmpty() || points.isEmpty()) return;
        for (SurfacePoint point : points) {
            int i = 0;
            for (Gradient gradient : gradients) {
                Color toAppend = colorMap(point, gradient);
                point.color = colorWeightedMix(point.color, toAppend, i);
                i++;
            }
        }
    }

    private Color colorWeightedMix(Color original, Color toAppend, double weight) {
        double red = (original.getRed() * weight + toAppend.getRed()) / (weight + 1);
        double green = (original.getGreen() * weight + toAppend.getGreen()) / (weight + 1);
        double blue = (original.getBlue() * weight + toAppend.getBlue()) / (weight + 1);
        return new Color(red, green, blue, 1.0);
    }

    private Color colorMap(SurfacePoint point, Gradient gradient) {
        GradientPoint lower, upper;

        lower = upper = new GradientPoint(0, Color.BLACK);

        double len = map(
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


        double red = map(
                len,
                lower.getOffset(),
                upper.getOffset(),
                lower.getColor().getRed(),
                upper.getColor().getRed());
        double green = map(
                len,
                lower.getOffset(),
                upper.getOffset(),
                lower.getColor().getGreen(),
                upper.getColor().getGreen());
        double blue = map(
                len,
                lower.getOffset(),
                upper.getOffset(),
                lower.getColor().getBlue(),
                upper.getColor().getBlue());
        return new Color(red, green, blue, 1.0);
    }

    private double map(double x, double inMin, double inMax, double outMin, double outMax) {
        x = (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
        double absMax = Math.max(outMax, outMin);
        double absMin = Math.min(outMax, outMin);
        if (x > absMax) x = absMax;
        if (x < absMin) x = absMin;
        return x;
    }

    private double lenProjection(SurfacePoint point, Gradient gradient) {
        return point.vector.dot(gradient.getReference()) / gradient.getReference().dot(gradient.getReference());
    }
}