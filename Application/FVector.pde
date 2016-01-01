class FVector {
  public float x, y, z;
  FVector(float x_, float y_, float z_) {
    x = x_;
    y = y_;
    z = z_;
  }
  
  float multiplyScalar(FVector second){
    return(this.x*second.x + this.y*second.y + this.z*second.z);
  }
  
  FVector multiplyEach(FVector second){
    return(new FVector(this.x*second.x, this.y*second.y, this.z*second.z));
  }
  
  FVector add(FVector second){
    return(new FVector(this.x+second.x, this.y+second.y, this.z+second.z));
  }
  
  FVector multiplyNumber(float number){
    return(new FVector(this.x*number, this.y*number, this.z*number));
  }
  
  float module(){
    return(sqrt(pow((float)this.x, 2.0) + pow((float)this.y, 2.0) +pow((float)this.z, 2.0)));
  }
};