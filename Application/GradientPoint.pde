class GradientPoint {
  public String name;
  public double val;
  public int colour;
  GradientPoint(String name_, double val_, int color_) {
    name = name_;
    val = val_;
    colour = color_;
  }
  
  FVector getRGB(){
    return new FVector(red(colour), green(colour), blue(colour));
  }
  
};

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