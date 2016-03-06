package pl.gajoch.layview.graphics3d;

import java.util.ArrayList;

/**
 * Created by Piotr on 22/02/2016.
 */
public class TrigonometricTab {
    private double[] sinTab;
    private double[] cosTab;
    private int divisions;
    public TrigonometricTab(int divisions){
        this.divisions = divisions;
        final double angleJump = Math.PI * 2 / divisions;
        sinTab = new double[divisions];
        cosTab = new double[divisions];
        for(int i = 0 ; i < divisions ; i++){
            sinTab[i] = Math.sin(angleJump * i);
            cosTab[i] = Math.cos(angleJump * i);
        }
    }

    public double sin(int division){
        for(;division<0;division+=this.divisions);
        return sinTab[division % this.divisions];
    }

    public double cos(int division){
        for(;division<0;division+=this.divisions);
        return cosTab[division % this.divisions];
    }
}
