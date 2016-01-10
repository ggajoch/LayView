public class Gradient {
  public List<GradientPoint> points;
  public DVector reference;
  
  volatile public float min;
  volatile public float max;
  
  Gradient(){
    this.points = new ArrayList<GradientPoint>();
    this.reference = new DVector(1,0,0);
  }
  
  Gradient(ArrayList<GradientPoint> points_){
    this.points = points_;
    Collections.sort(this.points);
    this.reference = new DVector(1,0,0);
  }
  
  Gradient(ArrayList<GradientPoint> points_, DVector reference_){
    this.points = points_;
    Collections.sort(this.points);
    this.reference = reference_;
  }
}

