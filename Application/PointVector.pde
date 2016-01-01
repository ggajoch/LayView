class PointVector {
  public DVector position;
  public DVector vector;
  public FVector rgbcolor;
  
  PointVector(){
    position = new DVector(0,0,0);
    vector = new DVector(0,0,0);
    rgbcolor = new FVector(0,0,0);
  }
  
  PointVector(PointVector full){
    position = new DVector(full.position.x,full.position.y,full.position.z);
    vector = new DVector(full.vector.x,full.vector.y,full.vector.z);
    rgbcolor = new FVector(full.rgbcolor.x,full.rgbcolor.y,full.rgbcolor.z);
  }
  
  PointVector(DVector position_, DVector vector_) {
    position = position_;
    vector = vector_;
    rgbcolor = new FVector(0,0,0);
  }
  
  PointVector(DVector position_, DVector vector_, FVector rgbcolor_) {
    position = position_;
    vector = vector_;
    rgbcolor = rgbcolor_;
  }
  
  PointVector add(PointVector second){
    return(new PointVector(this.position.add(second.position), this.vector.add(second.vector), this.rgbcolor.add(second.rgbcolor)));
  }
  
  PointVector multiplyNumber(double scalar){
    return(new PointVector(this.position.multiplyNumber(scalar), this.vector.multiplyNumber(scalar), this.rgbcolor.multiplyNumber((float)scalar)));
  }
};