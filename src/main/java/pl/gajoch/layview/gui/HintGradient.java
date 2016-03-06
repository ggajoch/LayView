package pl.gajoch.layview.gui;

public class HintGradient extends Gradient {
    private double hintMin;
    private double hintMax;

    public HintGradient() {
        super();
        hintMax = hintMin = 0;
    }

    public HintGradient(HintGradient second) {
        super(second);
        this.hintMax = second.hintMax;
        this.hintMin = second.hintMin;
    }

    public double getHintMin() {
        return hintMin;
    }

    public void setHintMin(double hintMin) {
        this.hintMin = hintMin;
    }

    public double getHintMax() {
        return hintMax;
    }

    public void setHintMax(double hintMax) {
        this.hintMax = hintMax;
    }
}