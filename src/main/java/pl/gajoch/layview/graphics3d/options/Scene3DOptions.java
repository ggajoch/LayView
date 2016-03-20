
package pl.gajoch.layview.graphics3d.options;

import com.sun.javafx.geom.Vec3d;

public class Scene3DOptions {
    public final VectorProperties vectorProperties;
    public final BoxProperties boxProperties;
    public double globalScale, FPS;
    public final HintGradient gradient1, gradient2;
    public boolean isVectors;
    public boolean gradient2enable;
    public Vec3d initialTranslate;

    public Scene3DOptions(double tipLen, double tipRadius, double radius, double lenScale,
                          Vec3d boxSize,
                          double globalScale, double FPS,
                          final HintGradient gradient1, final HintGradient gradient2,
                          boolean gradient2enable, boolean isVectors) {
        this.vectorProperties = new VectorProperties(tipLen, tipRadius, radius, lenScale);
        this.boxProperties = new BoxProperties(boxSize);
        this.globalScale = globalScale;
        this.FPS = FPS;
        this.gradient1 = gradient1;
        this.gradient2 = gradient2;
        this.initialTranslate = new Vec3d();
        this.gradient2enable = gradient2enable;
        this.isVectors = isVectors;
    }

    public Scene3DOptions(Scene3DOptions second) {
        this.vectorProperties = new VectorProperties(second.vectorProperties);
        this.boxProperties = new BoxProperties(second.boxProperties);
        this.globalScale = second.globalScale;
        this.FPS = second.FPS;
        this.gradient1 = new HintGradient(second.gradient1);
        this.gradient2 = new HintGradient(second.gradient2);
        this.initialTranslate = new Vec3d(second.initialTranslate);
        this.gradient2enable = second.gradient2enable;
        this.isVectors = second.isVectors;
    }
}