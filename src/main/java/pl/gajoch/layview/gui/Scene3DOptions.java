package pl.gajoch.layview.gui;

public class Scene3DOptions {
    final double tipLen, tipRadius, radius, lenScale, globalScale, FPS;

    public Scene3DOptions(double tipLen, double tipRadius, double radius, double lenScale,
                          double globalScale, double FPS) {
        this.tipLen = tipLen;
        this.tipRadius = tipRadius;
        this.radius = radius;
        this.lenScale = lenScale;
        this.globalScale = globalScale;
        this.FPS = FPS;
    }

    public Scene3DOptions(Scene3DOptions second) {
        this.tipLen = second.tipLen;
        this.tipRadius = second.tipRadius;
        this.radius = second.radius;
        this.lenScale = second.lenScale;
        this.globalScale = second.globalScale;
        this.FPS = second.FPS;
    }
}
