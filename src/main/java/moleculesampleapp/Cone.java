package moleculesampleapp;

import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * Created by Piotr on 10/02/2016.
 */
public class Cone extends MeshView {
    public Cone(double radius, double height, int divisions) {
        TriangleMesh mesh = new TriangleMesh();
        mesh.getTexCoords().addAll(0, 0);
        mesh.getPoints().addAll(0, 0, (float) height);//top center
        for (int division = 0; division < divisions; division++) {
            double angle = Math.PI / (float) divisions * (float) division * 2.0;
            mesh.getPoints().addAll(
                    (float) (Math.sin(angle) * radius),
                    (float) (Math.cos(angle) * radius),
                    0
            );
            int face = division + 1;
            int next = (division + 1) % divisions + 1;
            mesh.getFaces().addAll(
                    0, 0, face, 0, next, 0,
                    divisions + 1, 0, next, 0, face, 0,
                    0, 0, next, 0, face, 0,//different order - facing opposite side!
                    divisions + 1, 0, face, 0, next, 0 //different order - facing opposite side!
            );
        }
        mesh.getPoints().addAll(0, 0, 0);//bottom center
        this.setMesh(mesh);
        this.setDrawMode(DrawMode.FILL);
    }
}
