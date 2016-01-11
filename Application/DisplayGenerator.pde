class DisplayGenerator extends PApplet{
  DisplayManager display;
  void settings(){
    size(1024, 768, P3D);
  }
  void setup(){
    noStroke();
    colorMode(RGB, 1);
    display = new DisplayManager("drugi",this);
    display.mySetup();
  }
  void draw(){
    background(0.5);
    scale(200);
    display.myDraw();
  }
}