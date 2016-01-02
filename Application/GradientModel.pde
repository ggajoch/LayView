import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradientModel extends ListModel<GradientPoint> implements Cloneable {
    GradientModel() {
        super(new ArrayList<GradientPoint>());
    }

    @Override
    public GradientModel clone() {
        GradientModel ret = new GradientModel();
        ret.list = new ArrayList<GradientPoint>(this.list);
        //Collections.copy(ret.list, this.list);
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
}