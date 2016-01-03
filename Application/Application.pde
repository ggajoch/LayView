/**
 * LayView - application to display OOMF 
 * 
 */

import g4p_controls.*;
import peasy.*;

PeasyCam cam;

volatile ArrayList<ColourSurface> Surfaces;
VideoExport export;

float pitchScale = 200000000.0;
float lengthScale = 0.2/1000000.0;
float minimumDisplay = 0.0;

long frameDelayMs = 10;
FileView file_list;

GradientManager gradients;
DisplayOptionsManager display_options_manager = new DisplayOptionsManager();

void setup()  { 
  size(1024, 768, P3D);
  noStroke();
  colorMode(RGB, 1);
  
  /*export = new VideoExport();
  export.cleanFolder();*/
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(1);
  cam.setMaximumDistance(10000);
  
  createGUI();
  GradientEditWindow.setVisible(false);
  GradientPointEditWindow.setVisible(false);
  InputSelectorWindow.setVisible(false);
  DisplayOptionsWindow.setVisible(false);
  VideoControlWindow.setVisible(false);
  file_list = new FileView(listInputListFiles);
  gradients = new GradientManager(GradientEditWindow, padGradColorPrev, padGradGradPrev, GradientEditWindow);
  display_options_manager.updateDisplays();
  
  Surfaces = new ArrayList<ColourSurface>();
} 
Object mutex = new Object();


void showFiles(List<File> files) {
  Surfaces.clear();
  for( File ffile : files ) {
    String file = ffile.getAbsolutePath();
    synchronized(mutex) {
      FileParser parser = new FileParser();
      parser.parseFile(file);
      
      
      
      print("Parsing file:");
      println(file);
      
      if(Surfaces.size()>0){
        //Surfaces.remove(0);
      }
      
      Surfaces.add(new ColourSurface((float)parser.segments.get(0).header.getDouble("xstepsize")*pitchScale,
      (float)parser.segments.get(0).header.getDouble("ystepsize")*pitchScale,
      (float)parser.segments.get(0).header.getDouble("zstepsize")*pitchScale,lengthScale));
      
      //print();
      ArrayList<PointVector> pp = parser.segments.get(0).data.points;
      for( PointVector p: pp ) {
      
      p.position.x *= pitchScale;
      p.position.y *= pitchScale;
      p.position.z *= pitchScale;
      
      p.rgbcolor.x = 0;
      p.rgbcolor.y = 1;
      p.rgbcolor.z = 0;
      
      
      if(p.vector.module()>minimumDisplay) Surfaces.get(Surfaces.size()-1).addPoint(p);
      
      }
      
    
      Gradient gradientMaker = new Gradient();
      gradientMaker.points = gradients.getList(0);
      gradientMaker.reference = gradients.getReference(0);
      
      Surfaces.get(Surfaces.size()-1).gradientMakers.add(gradientMaker);
      
      
      gradientMaker = new Gradient();
      gradientMaker.points = gradients.getList(1);
      gradientMaker.reference = gradients.getReference(1);  
      
      Surfaces.get(Surfaces.size()-1).gradientMakers.add(gradientMaker);
      
      
      Surfaces.get(Surfaces.size()-1).gradientMaxFind();
      Surfaces.get(Surfaces.size()-1).colourPrepare();
      
      Surfaces.get(Surfaces.size()-1).translatePoints(Surfaces.get(Surfaces.size()-1).max.add(Surfaces.get(Surfaces.size()-1).min).multiplyNumber(-0.5));
    }
  }
}

int vectors = 0;
int record = 0;

int frame = 0;

long lastMillis = 0;

void draw()  { 
  
  background(0.5);
  
  scale(200);
  if(record == 2){
    lastMillis=millis();
    frame=0;
    record = 1;
    export = new VideoExport();
    export.cleanFolder();
    println("record started");
  }
  if((millis()-lastMillis)>=frameDelayMs){
    lastMillis=millis();
    frame++;
    if(frame>=Surfaces.size()){
      frame = 0;
      if(record == 1){
        record = 0;
        export.closeVideo();
        println("Saved");
      }
    }
  }
 
  synchronized(mutex) {
    if( Surfaces.size() >0 ) {
      if( vectors == 1 ) {
        Surfaces.get(frame).drawVectorsVolume();
      } else {
        Surfaces.get(frame).drawBox();
      }
    }
  }
  
  cam.beginHUD();
  //2D Overlay
  textSize(32);
  text(mouseX, 10, 30);
  cam.endHUD();
  
  if(record == 1)export.saveVideoFrame();
  
  
  /*if(frameCount<1024){
    export.saveVideoFrame();
  }else if(frameCount==1024){
    export.closeVideo();
  }*/

} 