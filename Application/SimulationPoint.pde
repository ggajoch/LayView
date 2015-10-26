class SimulationPoint{
  float x, y, z;
  float vx, vy, vz;
  float r, g, b;
  
  SimulationPoint(){
    this.x = 0;
    this.y = 0;
    this.z = 0;
    this.vx = 0;
    this.vy = 0;
    this.vz = 0;
    this.r = 0;
    this.g = 0;
    this.b = 0;
  }
  
  SimulationPoint(float xx, float yy, float zz){
    this.x = xx;
    this.y = yy;
    this.z = zz;
    this.vx = 0;
    this.vy = 0;
    this.vz = 0;
    this.r = 0;
    this.g = 0;
    this.b = 0;
  }
  
  SimulationPoint(float xx, float yy, float zz, float vxx, float vyy, float vzz){
    this.x = xx;
    this.y = yy;
    this.z = zz;
    this.vx = vxx;
    this.vy = vyy;
    this.vz = vzz;
    this.r = 0;
    this.g = 0;
    this.b = 0;
  }
  
  SimulationPoint(float xx, float yy, float zz, float vxx, float vyy, float vzz, float rr, float gg, float bb){
    this.x = xx;
    this.y = yy;
    this.z = zz;
    this.vx = vxx;
    this.vy = vyy;
    this.vz = vzz;
    this.r = rr;
    this.g = gg;
    this.b = bb;
  }
  
  void SetPos(float xx, float yy, float zz){
    this.x = xx;
    this.y = yy;
    this.z = zz;
  }
  
  void SetVal(float vxx, float vyy, float vzz){
    this.vx = vxx;
    this.vy = vyy;
    this.vz = vzz;
  }
  
  void SetColor(float rr, float gg, float bb){
    this.r = rr;
    this.g = gg;
    this.b = bb;
  }
  
  
}