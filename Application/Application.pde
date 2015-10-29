/**
 * RGB Cube.
 * 
 * The three primary colors of the additive color model are red, green, and blue.
 * This RGB color cube displays smooth transitions between these colors. 
 */
 
float xmag, ymag = 0;
float newXmag, newYmag = 0; 
float scale_val = 180;
float trans_x;
float trans_y;
 
Arcball arcball;
VectorSurface surface_1;
 
void setup()  { 
  size(640, 360, P3D); 
  noStroke(); 
  colorMode(RGB, 1); 
  arcball = new Arcball(width/2, height/2, 600); 
  //surface_1 = new Surface(0.05, 0.05, 0.05);
  surface_1 = new VectorSurface(0.03);
  
  trans_x = width/2;
  trans_y = height/2;
  
  for(float x=-1.0; x<=1.0 ; x += 0.05*2){
      for(float y=-1.0; y<=x; y += 0.05*2){
        surface_1.addPoint(x,y,0,0,0,x,0,1,0);
      }
  }
} 

void mouseWheel(MouseEvent event) {
  //mouse wheel changes zoom
  float e = event.getCount();
  scale_val += e*5.0;
  if(scale_val<5.0)scale_val = 5.0;
}

void mousePressed(){
  //only left mouse runs rotation, right mouse runs dragging
  if(mouseButton == LEFT){
    arcball.mousePressed();
  }
}

void mouseDragged(){
  //only left mouse runs rotation, right mouse runs dragging
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
  //execute X-Y translation as 2D
  translate(trans_x-width/2, trans_y-height/2);
  
  pushMatrix(); 
  //begin 3D in center of the window
  translate(width/2, height/2, 0); 
  //execute mouse interface for rotation
  arcball.run();
  //scale view
  scale(scale_val);
  
  //draw surface
  //surface_1.drawBox();
  surface_1.drawVectorsVolume();
  
  //surface_1.drawVector(0,0,0,0,0,0);
  
  popMatrix(); 
  popMatrix();
  
  //2D overlay
  hint(DISABLE_DEPTH_TEST);
  textSize(32);
  
  text(mouseX, 10, 30);
  text(mouseY, 10, 60); 
  text(scale_val, 10, 90); 
  
  
  hint(ENABLE_DEPTH_TEST);
} 