/**
 * RGB Cube.
 * 
 * The three primary colors of the additive color model are red, green, and blue.
 * This RGB color cube displays smooth transitions between these colors. 
 */
 
float xmag, ymag = 0;
float newXmag, newYmag = 0; 
float scale_val = 90;
float trans_x;
float trans_y;
 
Arcball arcball;
Surface surface_1;
 
void setup()  { 
  size(640, 360, P3D); 
  noStroke(); 
  colorMode(RGB, 1); 
  arcball = new Arcball(width/2, height/2, 600); 
  surface_1 = new Surface();
  
  trans_x = width/2;
  trans_y = height/2;
} 

void mouseWheel(MouseEvent event) {
  float e = event.getCount();
  scale_val += e*5.0;
  if(scale_val<5.0)scale_val = 5.0;
}

void mousePressed(){
  if(mouseButton == LEFT){
    arcball.mousePressed();
  }
}

void mouseDragged(){
  if(mouseButton == LEFT){
    arcball.mouseDragged();
  }else{
    trans_x += mouseX-pmouseX;
    trans_y += mouseY-pmouseY;
  }
}
 
void draw()  { 
  background(0.5);
  
  pushMatrix(); 
  
  translate(width/2, height/2, -30); 
  
  arcball.run();
  
  translate(trans_x-width/2, trans_y-height/2, -30); 
  
  scale(scale_val);
  
  surface_1.draw();
  
  popMatrix(); 
  hint(DISABLE_DEPTH_TEST);
  textSize(32);
  
  text(mouseX, 10, 30);
  text(mouseY, 10, 60); 
  text(scale_val, 10, 90); 
  hint(ENABLE_DEPTH_TEST);
} 