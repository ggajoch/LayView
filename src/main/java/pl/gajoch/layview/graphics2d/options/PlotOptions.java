package pl.gajoch.layview.graphics2d.options;

import java.awt.*;

public class PlotOptions {
    public LineOptions lineOptions;

    public AxisOptions xAxisOptions;
    public AxisOptions yAxisOptions;

    public PlotAreaOptions plotAreaOptions;
    public TitleOptions titleOptions;


    public PlotOptions(PlotOptions second) {
        this.lineOptions = new LineOptions(second.lineOptions);
        this.xAxisOptions = new AxisOptions(second.xAxisOptions);
        this.yAxisOptions = new AxisOptions(second.yAxisOptions);
        this.plotAreaOptions = new PlotAreaOptions(second.plotAreaOptions);
        this.titleOptions = new TitleOptions(second.titleOptions);
    }

    public PlotOptions() {
        lineOptions = new LineOptions();
        lineOptions.width = 3;
        lineOptions.symbolRadius = 1;
        lineOptions.isLine = true;
        lineOptions.color = Color.BLACK;
        lineOptions.divisions = 10;

        xAxisOptions = new AxisOptions();
        xAxisOptions.label.text = "X [fs]";

        yAxisOptions = new AxisOptions();
        yAxisOptions.label.text = "Y [kV]";

        plotAreaOptions = new PlotAreaOptions(new Rectangle(40, 20, 40, 60));

        this.titleOptions = new TitleOptions();
        this.titleOptions.title = new RichString("Test", 30, Color.RED);
    }
}
