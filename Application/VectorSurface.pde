class VectorSurface extends Surface{
  public float vector_scale;
  public float vectorWidth;
  public float tipRadius;
  public float tip;
  
  VectorSurface(){
    super();
    vector_scale = 1.0;
    vectorWidth = 0.025;
  }
  
  VectorSurface(float scale){
    super();
    vector_scale = scale;
    vectorWidth = 0.0125;
    tipRadius = 0.025;
    tip = 0.0375;
  }
  
  VectorSurface(float scale, float size){
    super();
    vector_scale = scale;
    vectorWidth = size;
    tipRadius = 0.025;
    tip = 0.0375;
  }
  
  VectorSurface(float px, float py, float pz){
    super(px, py, pz);
    vector_scale = 1.0;
    vectorWidth = 0.0125;
    tipRadius = 0.025;
    tip = 0.0375;
  }
  
  VectorSurface(float px, float py, float pz, float scale){
    super(px, py, pz);
    vector_scale = scale;
    vectorWidth = 0.0125;
    tipRadius = 0.025;
    tip = 0.0375;
  }
  
  VectorSurface(float px, float py, float pz, float scale, float size){
    super(px, py, pz);
    vector_scale = scale;
    vectorWidth = size;
    tipRadius = 0.025;
    tip = 0.0375;
  }
  
  void setVectorScale(float scale){
    vector_scale = scale;
  }
  
  void setVectorWidth(float scale){
    vectorWidth = scale;
  }
  
  void setTip(float scale){
    tip = scale;
  }
  
  void setTipRadius(float scale){
    tipRadius = scale;
  }
  
  void drawCylinder(float topRadius, float bottomRadius, float tall, float offset, int sides) {
    float angle = 0;
    float angleIncrement = TWO_PI / sides;
    beginShape(QUAD_STRIP);
    for (int i = 0; i < sides + 1; ++i) {
      vertex(topRadius*cos(angle), offset, topRadius*sin(angle));
      vertex(bottomRadius*cos(angle), tall+offset, bottomRadius*sin(angle));
      angle += angleIncrement;
    }
    endShape();
    
    // If it is not a cone, draw the circular top cap
    if (topRadius != 0) {
      angle = 0;
      beginShape(TRIANGLE_FAN);
      
      // Center point
      vertex(0, offset, 0);
      for (int i = 0; i < sides + 1; i++) {
        vertex(topRadius * cos(angle), offset, topRadius * sin(angle));
        angle += angleIncrement;
      }
      endShape();
    }
  
    // If it is not a cone, draw the circular bottom cap
    if (bottomRadius != 0) {
      angle = 0;
      beginShape(TRIANGLE_FAN);
  
      // Center point
      vertex(0, tall+offset, 0);
      for (int i = 0; i < sides + 1; i++) {
        vertex(bottomRadius * cos(angle), tall+offset, bottomRadius * sin(angle));
        angle += angleIncrement;
      }
      endShape();
    }
  }
  
  void drawVector(float x1, float y1, float z1, float x2, float y2, float z2){
    float len = sqrt(pow((x2), 2) + pow((y2), 2) + pow((z2), 2)) * this.vector_scale;
    pushMatrix();
    translate(x1, y1, z1);
    
    pushMatrix();
    rotateZ(atan2(-x2, y2)); //  in plane angle
    rotateX(atan2(z2, sqrt(pow((x2), 2) + pow((y2), 2)))); //  out of XY plane angle
    this.drawCylinder(this.tipRadius,                          0, this.tip, len, 10); //  arrow
    this.drawCylinder(this.vectorWidth,  this.vectorWidth, len,                     0,  10); //  line
    popMatrix();
    
    popMatrix();
  }
  
  void drawVectorsVolume(){
    for(PointVector point : points){
      fill(point.rgbcolor.x, point.rgbcolor.y, point.rgbcolor.z);
      this.drawVector((float)point.position.x*this.pitchScale, (float)point.position.y*this.pitchScale, (float)point.position.z*this.pitchScale, (float)point.vector.x, (float)point.vector.y, (float)point.vector.z);
    }
  }
}