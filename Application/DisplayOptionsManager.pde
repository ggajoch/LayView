import g4p_controls.GTextField;

public class DisplayOptionsManager {
    private double threshold, ArrowScale, ArrowWidth, ArrowTip,
                   ScaleValues, ScaleXYZ;

    private boolean vectors;

    public DisplayOptionsManager() {
        this.threshold = 0;
        this.ArrowScale = 0.2/1000000.0;
        this.ArrowWidth = 1;
        this.ArrowTip = 1;
        this.ScaleValues = 200000000.0;
        this.ScaleXYZ = 1;
    }

    private void updateField(GTextField field, double value) {
        field.setText(Double.toString(value));
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
        updateField(valDisplayXYZ, ScaleXYZ);
    }

    public void getFromDisplays() {
        threshold = getField(valDisplayThreshold);
        ArrowScale = getField(valDisplayScale);
        ArrowWidth = getField(valDisplayWidth);
        ArrowTip = getField(valDisplayTip);
        ScaleValues = getField(valDisplayValues);
        ScaleXYZ = getField(valDisplayXYZ);
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

    public double getScaleXYZ() {
        return ScaleXYZ;
    }

    public void setScaleXYZ(double scaleXYZ) {
        ScaleXYZ = scaleXYZ;
    }

    public boolean isVectors() {
        return vectors;
    }

    public void setVectors(boolean vectors) {
        this.vectors = vectors;
    }
};


DisplayOptionsManager display_options_manager;

