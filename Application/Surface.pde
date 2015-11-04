class Surface{
  float pitch_x, pitch_y, pitch_z;
  
  ArrayList<SimulationPoint> points;
  
  Surface(float px, float py, float pz){
    this.pitch_x = px/2.0;
    this.pitch_y = py/2.0;
    this.pitch_z = pz/2.0;
    points = new ArrayList<SimulationPoint>();
  }
  
  Surface(){
    this.pitch_x = 0.05;
    this.pitch_y = 0.05;
    this.pitch_z = 0.05;
    points = new ArrayList<SimulationPoint>();
  }
  
  void addPoint(float x, float y, float z, float vx, float vy, float vz, float r, float g, float b){
    points.add(new SimulationPoint(x, y, z, vx, vy, vz, r, g, b));
  }
  
  void drawBox(){
    beginShape(QUADS);
    
    for(SimulationPoint point : points){
      fill(point.r, point.g, point.b);
      if(abs(point.vx) > 0.0 || abs(point.vy) > 0.0 || abs(point.vz) > 0.0){
        vertex(point.x-pitch_x, point.y+pitch_y, point.z+pitch_z);
        vertex(point.x+pitch_x, point.y+pitch_y, point.z+pitch_z);
        vertex(point.x+pitch_x, point.y-pitch_y, point.z+pitch_z);
        vertex(point.x-pitch_x, point.y-pitch_y, point.z+pitch_z);
        
        vertex(point.x+pitch_x, point.y+pitch_y, point.z+pitch_z);
        vertex(point.x+pitch_x, point.y+pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y-pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y-pitch_y, point.z+pitch_z);
        
        vertex(point.x+pitch_x, point.y+pitch_y, point.z-pitch_z);
        vertex(point.x-pitch_x, point.y+pitch_y, point.z-pitch_z);
        vertex(point.x-pitch_x, point.y-pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y-pitch_y, point.z-pitch_z);
        
        vertex(point.x-pitch_x, point.y+pitch_y, point.z-pitch_z);
        vertex(point.x-pitch_x, point.y+pitch_y, point.z+pitch_z);
        vertex(point.x-pitch_x, point.y-pitch_y, point.z+pitch_z);
        vertex(point.x-pitch_x, point.y-pitch_y, point.z-pitch_z);
        
        vertex(point.x-pitch_x, point.y+pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y+pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y+pitch_y, point.z+pitch_z);
        vertex(point.x-pitch_x, point.y+pitch_y, point.z+pitch_z);
        
        vertex(point.x-pitch_x, point.y-pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y-pitch_y, point.z-pitch_z);
        vertex(point.x+pitch_x, point.y-pitch_y, point.z+pitch_z);
        vertex(point.x-pitch_x, point.y-pitch_y, point.z+pitch_z);
      }
    }
  
    endShape(); 
  }
}