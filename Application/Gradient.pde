import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gradient {
  private final List<GradientPoint> points;
  private final DVector reference;

  private float min, max;

  Gradient() {
    this.points = new ArrayList<GradientPoint>();
    this.reference = new DVector();
  }
  
  Gradient(List<GradientPoint> points_) {
    this.points = points_;
    Collections.sort(this.points);
    this.reference = new DVector();
  }
  
  Gradient(List<GradientPoint> points_, DVector reference_) {
    this.points = points_;
    Collections.sort(this.points);
    this.reference = reference_;
  }
}

