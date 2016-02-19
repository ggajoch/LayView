package pl.gajoch.layview;

import processing.core.PApplet;

public class Applet extends PApplet {

    public void setup() {
        size(400, 400, P3D);
        background(0);
        lights();

        pushMatrix();
        translate(130, height/2, 0);
        rotateY(1.25f);
        rotateX(-0.4f);
        noStroke();
        box(100);
        popMatrix();
    }

    public void draw() {
    }
}