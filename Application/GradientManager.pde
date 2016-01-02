import g4p_controls.GSketchPad;
import g4p_controls.GWindow;
import processing.core.PGraphics;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class GradientManager {
    private ArrayList<GradientModel> gradient_list;

    private volatile GradientModel actual_model;
    private volatile int actual_index;
    private PGraphics actual_color_graphics, gradient_preview_graphics;
    private GSketchPad actual_color_pad, gradient_preview_pad;
    public GradientPointEditor gradient_point_editor;

    volatile GradientView gradient_view;

    GWindow grad_window, pointAddWindow;

    GradientManager(GWindow grad_window, GSketchPad actual_color_pad, GSketchPad gradient_preview_pad, GWindow pointAddWindow) {
        this.grad_window = grad_window;
        this.pointAddWindow = pointAddWindow;
        this.actual_color_pad = actual_color_pad;
        this.gradient_preview_pad = gradient_preview_pad;
        this.actual_color_graphics = createGraphics(120, 40, JAVA2D);
        this.gradient_preview_graphics = createGraphics(40, 180, JAVA2D);

        grad_window.setVisible(false);
        pointAddWindow.setVisible(false);

        gradient_list = new ArrayList<GradientModel>();
        gradient_list.add(new GradientModel(this));
        gradient_list.add(new GradientModel(this));
        gradient_point_editor = new GradientPointEditor(GradientPointEditWindow, this, valGradEditValue);
    }

    void setActive(int index) {
        actual_model = gradient_list.get(index).clone();
        gradient_view = new GradientView(listGradList, actual_model);
    }

    void edit(int index) {
        actual_index = index;
        setActive(actual_index);
        println(actual_model.getReference());
        valGradX.setText(Double.toString(actual_model.getReference().x));
        valGradY.setText(Double.toString(actual_model.getReference().y));
        valGradZ.setText(Double.toString(actual_model.getReference().z));
        println(actual_model.getReference().x + " " + actual_model.getReference().y + " " + actual_model.getReference().z);
        grad_window.setVisible(true);
        this.gradient_view.reEnumerate();
    }

    void addPoint() {
        gradient_point_editor.open();
        colorUpdate();
    }

    private GradientPoint minValue() {
        return this.gradient_view.model.get(0);
    }

    private GradientPoint maxValue() {
        return this.gradient_view.model.get(this.gradient_view.model.size()-1);
    }

    void colorUpdate() {
        actual_color_graphics.beginDraw();

        if (this.gradient_view.model.isEmpty() )
            actual_color_graphics.background(grad_window.getGraphics().backgroundColor);
        else
            actual_color_graphics.background(this.gradient_view.model.get(this.gradient_view.dropList.getSelectedIndex()).colour);

        actual_color_graphics.endDraw();
        actual_color_pad.setGraphic(actual_color_graphics);



        gradient_preview_graphics.beginDraw();
        if (this.gradient_view.model.size() == 0) {
            gradient_preview_graphics.background(grad_window.getGraphics().backgroundColor);
        } else if (this.gradient_view.model.size() == 1) {
            gradient_preview_graphics.background(this.gradient_view.model.get(this.gradient_view.dropList.getSelectedIndex()).colour);
        } else {
            float minVal = (float) minValue().val;
            float maxVal = (float) maxValue().val;
            ArrayList<Pair<Float, Integer>> color_list = new ArrayList<Pair<Float, Integer>>();
            for (GradientPoint p : this.gradient_view.model.list) {
                float val = map((float) p.val, minVal, maxVal, 0.0, gradient_preview_graphics.height);
                color_list.add(new Pair<Float, Integer>(Float.valueOf(val), Integer.valueOf(p.colour)));
                println("color list: " + val + " " + p.colour);
            }

            int left = 0, right = 1;
            Pair<Float, Integer> cLeft = color_list.get(left),
                    cRight = color_list.get(right);
            println("left:" + cLeft.first + " right: " + cRight.first);
            for (int i = 0; i <= gradient_preview_graphics.height; i++) {

                if (i > cRight.first) {
                    right++;
                    left++;
                    cLeft = color_list.get(left);
                    cRight = color_list.get(right);
                }

                println("i = " + i);
                float inter = map(i, cLeft.first, cRight.first, 0, 1);
                println("inter: " + inter);
                color c = lerpColor(cLeft.second, cRight.second, inter);

                gradient_preview_graphics.stroke(c);
                gradient_preview_graphics.line(gradient_preview_graphics.width, i, 0, i);
            }
        }

        gradient_preview_graphics.endDraw();
        gradient_preview_pad.setGraphic(gradient_preview_graphics);



    }

    void OK_Handler() {
        Double x = Double.valueOf(valGradX.getText());
        Double y = Double.valueOf(valGradY.getText());
        Double z = Double.valueOf(valGradZ.getText());

        actual_model.setReference(new DVector(x, y, z));
        println(actual_model.getReference().x + " " + actual_model.getReference().y + " " + actual_model.getReference().z);
        gradient_list.set(actual_index, actual_model);
        grad_window.setVisible(false);
    }

    void Cancel_Handler() {
        grad_window.setVisible(false);
    }
}