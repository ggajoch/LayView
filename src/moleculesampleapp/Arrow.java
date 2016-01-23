package moleculesampleapp;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * Created by Piotr on 23/01/2016.
 */
public class Arrow extends MeshView {
    private double len, radius, tipRadius, tipLen;
    private TriangleMesh mesh;
    private void generate(){
        float h = 150;                    // Height
        float s = 300;                    // Side

        this.mesh = new TriangleMesh();

        this.mesh.getTexCoords().addAll(0,0);

        this.mesh.getPoints().addAll(
                0,    0,    0,            // Point 0 - Top
                0,    h,    -s/2,         // Point 1 - Front
                -s/2, h,    0,            // Point 2 - Left
                s/2,  h,    0,            // Point 3 - Back
                0,    h,    s/2           // Point 4 - Right
        );

        this.mesh.getFaces().addAll(
                0,0,  2,0,  1,0,          // Front left face
                0,0,  1,0,  3,0,          // Front right face
                0,0,  3,0,  4,0,          // Back right face
                0,0,  4,0,  2,0,          // Back left face
                4,0,  1,0,  2,0,          // Bottom rear face
                4,0,  3,0,  1,0           // Bottom front face
        );

        this.setMesh(this.mesh);
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.DARKRED);
        this.setDrawMode(DrawMode.FILL);
        this.setMaterial(redMaterial);
        this.setTranslateX(200);
        this.setTranslateY(100);
        this.setTranslateZ(200);
    }

    Arrow(){
        super();
        this.len = this.radius = this.tipRadius = this.tipLen = 1.0;
        this.generate();
    }

    Arrow(double len_, double radius_, double tipRadius_, double tipLen_){
        this.len = len_;
        this.radius = radius_;
        this.tipRadius = tipRadius_;
        this.tipLen = tipLen_;
        this.generate();
    }
}
