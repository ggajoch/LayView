class Surface{
  public float pitch_x, pitch_y, pitch_z;
  public float pitchScale;
  
  public ArrayList<PointVector> points;
  
  public PointVector min, max;
  
  Surface(float px, float py, float pz){
    this.pitch_x = px/2.0;
    this.pitch_y = py/2.0;
    this.pitch_z = pz/2.0;
    points = new ArrayList<PointVector>();
    min = new PointVector();
    max = new PointVector();
    pitchScale = 1.0;
  }
  
  Surface(){
    this.pitch_x = 0.05;
    this.pitch_y = 0.05;
    this.pitch_z = 0.05;
    points = new ArrayList<PointVector>();
    min = new PointVector();
    max = new PointVector();
    pitchScale = 1.0;
  }
  
  void setPitchScale(float scale){
    this.pitchScale = scale;
  }
  
  void addPoint(DVector position, DVector vector, FVector rgbcolor){
    if(points.size()==0){
      max = new PointVector(position, vector, rgbcolor);
      min = new PointVector(position, vector, rgbcolor);
    }else{     
      if(position.x>max.position.x)max.position.x = position.x;
      if(position.y>max.position.y)max.position.y = position.y;
      if(position.z>max.position.z)max.position.z = position.z;
      
      if(vector.x>max.vector.x)max.vector.x = vector.x;
      if(vector.y>max.vector.y)max.vector.y = vector.y;
      if(vector.z>max.vector.z)max.vector.z = vector.z;
      
      if(rgbcolor.x>max.rgbcolor.x)max.rgbcolor.x = rgbcolor.x;
      if(rgbcolor.y>max.rgbcolor.y)max.rgbcolor.y = rgbcolor.y;
      if(rgbcolor.z>max.rgbcolor.z)max.rgbcolor.z = rgbcolor.z;
      
      if(position.x<min.position.x)min.position.x = position.x;
      if(position.y<min.position.y)min.position.y = position.y;
      if(position.z<min.position.z)min.position.z = position.z;
      
      if(vector.x<min.vector.x)min.vector.x = vector.x;
      if(vector.y<min.vector.y)min.vector.y = vector.y;
      if(vector.z<min.vector.z)min.vector.z = vector.z;
      
      if(rgbcolor.x<min.rgbcolor.x)min.rgbcolor.x = rgbcolor.x;
      if(rgbcolor.y<min.rgbcolor.y)min.rgbcolor.y = rgbcolor.y;
      if(rgbcolor.z<min.rgbcolor.z)min.rgbcolor.z = rgbcolor.z;
    }
    points.add(new PointVector(position, vector, rgbcolor));
  }
  
  void addPoint(PointVector point){
    if(this.points.size()==0){
      this.max = new PointVector(point);
      this.min = new PointVector(point);
    }else{     
      if(point.position.x>max.position.x)this.max.position.x = point.position.x;
      if(point.position.y>max.position.y)this.max.position.y = point.position.y;
      if(point.position.z>max.position.z)this.max.position.z = point.position.z;
      
      if(point.vector.x>max.vector.x)this.max.vector.x = point.vector.x;
      if(point.vector.y>max.vector.y)this.max.vector.y = point.vector.y;
      if(point.vector.z>max.vector.z)this.max.vector.z = point.vector.z;
      
      if(point.rgbcolor.x>max.rgbcolor.x)this.max.rgbcolor.x = point.rgbcolor.x;
      if(point.rgbcolor.y>max.rgbcolor.y)this.max.rgbcolor.y = point.rgbcolor.y;
      if(point.rgbcolor.z>max.rgbcolor.z)this.max.rgbcolor.z = point.rgbcolor.z;
      
      if(point.position.x<min.position.x)this.min.position.x = point.position.x;
      if(point.position.y<min.position.y)this.min.position.y = point.position.y;
      if(point.position.z<min.position.z)this.min.position.z = point.position.z;
      
      if(point.vector.x<min.vector.x)this.min.vector.x = point.vector.x;
      if(point.vector.y<min.vector.y)this.min.vector.y = point.vector.y;
      if(point.vector.z<min.vector.z)this.min.vector.z = point.vector.z;
      
      if(point.rgbcolor.x<min.rgbcolor.x)this.min.rgbcolor.x = point.rgbcolor.x;
      if(point.rgbcolor.y<min.rgbcolor.y)this.min.rgbcolor.y = point.rgbcolor.y;
      if(point.rgbcolor.z<min.rgbcolor.z)this.min.rgbcolor.z = point.rgbcolor.z;
    }
    points.add(point);
  }
  
  void translatePoints(double x, double y, double z){
    for(PointVector point : points){
      point.position.x += x;
      point.position.y += y;
      point.position.z += z;
    }
  }
  
  void translatePoints(DVector vector){
    for(PointVector point : points){
      point.position.x += vector.x;
      point.position.y += vector.y;
      point.position.z += vector.z;
    }
  }
  
  void translatePoints(PointVector vector){
    for(PointVector point : points){
      point.position.x += vector.position.x;
      point.position.y += vector.position.y;
      point.position.z += vector.position.z;
    }
  }
  
  void drawBox(){
    beginShape(QUADS);
    for(PointVector point : points){
      fill(point.rgbcolor.x, point.rgbcolor.y, point.rgbcolor.z);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y+pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z-pitch_z)*this.pitchScale);
      vertex(((float)point.position.x+pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
      vertex(((float)point.position.x-pitch_x)*this.pitchScale, ((float)point.position.y-pitch_y)*this.pitchScale, ((float)point.position.z+pitch_z)*this.pitchScale);
    }
    endShape(); 
  }
}