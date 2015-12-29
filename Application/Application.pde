/**
 * RGB Cube.
 * 
 * The three primary colors of the additive color model are red, green, and blue.
 * This RGB color cube displays smooth transitions between these colors. 
 */

import g4p_controls.*;
import peasy.*;

PeasyCam cam;
 
//volatile ColourSurface surface_1, surface_2;

ArrayList<ColourSurface> Surfaces;
//FileParser parser;
VideoExport export;
Gradient gradientMaker;

float pitchScale = 200000000.0;
float lengthScale = 1.0/1000000.0;
 
void setup()  { 
  size(1024, 768, P3D); 
  noStroke(); 
  colorMode(RGB, 1); 
  
  /*export = new VideoExport();
  export.cleanFolder();*/
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(1);
  cam.setMaximumDistance(10000);
  
  //surface_1 = new Surface(0.05, 0.05, 0.05);
  //surface_1 = new VectorSurface(0.05,0.05,0.05,0.2);
  //parser = new FileParser();
  createGUI();
  gradientList = new GradientPointsList();
  gradientMaker = new Gradient();
  Surfaces = new ArrayList<ColourSurface>();
} 
Object mutex = new Object();

void showFile(String file) {
  
  synchronized(mutex) {
    //parser.parseFile("../TestFiles/x.omf");
    FileParser parser = new FileParser();
    parser.parseFile(file);
    
    print("Parsing file:");
    println(file);
    
    if(Surfaces.size()>0){
      Surfaces.remove(0);
    }
    
    Surfaces.add(new ColourSurface((float)parser.segments.get(0).header.getDouble("xstepsize")*pitchScale,
    (float)parser.segments.get(0).header.getDouble("ystepsize")*pitchScale,
    (float)parser.segments.get(0).header.getDouble("zstepsize")*pitchScale,0.2*lengthScale));
    
    //print();
    ArrayList<PointVector> pp = parser.segments.get(0).data.points;
    for( PointVector p: pp ) {
    
    p.position.x *= pitchScale;
    p.position.y *= pitchScale;
    p.position.z *= pitchScale;
    
    p.rgbcolor.x = 1;
    p.rgbcolor.y = 0;
    p.rgbcolor.z = 1;
    
    
    if(p.vector.module()>0.0) Surfaces.get(Surfaces.size()-1).addPoint(p);
    }
    
    gradientMaker.points = gradientList.DropListElements;
    gradientMaker.reference = new DVector(0,0,1);
    
    Surfaces.get(Surfaces.size()-1).gradientMakers.add(gradientMaker);
    Surfaces.get(Surfaces.size()-1).colourPrepare();
  }
}

int vectors = 0;

void draw()  { 
  background(0.5);
  
  scale(200);
 
  //draw surface
  //surface_1.drawBox();
  //surface_1.drawVectorsVolume();
  synchronized(mutex) {
    if( Surfaces.size() >0 ) {
    if( vectors == 1 ) {
      Surfaces.get(Surfaces.size()-1).drawVectorsVolume();
    } else {
      Surfaces.get(Surfaces.size()-1).drawBox();
    }
    }
  }
  //

  cam.beginHUD();
  //2D Overlay
  textSize(32);
  text(mouseX, 10, 30);
  cam.endHUD();
  
  /*if(frameCount<1024){
    export.saveVideoFrame();
  }else if(frameCount==1024){
    export.closeVideo();
  }*/

} 