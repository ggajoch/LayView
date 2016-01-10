class DVector {
  public double x, y, z;
  DVector() {
    x = y = z = 0;
  }
  DVector(double x_, double y_, double z_) {
    x = x_;
    y = y_;
    z = z_;
  }
  
  double multiplyScalar(DVector second){
    return(this.x*second.x + this.y*second.y + this.z*second.z);
  }
  
  DVector multiplyEach(DVector second){
    return(new DVector(this.x*second.x, this.y*second.y, this.z*second.z));
  }
  
  DVector add(DVector second){
    return(new DVector(this.x+second.x, this.y+second.y, this.z+second.z));
  }
  
  DVector multiplyNumber(double number){
    return(new DVector(this.x*number, this.y*number, this.z*number));
  }
  
  float module(){
    return(sqrt(pow((float)this.x, 2.0) + pow((float)this.y, 2.0) +pow((float)this.z, 2.0)));
  }
};