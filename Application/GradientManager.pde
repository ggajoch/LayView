import g4p_controls.GWindow;

import java.util.ArrayList;

public class GradientManager {
    private ArrayList<GradientModel> gradient_list;

    private volatile GradientModel actual_model;
    private volatile int actual_index;
    volatile GradientView gradient_view;


    GWindow gradWindow, pointAddWindow;
    GradientManager(GWindow gradWindow, GWindow pointAddWindow) {
        this.gradWindow = gradWindow;
        this.pointAddWindow = pointAddWindow;

        gradWindow.setVisible(false);
        pointAddWindow.setVisible(false);

        gradient_list = new ArrayList<GradientModel>();
        gradient_list.add(new GradientModel());
        gradient_list.add(new GradientModel());
    }

    void setActive(int index) {
        actual_model = gradient_list.get(index).clone();
        gradient_view = new GradientView(listGradList, actual_model);
    }

    void edit(int index) {
        actual_index = index;
        setActive(actual_index);
        gradWindow.setVisible(true);
    }

    void OK_Handler() {
        gradient_list.set(actual_index, actual_model);
        gradWindow.setVisible(false);
    }

    void Cancel_Handler() {
        gradWindow.setVisible(false);
    }
}