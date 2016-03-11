package pl.gajoch.layview.utils.parsers.OMFParser;

import pl.gajoch.layview.graphics3d.SurfacePoint;

import java.util.List;

public class OMFData {
    final public List<SurfacePoint> points;
    final public double xStepSize, yStepSize, zStepSize;

    public OMFData(List<SurfacePoint> points, double xStepSize, double yStepSize, double zStepSize) {
        this.points = points;
        this.xStepSize = xStepSize;
        this.yStepSize = yStepSize;
        this.zStepSize = zStepSize;
    }
}
