package pl.gajoch.layview.utils.performance;

public final class Map {
    private Map(){}

    public static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        x = (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
        double absMax = Math.max(outMax, outMin);
        double absMin = Math.min(outMax, outMin);
        if (x > absMax) x = absMax;
        if (x < absMin) x = absMin;
        return x;
    }
}
