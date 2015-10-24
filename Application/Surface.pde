class Surface{
  float pitch_x, pitch_y, pitch_z;
  
  Surface(float px, float py, float pz){
    pitch_x = px/2.0;
    pitch_y = py/2.0;
    pitch_z = pz/2.0;
  }
  
  Surface(){
    pitch_x = 0.05;
    pitch_y = 0.05;
    pitch_z = 0.05;
  }
  
  void draw(){
    beginShape(QUADS);
    
    for(float x=-1.0; x<=1.0 ; x += pitch_x*2){
      for(float y=-1.0; y<=x; y += pitch_y*2){
        float z=((x+y)+2.0)/4.0;
        float v = abs((((float)(millis()%5000))/2500.0)-1.0);
        fill(z,v,1.0-z);
        vertex(x-pitch_x, y+pitch_y, pitch_z);
        vertex(x+pitch_x, y+pitch_y, pitch_z);
        vertex(x+pitch_x, y-pitch_y, pitch_z);
        vertex(x-pitch_x, y-pitch_y, pitch_z);
        
        vertex(x+pitch_x, y+pitch_y, pitch_z);
        vertex(x+pitch_x, y+pitch_y, -pitch_z);
        vertex(x+pitch_x, y-pitch_y, -pitch_z);
        vertex(x+pitch_x, y-pitch_y, pitch_z);
        
        vertex(x+pitch_x, y+pitch_y, -pitch_z);
        vertex(x-pitch_x, y+pitch_y, -pitch_z);
        vertex(x-pitch_x, y-pitch_y, -pitch_z);
        vertex(x+pitch_x, y-pitch_y, -pitch_z);
        
        vertex(x-pitch_x, y+pitch_y, -pitch_z);
        vertex(x-pitch_x, y+pitch_y, pitch_z);
        vertex(x-pitch_x, y-pitch_y, pitch_z);
        vertex(x-pitch_x, y-pitch_y, -pitch_z);
        
        vertex(x-pitch_x, y+pitch_y, -pitch_z);
        vertex(x+pitch_x, y+pitch_y, -pitch_z);
        vertex(x+pitch_x, y+pitch_y, pitch_z);
        vertex(x-pitch_x, y+pitch_y, pitch_z);
        
        vertex(x-pitch_x, y-pitch_y, -pitch_z);
        vertex(x+pitch_x, y-pitch_y, -pitch_z);
        vertex(x+pitch_x, y-pitch_y, pitch_z);
        vertex(x-pitch_x, y-pitch_y, pitch_z);
      }
    }
  
    endShape(); 
  }
}