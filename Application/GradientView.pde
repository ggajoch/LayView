import g4p_controls.GDropList;

public class GradientView extends ListView<GradientPoint> {
    GradientView(GDropList dropList, GradientModel model) {
        super(dropList, model);
    }

    public boolean isEmpty() {
        return getList().isEmpty();
    }

    public int size() {
        return getList().size();
    }
}