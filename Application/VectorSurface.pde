class VectorSurface extends Surface{
  float vector_scale;
  float vector_size;
  
  VectorSurface(){
    super();
    vector_scale = 1.0;
    vector_size = 0.025;
  }
  
  VectorSurface(float scale){
    super();
    vector_scale = scale;
    vector_size = 0.025;
  }
  
  VectorSurface(float scale, float size){
    super();
    vector_scale = scale;
    vector_size = size;
  }
  
  VectorSurface(float px, float py, float pz){
    super(px, py, pz);
    vector_scale = 1.0;
    vector_size = 0.025;
  }
  
  VectorSurface(float px, float py, float pz, float scale){
    super(px, py, pz);
    vector_scale = scale;
    vector_size = 0.025;
  }
  
  VectorSurface(float px, float py, float pz, float scale, float size){
    super(px, py, pz);
    vector_scale = scale;
    vector_size = size;
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
    float len = sqrt(pow((x2), 2) + pow((y2), 2) + pow((z2), 2)) * vector_scale;
    pushMatrix();
    translate(x1, y1, z1);
    
    pushMatrix();
    rotateZ(atan2(-x2, y2)); //  in plane angle
    rotateX(atan2(z2, sqrt(pow((x2), 2) + pow((y2), 2)))); //  out of XY plane angle
    this.drawCylinder(this.vector_size, 0, this.vector_size*1.5, len, 10); //  arrow
    this.drawCylinder(this.vector_size/2.0, this.vector_size/2.0, len, 0,10); //  line
    popMatrix();
    
    popMatrix();
  }
  
  void drawVectorsVolume(){
    for(PointVector point : points){
      fill(point.rgbcolor.x, point.rgbcolor.y, point.rgbcolor.z);
      this.drawVector((float)point.position.x, (float)point.position.y, (float)point.position.z, (float)point.vector.x, (float)point.vector.y, (float)point.vector.z);
        //println(point.x);
    }
  }
}