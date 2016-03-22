package pl.gajoch.layview.graphics2d.options;

import java.awt.*;

public class PlotAreaOptions {
    public Rectangle margins;

    public PlotAreaOptions() {
        margins = new Rectangle();
    }

    public PlotAreaOptions(Rectangle margins) {
        this.margins = margins;
    }

    public PlotAreaOptions(PlotAreaOptions second) {
        this.margins = new Rectangle(second.margins);
    }
}
