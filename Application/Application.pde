/**
 * RGB Cube.
 * 
 * The three primary colors of the additive color model are red, green, and blue.
 * This RGB color cube displays smooth transitions between these colors. 
 */
 
float xmag, ymag = 0;
float newXmag, newYmag = 0; 
float scale_val = 90;
 
Arcball arcball;
 
void setup()  { 
  size(640, 360, P3D); 
  noStroke(); 
  colorMode(RGB, 1); 
  arcball = new Arcball(width/2, height/2, 600); 
} 

void mouseWheel(MouseEvent event) {
  float e = event.getCount();
  scale_val += e*5.0;
}

void mousePressed(){
  arcball.mousePressed();
}

void mouseDragged(){
  arcball.mouseDragged();
}
 
void draw()  { 
  background(0.5);
  
  pushMatrix(); 
  translate(width/2, height/2, -30); 
  
  arcball.run();
  
  newXmag = mouseX/float(width) * TWO_PI;
  newYmag = mouseY/float(height) * TWO_PI;
  
  float diff = xmag-newXmag;
  if (abs(diff) >  0.01) { 
    xmag -= diff/4.0; 
  }
  
  diff = ymag-newYmag;
  if (abs(diff) >  0.01) { 
    ymag -= diff/4.0; 
  }
  
  //rotateX(-ymag); 
  //rotateY(-xmag); 
  
  scale(scale_val);
  beginShape(QUADS);

  /*fill(0, 1, 1); vertex(-1,  1,  1);
  fill(1, 1, 1); vertex( 1,  1,  1);
  fill(1, 0, 1); vertex( 1, -1,  1);
  fill(0, 0, 1); vertex(-1, -1,  1);

  fill(1, 1, 1); vertex( 1,  1,  1);
  fill(1, 1, 0); vertex( 1,  1, -1);
  fill(1, 0, 0); vertex( 1, -1, -1);
  fill(1, 0, 1); vertex( 1, -1,  1);

  fill(1, 1, 0); vertex( 1,  1, -1);
  fill(0, 1, 0); vertex(-1,  1, -1);
  fill(0, 0, 0); vertex(-1, -1, -1);
  fill(1, 0, 0); vertex( 1, -1, -1);

  fill(0, 1, 0); vertex(-1,  1, -1);
  fill(0, 1, 1); vertex(-1,  1,  1);
  fill(0, 0, 1); vertex(-1, -1,  1);
  fill(0, 0, 0); vertex(-1, -1, -1);

  fill(0, 1, 0); vertex(-1,  1, -1);
  fill(1, 1, 0); vertex( 1,  1, -1);
  fill(1, 1, 1); vertex( 1,  1,  1);
  fill(0, 1, 1); vertex(-1,  1,  1);

  fill(0, 0, 0); vertex(-1, -1, -1);
  fill(1, 0, 0); vertex( 1, -1, -1);
  fill(1, 0, 1); vertex( 1, -1,  1);
  fill(0, 0, 1); vertex(-1, -1,  1);*/
  
  for(float x=-1.0; x<=1.0 ; x += 0.1){
    for(float y=-1.0; y<=x; y += 0.1){
      float z=((x+y)+2.0)/4.0;
      float v = abs((((float)(millis()%5000))/2500.0)-1.0);
      fill(z,v,1.0-z);
      vertex(x-0.05,y+0.05,0.05);
      vertex(x+0.05,y+0.05,0.05);
      vertex(x+0.05,y-0.05,0.05);
      vertex(x-0.05,y-0.05,0.05);
      
      vertex(x+0.05,y+0.05,0.05);
      vertex(x+0.05,y+0.05,-0.05);
      vertex(x+0.05,y-0.05,-0.05);
      vertex(x+0.05,y-0.05,0.05);
      
      vertex(x+0.05,y+0.05,-0.05);
      vertex(x-0.05,y+0.05,-0.05);
      vertex(x-0.05,y-0.05,-0.05);
      vertex(x+0.05,y-0.05,-0.05);
      
      vertex(x-0.05,y+0.05,-0.05);
      vertex(x-0.05,y+0.05,0.05);
      vertex(x-0.05,y-0.05,0.05);
      vertex(x-0.05,y-0.05,-0.05);
      
      vertex(x-0.05,y+0.05,-0.05);
      vertex(x+0.05,y+0.05,-0.05);
      vertex(x+0.05,y+0.05,0.05);
      vertex(x-0.05,y+0.05,0.05);
      
      vertex(x-0.05,y-0.05,-0.05);
      vertex(x+0.05,y-0.05,-0.05);
      vertex(x+0.05,y-0.05,0.05);
      vertex(x-0.05,y-0.05,0.05);
    }
  }

  endShape(); 
  
  popMatrix(); 
  hint(DISABLE_DEPTH_TEST);
  textSize(32);
  
  text(mouseX, 10, 30);
  text(mouseY, 10, 60); 
  text(scale_val, 10, 90); 
  hint(ENABLE_DEPTH_TEST);
} 