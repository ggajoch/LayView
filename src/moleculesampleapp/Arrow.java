package moleculesampleapp;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;

/**
 * Created by Piotr on 23/01/2016.
 */
public class Arrow extends Group {
    private int len, radius, tipRadius, tipLen;

    private int divisions;
    private TriangleMesh tipMesh;
    private MeshView tipView;
    private PhongMaterial arrowMaterial;

    private Cylinder line;

    Arrow(){
        super();
        this.tipRadius = this.tipLen = 100;
        this.radius = 50;
        this.len = 300;
        this.divisions = 10;
        this.arrowMaterial = new PhongMaterial();
        this.arrowMaterial.setDiffuseColor(Color.RED);
        this.arrowMaterial.setSpecularColor(Color.DARKRED);
        this.generate();
    }

    Arrow(int len_, int radius_, int tipRadius_, int tipLen_) {
        this.len = len_;
        this.radius = radius_;
        this.tipRadius = tipRadius_;
        this.tipLen = tipLen_;
        this.divisions = 10;
        this.arrowMaterial = new PhongMaterial();
        this.arrowMaterial.setDiffuseColor(Color.RED);
        this.arrowMaterial.setSpecularColor(Color.DARKRED);
        this.generate();
    }

    public void setLen(int len_){
        this.len = len_;
        this.generate();
    }

    public void setRadius(int radius_){
        this.radius = radius_;
        this.generate();
    }

    public void setTipRadius(int tipRadius_){
        this.tipRadius = tipRadius_;
        this.generate();
    }

    public void setTipLen(int tipLen_){
        this.tipLen = tipLen_;
        this.generate();
    }

    public void setMaterial(PhongMaterial material){
        this.arrowMaterial = material;
        this.generate();
    }

    public void setDivisions(int divisions_){
        this.divisions = divisions_;
        this.generate();
    }

    private void generate(){
        //Generate Arrow Tip (Cone)
        this.tipMesh = new TriangleMesh();
        this.tipView = new MeshView();
        this.tipMesh.getTexCoords().addAll(0,0);
        this.tipMesh.getPoints().addAll(0, 0, tipLen);//top center

        for(int division = 0 ; division < this.divisions ; division++){
            double angle = Math.PI/(float)divisions*(float)division*2.0;
            this.tipMesh.getPoints().addAll(
                    (int)(Math.sin(angle)*(double)tipRadius),
                    (int)(Math.cos(angle)*(double)tipRadius),
                    0
            );
            int face = division + 1;
            int next = (division + 1)%this.divisions + 1;
            this.tipMesh.getFaces().addAll(
                    0,0,                    face,0,     next,0,
                    this.divisions+1,0,     next,0,     face,0,
                    0,0,                    next,0,     face,0,//different order - facing opposite side!
                    this.divisions+1,0,     face,0,     next,0 //different order - facing opposite side!
            );
        }
        this.tipMesh.getPoints().addAll(0, 0, 0);//bottom center

        this.tipView.setMesh(this.tipMesh);
        this.tipView.setDrawMode(DrawMode.FILL);
        this.tipView.setMaterial(this.arrowMaterial);
        this.tipView.setTranslateZ((double) len);

        this.getChildren().add(tipView);

        //Generate cylinder
        this.line = new Cylinder(this.radius,this.len);
        this.line.setMaterial(this.arrowMaterial);
        this.line.setRotationAxis(new Point3D(1,0,0));
        this.line.setRotate(90);
        this.line.setTranslateZ(this.len/2.0);
        this.getChildren().add(this.line);
        //position the group, so it's center is the end of the arrow
        /*Sphere positioner = new Sphere(0);
        positioner.setMaterial(this.arrowMaterial);
        positioner.setTranslateZ(-(this.len+this.tipLen));
        //this.positioner.setVisible(false);
        this.getChildren().add(positioner);*/
    }
}
