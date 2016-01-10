import g4p_controls.G4P;
import g4p_controls.GTextField;
import g4p_controls.GWindow;

public class GradientPointEditor {
    private final GradientManager gradient_manager;
    private final GTextField value_field;
    private final GWindow window;
    private int selected_color;
    private boolean edit_mode;

    GradientPointEditor(GWindow window, GradientManager gradient_manager, GTextField value_field) {
        window.setVisible(false);
        this.window = window;
        this.gradient_manager = gradient_manager;
        this.value_field = value_field;
        this.selected_color = 0;
        this.edit_mode = false;
    }

    public void open(GradientPoint now) {
        if( now != null ) {
            edit_mode = true;
            value_field.setText(Double.toString(now.val));
            this.selected_color = now.colour;
        } else {
            value_field.setText("value");
        }
        window.setVisible(true);
    }

    public void Color_handler() {
        selected_color = G4P.selectColor();
        gradient_manager.colorUpdate();
    }

    public void OK_handler() {
        if( edit_mode ) {
            gradient_manager.gradient_view.remove();
            edit_mode = false;
        }
        Double val = Double.valueOf(value_field.getText());
        gradient_manager.gradient_view.add(new GradientPoint(val, selected_color));
        window.setVisible(false);
        gradient_manager.colorUpdate();
    }

    public void Cancel_handler() {
        window.setVisible(false);
    }
}