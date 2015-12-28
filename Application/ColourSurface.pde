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
    for(Gradient gradientMaker : gradientMakers){
      float minlen = (float)(gradientMaker.reference.multiplyScalar(points.get(0).vector)/gradientMaker.reference.multiplyScalar(gradientMaker.reference));
      float maxlen = minlen;
      
      for(PointVector point : points){
        point.rgbcolor = new FVector(0,0,0);
        
        float len = (float)(gradientMaker.reference.multiplyScalar(point.vector)/gradientMaker.reference.multiplyScalar(gradientMaker.reference));
        
        if(len>maxlen)maxlen = len;
        if(len<minlen)minlen = len;
      }
      
      for(PointVector point : points){
        point.rgbcolor = new FVector(0,0,0);
        float len = (float)(gradientMaker.reference.multiplyScalar(point.vector)/gradientMaker.reference.multiplyScalar(gradientMaker.reference));
        len = map(len, minlen, maxlen, 0.0, 1.0);
        int greaterIndex = 1;
        for(greaterIndex = 1 ; greaterIndex < gradientMaker.points.size()-1 ; greaterIndex++){
          if(len<(float)gradientMaker.points.get(greaterIndex).val)break;
        }
        
        GradientPoint greater, lower;
        greater = gradientMaker.points.get(greaterIndex);
        lower = gradientMaker.points.get(greaterIndex-1);
        
        point.rgbcolor = new FVector(map(len, (float)lower.val, (float)greater.val, lower.getRGB().x, greater.getRGB().x) ,0,1.0-len);
      }
      
      print("MIN: "+minlen+" MAX: "+maxlen);
    }
  }
  
}