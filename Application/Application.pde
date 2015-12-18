/**
 * RGB Cube.
 * 
 * The three primary colors of the additive color model are red, green, and blue.
 * This RGB color cube displays smooth transitions between these colors. 
 */

import g4p_controls.*;
import peasy.*;

PeasyCam cam;
 
ColourSurface surface_1, surface_2;
FileParser parser;
VideoExport export;
Gradient gradientMaker;

float pitchScale = 200000000.0;
float lengthScale = 1.0/1000000.0;
 
void setup()  { 
  size(1024, 768, P3D); 
  noStroke(); 
  colorMode(RGB, 1); 
  
  export = new VideoExport();
  export.cleanFolder();
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(1);
  cam.setMaximumDistance(10000);
  
  //surface_1 = new Surface(0.05, 0.05, 0.05);
  //surface_1 = new VectorSurface(0.05,0.05,0.05,0.2);
  parser = new FileParser();
  createGUI();
  gradientList = new GradientPointsList();
  gradientMaker = new Gradient();
} 

void showFile(String file) {
  
  
  //parser.parseFile("../TestFiles/x.omf");
  parser.parseFile(file);
  
  print("Parsing file:");
  println(file);
  surface_2 = new ColourSurface((float)parser.segments.get(0).header.getDouble("xstepsize")*pitchScale,
  (float)parser.segments.get(0).header.getDouble("ystepsize")*pitchScale,
  (float)parser.segments.get(0).header.getDouble("zstepsize")*pitchScale,0.2*lengthScale);
  
  //print();
  ArrayList<PointVector> pp = parser.segments.get(0).data.points;
  for( PointVector p: pp ) {
    
    p.position.x *= pitchScale;
    p.position.y *= pitchScale;
    p.position.z *= pitchScale;
    
    p.rgbcolor.x = 1;
    p.rgbcolor.y = 0;
    p.rgbcolor.z = 1;
    
    
    if(p.vector.module()>0.0) surface_2.addPoint(p);
  }
  
  gradientMaker.points = gradientList.DropListElements;
  gradientMaker.reference = new DVector(0,0,1);
  
  surface_2.gradientMakers.add(gradientMaker);
  surface_2.colourPrepare();
  
  
  /*for(float x=-1.0; x<=1.0 ; x += 0.05*2){
      for(float y=-1.0; y<=1.0; y += 0.05*2){
        for(float z=-1.0; z<=0; z += 0.05*2){
          if(sqrt(pow((x),2)+pow((y),2)+pow((z),2))<=1.0){
            surface_1.addPoint(x,y,z,x,y,z,(x+1.0)/2.0,0,1.0-(x+1.0)/2.0);
          }
        }
      }
  }*/
}

int vectors = 0;

void draw()  { 
  background(0.5);
  
  scale(200);
 
  //draw surface
  //surface_1.drawBox();
  //surface_1.drawVectorsVolume();
  
  if( surface_2 != null ) {
    if( vectors == 1 ) {
      surface_2.drawVectorsVolume();
    } else {
      surface_2.drawBox();
    }
  }
    
  //

  cam.beginHUD();
  //2D Overlay
  textSize(32);
  text(mouseX, 10, 30);
  cam.endHUD();
  
  if(frameCount<1024){
    export.saveVideoFrame();
  }else if(frameCount==1024){
    export.closeVideo();
  }

} 