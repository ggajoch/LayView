package moleculesampleapp;

import javafx.scene.transform.Scale;

/**
 * Created by Piotr on 06/02/2016.
 */
public class DArrow extends Arrow {
    private double scaleFactor;
    private Scale scale;
    public DArrow(){
        this(300, 50, 100, 100);
    }

    public DArrow(double len_, double radius_, double tipRadius_, double tipLen_){
        this(len_, radius_, tipRadius_, tipLen_, 0.001);
    }

    public DArrow(double len_, double radius_, double tipRadius_, double tipLen_, double scaleFactor_){
        super();
        scaleFactor = scaleFactor_;
        scale = new Scale(scaleFactor,scaleFactor,scaleFactor);
        this.getTransforms().add(scale);
        this.setLen(len_);
        this.setRadius(radius_);
        this.setTipRadius(tipRadius_);
        this.setTipLen(tipLen_);
    }

    void setLen(double len_){
        super.setLen((int)(len_/scaleFactor));
    }

    void setRadius(double radius_){
        super.setRadius((int)(radius_/scaleFactor));
    }

    void setTipRadius(double tipRadius_){
        super.setTipRadius((int)(tipRadius_/scaleFactor));
    }

    void setTipLen(double tipLen_){
        super.setTipLen((int)(tipLen_/scaleFactor));
    }

}
