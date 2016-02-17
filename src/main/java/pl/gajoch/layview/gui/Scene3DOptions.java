package pl.gajoch.layview.gui;

import pl.gajoch.layview.graphics3d.VectorProperties;

public class Scene3DOptions {
    public final VectorProperties vectorProperties;
    public final double globalScale, FPS;
    public final HintGradient gradient1, gradient2;

    public Scene3DOptions(double tipLen, double tipRadius, double radius, double lenScale,
                          double globalScale, double FPS,
                          final HintGradient gradient1, final HintGradient gradient2) {
        vectorProperties = new VectorProperties(tipLen, tipRadius, radius, lenScale);
        this.globalScale = globalScale;
        this.FPS = FPS;
        this.gradient1 = gradient1;
        this.gradient2 = gradient2;
    }

    public Scene3DOptions(Scene3DOptions second) {
        vectorProperties = new VectorProperties(second.vectorProperties);
        this.globalScale = second.globalScale;
        this.FPS = second.FPS;
        this.gradient1 = new HintGradient(second.gradient1);
        this.gradient2 = new HintGradient(second.gradient2);
    }
}
