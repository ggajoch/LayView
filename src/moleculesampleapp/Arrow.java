package moleculesampleapp;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * Created by Piotr on 23/01/2016.
 */
public class Arrow extends Group {
    private int len, radius, tipRadius, tipLen;
    private int divisions = 20;
    private TriangleMesh mesh;
    private MeshView view;
    private void generate(){
        float h = 200;                    // Height
        float s = 300;                    // Side*/

        this.mesh = new TriangleMesh();
        this.view = new MeshView();
        this.mesh.getTexCoords().addAll(0,0);

        /*this.mesh.getPoints().addAll(
                0,        0,    0,        // Point 0 - Top
                0,        -s/2,   0,      // Point 1 - Front
                -s/2,     0,        0,    // Point 2 - Left
                s/2,      0,          0,  // Point 3 - Back
                0,        s/2,          0// Point 4 - Right
        );

        this.mesh.getFaces().addAll(
                0,0,  2,0,  1,0,          // Front left face
                0,0,  1,0,  3,0,          // Front right face
                0,0,  3,0,  4,0,          // Back right face
                0,0,  4,0,  2,0          // Back left face
        );*/
        this.mesh.getPoints().addAll(0, 0, 200);//top center
        this.mesh.getPoints().addAll(0, 0, 0);//bottom center
        for(int division = 0 ; division < this.divisions ; division++){
            double angle = Math.PI/(float)divisions*(float)division*2.0;
            this.mesh.getPoints().addAll(
                    (int)(Math.sin(angle)*(double)tipRadius),
                    (int)(Math.cos(angle)*(double)tipRadius),
                    0
            );
            int face = division + 2;
            int next = (division + 1)%this.divisions + 2;
            this.mesh.getFaces().addAll(
                    0,0,    face,0,    next,0,
                    1,0,    face,0,    next,0
            );
            System.out.printf("Face:%d %d \r\n",face,next);
        }


        this.view.setMesh(this.mesh);
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.DARKRED);
        this.view.setDrawMode(DrawMode.LINE);
        this.view.setMaterial(redMaterial);
        /*this.setTranslateX(200);
        this.setTranslateY(100);
        this.setTranslateZ(200);*/

        this.setRotationAxis(new Point3D(1.0,0.0,0.0));
        this.setRotate(90);

        this.getChildren().add(view);
    }

    Arrow(){
        super();
        this.radius = this.tipRadius = this.tipLen = 100;
        this.len = 30;
        this.divisions = 4;
        this.generate();
    }

    Arrow(int len_, int radius_, int tipRadius_, int tipLen_){
        this.len = len_;
        this.radius = radius_;
        this.tipRadius = tipRadius_;
        this.tipLen = tipLen_;
        this.generate();
    }
}
