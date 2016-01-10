/**
 * LayView - application to display OOMF files as 3D visualisation
 * Authors:
 * Grzegorz Gajoch
 * Piotr Rzeszut
 * 
 */

import g4p_controls.*;
import peasy.*;

PeasyCam cam;

volatile ArrayList<ColourSurface> Surfaces;
VideoExport export;

FileView file_list;
Map<Integer, FrameText> frameTextList;

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
  frameTextList = new HashMap<Integer, FrameText>();
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
      
      Surfaces.add(new ColourSurface((float)parser.segments.get(0).header.getDouble("xstepsize"),
      (float)parser.segments.get(0).header.getDouble("ystepsize"),
      (float)parser.segments.get(0).header.getDouble("zstepsize"),(float)display_options_manager.getArrowScale()));
      
      ArrayList<PointVector> pp = parser.segments.get(0).data.points;
      for( PointVector p: pp ) {
      
        p.rgbcolor.x = 0;
        p.rgbcolor.y = 1;
        p.rgbcolor.z = 0;
        
        
        if(p.vector.module()>((float)display_options_manager.getThreshold())) Surfaces.get(Surfaces.size()-1).addPoint(p);
      
      }
      
    
      Gradient gradientMaker = new Gradient(gradients.getList(0), gradients.getReference(0));
      
      Surfaces.get(Surfaces.size()-1).gradientMakers.add(gradientMaker);
      
      if(display_options_manager.getGradient2_enable()){
        gradientMaker = new Gradient(gradients.getList(1), gradients.getReference(1));
        
        Surfaces.get(Surfaces.size()-1).gradientMakers.add(gradientMaker);
      }
      
      Surfaces.get(Surfaces.size()-1).gradientMaxFind();
      Surfaces.get(Surfaces.size()-1).colourPrepare();
      
      Surfaces.get(Surfaces.size()-1).setPitchScale((float)display_options_manager.getScaleValues());
      
      Surfaces.get(Surfaces.size()-1).translatePoints(Surfaces.get(Surfaces.size()-1).max.add(Surfaces.get(Surfaces.size()-1).min).multiplyNumber(-0.5));
    }
  }
}

int record = 0;
int frame = 0;

int play = 1;

boolean to_save = false;

long lastMillis = 0;

void draw()  { 
    
  background(0.5);
  
  scale(200);
  if(record == 2){
    lastMillis=millis();
    frame=0;
    record = 1;
    //export object initialised in gui
    println("record started");
  }
  if((millis()-lastMillis)*display_options_manager.getFPS() >= 1000){
    lastMillis=millis();
    to_save = true;
    if(play != 0) frame++;
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
      Surfaces.get(frame).setPitchScale((float)display_options_manager.getScaleValues());
      if(display_options_manager.isVectors()) {
        Surfaces.get(frame).setVectorScale((float)display_options_manager.getArrowScale());
        Surfaces.get(frame).setVectorWidth((float)display_options_manager.getArrowWidth());//correct in gui default values need to be applied
        Surfaces.get(frame).setTip((float)display_options_manager.getArrowTip());
        Surfaces.get(frame).setTipRadius((float)display_options_manager.getArrowTipRadius());
        Surfaces.get(frame).drawVectorsVolume();
      } else {
        Surfaces.get(frame).drawBox();
      }
    }
  }
  
  cam.beginHUD();
  //2D Overlay
  if (frameTextList.containsKey(frame)) {
    FrameText text = frameTextList.get(frame);
    textSize(text.font.size);
    fill(red(text.col), green(text.col), blue(text.col));
    text(text.text, text.font.x, text.font.y);
  }
  cam.endHUD();
  
  if(record == 1 && to_save)export.saveVideoFrame();
  to_save = false;

} 