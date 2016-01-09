import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradientModel extends ListModel<GradientPoint> implements Cloneable {

    private volatile DVector reference;

    private volatile float MaxVector, MinVector;



    private volatile float MaxHint, MinHint;
    
    private GradientManager gradient_manager;
    GradientModel(GradientManager gradient_manager) {
        super(new ArrayList<GradientPoint>());
        this.gradient_manager = gradient_manager;
        reference = new DVector(0, 0, 0);
        MaxHint = 1;
        MinHint = 0;
        MaxVector = 1;
        MinVector = 0;
    }

    public DVector getReference() {
        return reference;
    }

    public void setReference(DVector reference) {
        this.reference = reference;
    }

    public float getMaxVector() {
        return MaxVector;
    }
    public float getMinVector() {
        return MinVector;
    }

    public void setMaxVector(float maxVector) {
        MaxVector = maxVector;
    }
    public void setMinVector(float minVector) {
        MinVector = minVector;
    }
    
    public float getMaxHint() {
        return MaxHint;
    }
    public float getMinHint() {
        return MinHint;
    }

    public void setMaxHint(float maxHint) {
        MaxHint = maxHint;
    }
    public void setMinHint(float minHint) {
        MinHint = minHint;
    }
    
    @Override
    public GradientModel clone() {
        GradientModel ret = new GradientModel(gradient_manager);
        ret.list = new ArrayList<GradientPoint>(this.list);
        ret.reference = new DVector(this.reference.x, this.reference.y, this.reference.z);
        ret.MaxVector = this.MaxVector;
        ret.MinVector = this.MinVector;
        ret.MaxHint = this.MaxHint;
        ret.MinHint = this.MinHint;
        return ret;
    }

    @Override
    String labelFor(GradientPoint element) {
        return Double.toString(element.val);
    }

    @Override
    void preEnumerate() {
        this.list.sort(new GradientPointComparator());
    }

    @Override
    void postEnumerate() {
        gradient_manager.colorUpdate();
    }
}