import processing.core.*;

class MyApplet extends PApplet {
    private int x, y;
    MyApplet(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void settings() {
        this.size(300, 300);
    }
    public void setup() {
        background(color(255, 0, 0));
        ellipse(x, y, 100, 100);
    }
    public void draw() {

    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MyApplet app = new MyApplet(0,0);
        MyApplet app2 = new MyApplet(100,100);
        String[] a = {"main"};
        PApplet.runSketch(a, app);
        PApplet.runSketch(a, app2);
    }
}
