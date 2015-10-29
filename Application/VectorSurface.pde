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
    float len=sqrt(pow((x1-x2),2)+pow((y1-y2),2)+pow((z1-z2),2))*vector_scale;
    pushMatrix();
    translate(x1,y1,z1);
    this.drawCylinder(this.vector_size, 0, this.vector_size*1.5, len, 10);
    this.drawCylinder(this.vector_size/2.0, this.vector_size/2.0, len, 0,10);
    popMatrix();
  }
  
  void drawVectorsVolume(){
    for(SimulationPoint point : points){
      fill(point.r, point.g, point.b);
      this.drawVector(point.x, point.y, point.z, point.vx, point.vy, point.vz);
    }
  }
}