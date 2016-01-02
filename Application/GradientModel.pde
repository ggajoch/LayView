import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradientModel extends ListModel<GradientPoint> implements Cloneable {

    private DVector reference;
    private GradientManager gradient_manager;
    GradientModel(GradientManager gradient_manager) {
        super(new ArrayList<GradientPoint>());
        this.gradient_manager = gradient_manager;
        reference = new DVector(0, 0, 0);
    }

    public DVector getReference() {
        return reference;
    }

    public void setReference(DVector reference) {
        this.reference = reference;
    }

    @Override
    public GradientModel clone() {
        GradientModel ret = new GradientModel(gradient_manager);
        ret.list = new ArrayList<GradientPoint>(this.list);
        ret.reference = new DVector(this.reference.x, this.reference.y, this.reference.z);
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