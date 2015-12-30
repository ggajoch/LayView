class Gradient{
  public ArrayList<GradientPoint> points;
  public DVector reference;
  
  public float min;
  public float max;
  
  Gradient(){
    this.points = new ArrayList<GradientPoint>();
    this.reference = new DVector(1,0,0);
  }
  
  Gradient(ArrayList<GradientPoint> points_){
    this.points = points_;
    Collections.sort(this.points, new GradientPointComparator());
    this.reference = new DVector(1,0,0);
  }
  
  Gradient(ArrayList<GradientPoint> points_, DVector reference_){
    this.points = points_;
    Collections.sort(this.points, new GradientPointComparator());
    this.reference = reference_;
  }
  
  
  
}