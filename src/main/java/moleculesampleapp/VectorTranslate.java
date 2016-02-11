package moleculesampleapp;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.transform.Translate;

/**
 * Created by Piotr on 11/02/2016.
 */
public class VectorTranslate extends Translate {
    public VectorTranslate() {
        super(0, 0, 0);
    }

    public void set(Vec3d vector) {
        this.setX(vector.x);
        this.setY(vector.y);
        this.setZ(vector.z);
    }
}
