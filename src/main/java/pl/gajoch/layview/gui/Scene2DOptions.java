
package pl.gajoch.layview.gui;

import pl.gajoch.layview.graphics2d.PlotOptions;

public class Scene2DOptions {
    public final PlotOptions plotOptions;
    public double FPS;
    public boolean isLine;

    public Scene2DOptions(double FPS, PlotOptions plotOptions) {
        this.plotOptions = plotOptions;
        this.FPS = FPS;
        isLine = true;
    }

    public Scene2DOptions(Scene2DOptions second) {
        this.plotOptions = second.plotOptions;
        this.FPS = second.FPS;
        this.isLine = second.isLine;
    }
}