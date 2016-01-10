public class GradientPoint implements Comparable<GradientPoint> {
  public double val;
  public int colour;

  GradientPoint(double val_, int color_) {
    val = val_;
    colour = color_;
  }
  
  public FVector getRGB(){
    return new FVector(red(colour), green(colour), blue(colour));
  }

  public int compareTo(GradientPoint rhs) {
    if( this.val < rhs.val ) {
      return -1;
    } else if( this.val == rhs.val ) {
      return 0;
    } else {
      return 1;
    }
  }
};