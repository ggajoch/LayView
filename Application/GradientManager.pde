import g4p_controls.*;

public class GradientManager {
    private ArrayList<GradientModel> gradient_list;

    private volatile GradientModel actual_model;
    private volatile int actual_index;
    private PGraphics actual_color_graphics, gradient_preview_graphics;
    private GSketchPad actual_color_pad, gradient_preview_pad;
    private GradientPointEditor gradient_point_editor;

    private volatile GradientView gradient_view;

    private GWindow grad_window, pointAddWindow;



    GradientManager(GWindow grad_window, GSketchPad actual_color_pad, GSketchPad gradient_preview_pad, GWindow pointAddWindow) {
        this.grad_window = grad_window;
        this.pointAddWindow = pointAddWindow;
        this.actual_color_pad = actual_color_pad;
        this.gradient_preview_pad = gradient_preview_pad;
        this.actual_color_graphics = createGraphics(Math.round(actual_color_pad.getWidth()), Math.round(actual_color_pad.getHeight()), JAVA2D);
        this.gradient_preview_graphics = createGraphics(Math.round(gradient_preview_pad.getWidth()), Math.round(gradient_preview_pad.getHeight()), JAVA2D);

        grad_window.setVisible(false);
        pointAddWindow.setVisible(false);

        gradient_list = new ArrayList<GradientModel>();
        gradient_list.add(new GradientModel(this));
        gradient_list.add(new GradientModel(this));
        gradient_point_editor = new GradientPointEditor(GradientPointEditWindow, this, valGradEditValue);

        setActive(0);
        gradient_view.add(new GradientPoint(-1.0, -16776961)); //blue
        gradient_view.add(new GradientPoint(1.0, -65536)); //red
        actual_model.setReference(new DVector(1, 0, 0));
        gradient_list.set(actual_index, actual_model);
        this.gradient_view.reEnumerate();

        setActive(1);
        gradient_view.add(new GradientPoint(-1.0, -1)); //white
        gradient_view.add(new GradientPoint(1.0, -1)); //white
        actual_model.setReference(new DVector(0, 0, 1));
        gradient_list.set(actual_index, actual_model);
        this.gradient_view.reEnumerate();
    }
    
    public GradientModel getGradientModel(int gradientIndex) {
        return this.gradient_list.get(gradientIndex);
    }
    
    public int getActualIndex(){
      return actual_index;
    }

    private void setActive(int index) {
        actual_index = index;
        actual_model = gradient_list.get(actual_index).clone();
        gradient_view = new GradientView(listGradList, actual_model);
    }

    private void setText(GTextBase field, double value) {
        field.setText(Double.toString(value));
    }
    private void setText(GTextBase field, float value) {
        field.setText(Float.toString(value));
    }

    public void edit(int index) {
        setActive(index);
        updateDisplays();
        this.gradient_view.reEnumerate();
    }

    private void updateDisplays() {
        setText(valGradX, actual_model.getReference().x);
        setText(valGradY, actual_model.getReference().y);
        setText(valGradZ, actual_model.getReference().z);

        setText(btnGradMax, actual_model.getMaxHint());
        setText(btnGradMin, actual_model.getMinHint());
        setText(valGradMax, actual_model.getMaxVector());
        setText(valGradMin, actual_model.getMinVector());

        grad_window.setVisible(true);
    }

    void setMaxHint(float value) {
        actual_model.setMaxHint(value);
        updateDisplays();
    }
    
    void setMinHint(float value) {
        actual_model.setMinHint(value);
        updateDisplays();
    }

    void maxHintCopy() {
        setText(valGradMax, actual_model.getMaxHint());
    }
    
    void minHintCopy() {
        setText(valGradMin, actual_model.getMinHint());
    }

    void editPoint() {
        this.gradient_point_editor.edit(gradient_view.getActual());
    }

    void addPoint() {
        gradient_point_editor.open();
        colorUpdate();
    }

    private GradientPoint minValue() {
        return actual_model.get(0);
    }

    private GradientPoint maxValue() {
        return actual_model.get(actual_model.size()-1);
    }

    void colorUpdate() {
        actual_color_graphics.beginDraw();

        if (gradient_view.isEmpty() )
            actual_color_graphics.background(grad_window.getGraphics().backgroundColor);
        else
            actual_color_graphics.background(gradient_view.getActual().colour);

        actual_color_graphics.endDraw();
        actual_color_pad.setGraphic(actual_color_graphics);

        // -------------------------------------------------------

        gradient_preview_graphics.beginDraw();
        if (gradient_view.size() == 0) {
            gradient_preview_graphics.background(grad_window.getGraphics().backgroundColor);
        } else if (gradient_view.size() == 1) {
            gradient_preview_graphics.background(gradient_view.getActual().colour);
        } else {
            float minVal = (float) minValue().val;
            float maxVal = (float) maxValue().val;
            ArrayList<Pair<Float, Integer>> color_list = new ArrayList<Pair<Float, Integer>>();
            for (GradientPoint p : gradient_view.getList()) {
                float val = map((float) p.val, minVal, maxVal, 0.0, gradient_preview_graphics.height);
                color_list.add(new Pair<Float, Integer>(val, p.colour));
            }

            int left = 0, right = 1;
            Pair<Float, Integer> cLeft = color_list.get(left),
                    cRight = color_list.get(right);
            for (int i = 0; i <= gradient_preview_graphics.height; i++) {

                if (i > cRight.first) {
                    right++;
                    left++;
                    cLeft = color_list.get(left);
                    cRight = color_list.get(right);
                }

                float inter = map(i, cLeft.first, cRight.first, 0, 1);
                color c = lerpColor(cLeft.second, cRight.second, inter);

                gradient_preview_graphics.stroke(c);
                gradient_preview_graphics.line(gradient_preview_graphics.width, i, 0, i);
            }
        }

        gradient_preview_graphics.endDraw();
        gradient_preview_pad.setGraphic(gradient_preview_graphics);
    }

    void Apply_Handler() {
        Double x = Double.valueOf(valGradX.getText());
        Double y = Double.valueOf(valGradY.getText());
        Double z = Double.valueOf(valGradZ.getText());

        actual_model.setReference(new DVector(x, y, z));

        float maxi = Float.valueOf(valGradMax.getText());
        actual_model.setMaxVector(maxi);
        
        float mini = Float.valueOf(valGradMin.getText());
        actual_model.setMinVector(mini);
        

        print("now: "  + actual_model.getMaxVector());
        gradient_list.set(actual_index, actual_model);
        print("now: "  + gradient_list.get(actual_index).getMaxVector());
    }
    
    void OK_Handler() {
        Apply_Handler();
        grad_window.setVisible(false);
    }

    void Cancel_Handler() {
        grad_window.setVisible(false);
    }

    List<GradientPoint> getList(int gradientIndex) {
        return this.gradient_list.get(gradientIndex).list;
    }

    DVector getReference(int gradientIndex) {
        return this.gradient_list.get(gradientIndex).getReference();
    }
}