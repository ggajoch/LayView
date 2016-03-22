package pl.gajoch.layview.graphics2d;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

public class PlotPointsList extends ArrayList<PlotPoint> {
    private Rectangle2D rectangle;

    public PlotPointsList() {
        rectangle = new Rectangle(0, 0, 0, 0);
    }

    public final Rectangle2D getBounds() {
        return rectangle;
    }

    @Override
    public boolean add(PlotPoint point) {
        rectangle.add(point.get());
        super.add(point);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends PlotPoint> collection) {
        collection.forEach(this::add);
        return true;
    }

}
