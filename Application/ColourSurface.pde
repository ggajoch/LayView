class ColourSurface extends VectorSurface{
  ArrayList<Gradient> gradientMakers;
  
  ColourSurface(){
    super();
    gradientMakers = new ArrayList<Gradient>();
  }
  
  ColourSurface(float scale){
    super(scale);
    gradientMakers = new ArrayList<Gradient>();
  }
  
  ColourSurface(float scale, float size){
    super(scale, size);
    gradientMakers = new ArrayList<Gradient>();
  }
  
  ColourSurface(float px, float py, float pz){
    super(px, py, pz);
    gradientMakers = new ArrayList<Gradient>();
  }
  
  ColourSurface(float px, float py, float pz, float scale){
    super(px, py, pz, scale);
    gradientMakers = new ArrayList<Gradient>();
  }
  
  ColourSurface(float px, float py, float pz, float scale, float size){
    super(px, py, pz, scale, size);
    gradientMakers = new ArrayList<Gradient>();
  }
  
  void colourPrepare(){
    float minlen=1000, maxlen=0;
    
    for(PointVector point : points){
      point.rgbcolor = new FVector(0,0,0);
      for(Gradient gradientMaker : gradientMakers){
        float len = gradientMaker.reference.multiplyNumber(gradientMaker.reference.multiplyScalar(point.vector)/gradientMaker.reference.multiplyScalar(gradientMaker.reference)).module();
        
        if(len>maxlen)maxlen = len;
        if(len<minlen)minlen = len;
        
        point.rgbcolor = new FVector(len/515534.38,0,1.0-len/515534.38);//1187449.4;515534.38)
      }
    }
    print("MIN: "+minlen+" MAX: "+maxlen);
  }
  
}