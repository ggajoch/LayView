/**
 * RGB Cube.
 * 
 * The three primary colors of the additive color model are red, green, and blue.
 * This RGB color cube displays smooth transitions between these colors. 
 */
 
import peasy.*;

PeasyCam cam;
 
VectorSurface surface_1, surface_2;
FileParser parser;
 
void setup()  { 
  size(1024, 768, P3D); 
  noStroke(); 
  colorMode(RGB, 1); 
  
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(1);
  cam.setMaximumDistance(10000);
  
  //surface_1 = new Surface(0.05, 0.05, 0.05);
  surface_1 = new VectorSurface(0.05,0.05,0.05,0.2);
  parser = new FileParser();
  
  parser.parseFile("../TestFiles/x.omf");
  
  println();
  surface_2 = new VectorSurface((float)parser.segments.get(0).header.getDouble("xstepsize")*200000000.0,
  (float)parser.segments.get(0).header.getDouble("ystepsize")*200000000.0,
  (float)parser.segments.get(0).header.getDouble("zstepsize")*200000000.0,0.2);
  
  //print();
  ArrayList<PointVector> pp = parser.segments.get(0).data.points;
  for( PointVector p: pp ) {
    
    
    surface_2.addPoint((float)p.position.x*200000000.0, (float)p.position.y*200000000.0, (float)p.position.z*200000000.0,
    (float)p.vector.x/1000000.0, (float)p.vector.y/1000000.0, (float)p.vector.z/1000000.0, 1,1,0);
  }
  
  for(float x=-1.0; x<=1.0 ; x += 0.05*2){
      for(float y=-1.0; y<=1.0; y += 0.05*2){
        for(float z=-1.0; z<=0; z += 0.05*2){
          if(sqrt(pow((x),2)+pow((y),2)+pow((z),2))<=1.0){
            surface_1.addPoint(x,y,z,x,y,z,(x+1.0)/2.0,0,1.0-(x+1.0)/2.0);
          }
        }
      }
  }
} 
 
void draw()  { 
  background(0.5);
  
  scale(200);
 
  //draw surface
  //surface_1.drawBox();
  surface_1.drawVectorsVolume();
  surface_2.drawVectorsVolume();
  surface_2.drawBox();

  

} 