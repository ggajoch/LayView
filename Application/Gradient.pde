import java.util.List;
class Gradient{
  public List<GradientPoint> points;
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

import java.util.Comparator;

public class GradientPointComparator implements Comparator<GradientPoint> {
  @Override
  public int compare(GradientPoint o1, GradientPoint o2) {
    if( o1.val < o2.val ) {
      return -1;
    } else if( o1.val == o2.val ) {
      return 0;
    } else {
      return 1;
    }
  }
}