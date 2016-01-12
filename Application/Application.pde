/**
 * LayView - application to display OOMF files as 3D visualisation
 * Authors:
 * Grzegorz Gajoch
 * Piotr Rzeszut
 * 
 */

DisplayManager display;
DisplayGenerator second;

void settings(){
  size(1024, 768, P3D);
}

void setup(){ 
  noStroke();
  colorMode(RGB, 1);
  
  display = new DisplayManager("Main",this);
  
  createGUI();
  GradientEditWindow.setVisible(false);
  GradientPointEditWindow.setVisible(false);
  InputSelectorWindow.setVisible(false);
  DisplayOptionsWindow.setVisible(false);
  VideoControlWindow.setVisible(false);
  
  display.mySetup();
  display.display_options_manager.updateDisplays();
  second = new DisplayGenerator();
  String[] args = {"YourSketchNameHere"};
  PApplet.runSketch(args, second);
  
} 

void draw()  { 
  background(0.5);
  scale(200);
  display.myDraw();
} 