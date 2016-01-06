import java.text.*;
import g4p_controls.GTextField;
import g4p_controls.GWindow;

public class DisplayOptionsManager {
    private double threshold, ArrowScale, ArrowWidth, ArrowTip, ArrowTipRadius,
                   ScaleValues;
    private int    FPS;

    private boolean vectors;

    public DisplayOptionsManager() {
        this.threshold = 0;
        this.ArrowScale = 0.2/1000000.0;
        this.ArrowWidth = 1e-2;
        this.ArrowTip = 0.0375;
        this.ArrowTipRadius = 0.025;
        this.ScaleValues = 200000000.0;
        this.FPS = 50;
    }

    private void updateField(GTextField field, double value) {
        NumberFormat formatter = new DecimalFormat("0.######E0");
        
        field.setText(formatter.format(value));
    }

    private double getField(GTextField field) {
        return Double.valueOf(field.getText());
    }

    public void updateDisplays() {
        updateField(valDisplayThreshold, threshold);
        updateField(valDisplayScale, ArrowScale);
        updateField(valDisplayWidth, ArrowWidth);
        updateField(valDisplayTip, ArrowTip);
        updateField(valDisplayValues, ScaleValues);
        updateField(valDisplayTipRadius, ArrowTipRadius);
        valVideoFPS.setText(Integer.toString(FPS));
    }

    public void getFromDisplays() {
        threshold = getField(valDisplayThreshold);
        ArrowScale = getField(valDisplayScale);
        ArrowWidth = getField(valDisplayWidth);
        ArrowTip = getField(valDisplayTip);
        ArrowTipRadius = getField(valDisplayTipRadius);
        ScaleValues = getField(valDisplayValues);
        FPS = Integer.valueOf(valVideoFPS.getText());
    }

    public void open() {
        updateDisplays();
    }

    public void OK_handler() {
        getFromDisplays();
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getArrowScale() {
        return ArrowScale;
    }

    public void setArrowScale(double arrowScale) {
        ArrowScale = arrowScale;
    }

    public double getArrowWidth() {
        return ArrowWidth;
    }

    public void setArrowWidth(double arrowWidth) {
        ArrowWidth = arrowWidth;
    }

    public double getArrowTip() {
        return ArrowTip;
    }

    public void setArrowTip(double arrowTip) {
        ArrowTip = arrowTip;
    }

    public double getScaleValues() {
        return ScaleValues;
    }

    public void setScaleValues(double scaleValues) {
        ScaleValues = scaleValues;
    }

    public double getArrowTipRadius() {
        return ArrowTipRadius;
    }

    public void setArrowTipRadius(double arrowTipRadius) {
        ArrowTipRadius = arrowTipRadius;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int fps) {
        FPS = fps;
    }
    
    public boolean isVectors() {
        return vectors;
    }

    public void setVectors(boolean vectors) {
        this.vectors = vectors;
    }
};