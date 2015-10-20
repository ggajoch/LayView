class Surface{
  
  Surface(){
    
  }
  
  void draw(){
    beginShape(QUADS);
    
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
  }
}