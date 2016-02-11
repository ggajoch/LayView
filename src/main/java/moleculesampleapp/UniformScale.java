package moleculesampleapp;

import javafx.scene.transform.Scale;

/**
 * Created by Piotr on 11/02/2016.
 */
public class UniformScale extends Scale {
    public UniformScale(){
        super(1,1,1);
    }

    public void set(double scale){
        this.setX(scale);
        this.setY(scale);
        this.setZ(scale);
    }
}
