class PointVector {
  DVector position;
  DVector vector;
  FVector rgbcolor;
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
};