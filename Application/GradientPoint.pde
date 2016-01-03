import java.util.Comparator;

class GradientPoint {
  public double val;
  public int colour;
  GradientPoint(double val_, int color_) {
    val = val_;
    colour = color_;
  }
  
  FVector getRGB(){
    return new FVector(red(colour), green(colour), blue(colour));
  }
  
};

