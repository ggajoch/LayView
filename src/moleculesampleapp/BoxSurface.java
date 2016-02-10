package moleculesampleapp;

import com.sun.javafx.geom.Vec3d;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

/**
 * Created by Piotr on 06/02/2016.
 */
public class BoxSurface {
    protected ArrayList<VectorPoint> points;
    public VectorPoint min, max;
    protected Vec3d pitch;
    protected double pitchScale;

    public BoxSurface(){
        this(new Vec3d(10,10,10), 1.0);
    }

    public BoxSurface(Vec3d pitch_, double pitchScale_){
        points = new ArrayList<>();
        min = new VectorPoint();
        max = new VectorPoint();
        pitch = pitch_;
        pitchScale = pitchScale_;
    }

    public void add(VectorPoint point){
        if(points.isEmpty()){
            max = new VectorPoint(point);
            min = new VectorPoint(point);
        }else{
            if(max.position.x < point.position.x) max.position.x = point.position.x;
            if(max.position.y < point.position.y) max.position.y = point.position.y;
            if(max.position.z < point.position.z) max.position.z = point.position.z;

            if(max.vector.x < point.vector.x) max.vector.x = point.vector.x;
            if(max.vector.y < point.vector.y) max.vector.y = point.vector.y;
            if(max.vector.z < point.vector.z) max.vector.z = point.vector.z;
        }
        points.add(point);
    }

    public Group getBoxGroup(){
        final Group group = new Group();
        for(VectorPoint point : points) {
            Box element = new Box(pitch.x*pitchScale, pitch.y*pitchScale, pitch.z*pitchScale);
            PhongMaterial elementMaterial = new PhongMaterial();
            elementMaterial.setDiffuseColor(point.color);
            elementMaterial.setSpecularColor(Color.gray(0));
            element.setMaterial(elementMaterial);
            element.getTransforms().add(new Translate(point.position.x*pitchScale,point.position.y*pitchScale,point.position.z*pitchScale));
            group.getChildren().add(element);
        }
        return group;
    }

}
