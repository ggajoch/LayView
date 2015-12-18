class Surface{
  float pitch_x, pitch_y, pitch_z;
  
  ArrayList<PointVector> points;
  
  Surface(float px, float py, float pz){
    this.pitch_x = px/2.0;
    this.pitch_y = py/2.0;
    this.pitch_z = pz/2.0;
    points = new ArrayList<PointVector>();
  }
  
  Surface(){
    this.pitch_x = 0.05;
    this.pitch_y = 0.05;
    this.pitch_z = 0.05;
    points = new ArrayList<PointVector>();
  }
  
  void addPoint(DVector position, DVector vector, FVector rgbcolor){
    points.add(new PointVector(position, vector, rgbcolor));
  }
  
  void addPoint(PointVector point){
    points.add(point);
  }
  
  void drawBox(){
    beginShape(QUADS);
    
    for(PointVector point : points){
      fill(point.rgbcolor.x, point.rgbcolor.y, point.rgbcolor.z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y+pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y+pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y-pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y-pitch_y, (float)point.position.z+pitch_z);
      
      vertex((float)point.position.x+pitch_x, (float)point.position.y+pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y+pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y-pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y-pitch_y, (float)point.position.z+pitch_z);
      
      vertex((float)point.position.x+pitch_x, (float)point.position.y+pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y+pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y-pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y-pitch_y, (float)point.position.z-pitch_z);
      
      vertex((float)point.position.x-pitch_x, (float)point.position.y+pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y+pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y-pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y-pitch_y, (float)point.position.z-pitch_z);
      
      vertex((float)point.position.x-pitch_x, (float)point.position.y+pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y+pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y+pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y+pitch_y, (float)point.position.z+pitch_z);
      
      vertex((float)point.position.x-pitch_x, (float)point.position.y-pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y-pitch_y, (float)point.position.z-pitch_z);
      vertex((float)point.position.x+pitch_x, (float)point.position.y-pitch_y, (float)point.position.z+pitch_z);
      vertex((float)point.position.x-pitch_x, (float)point.position.y-pitch_y, (float)point.position.z+pitch_z);
    }
  
    endShape(); 
  }
}