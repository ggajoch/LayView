package pl.gajoch.layview.gui.GUI_3D.options;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import pl.gajoch.layview.graphics3d.options.HintGradient;
import pl.gajoch.layview.graphics3d.options.Scene3DOptions;
import pl.gajoch.layview.gui.GUI_3D.gradient.GradientEditor;
import pl.gajoch.layview.utils.GUIUtils;
import pl.gajoch.layview.utils.gui.RichTextField;

import javax.swing.*;

public class Scene3DOptionsController {
    private JFrame frame;
    private GradientEditor gradientEditor;
    private SimpleObjectProperty<Scene3DOptions> scene3DOptions;
    private SimpleObjectProperty<HintGradient> gradientToEdit1, gradientToEdit2;
    private RichTextField tipLenRich, tipRadiusRich, radiusRich, lenScaleRich, globalScaleRich, FPSRich;

    @FXML
    private Button okButton, cancelButton;
    @FXML
    private Button Gradient1, Gradient2;
    @FXML
    private TextField tipLen, tipRadius, radius, lenScale, globalScale, FPS;
    @FXML
    private CheckBox Grad2Enable, isVectorsCheckBox;

    private enum GradientsAvailable {
        NONE, GRADIENT1, GRADIENT2
    }

    private GradientsAvailable currentGradientEdititng;

    public void setup(JFrame frame, SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        this.frame = frame;
        this.scene3DOptions = scene3DOptions;

        gradientEditor = new GradientEditor();
        currentGradientEdititng = GradientsAvailable.NONE;

        tipLenRich = RichTextField.of(tipLen);
        tipRadiusRich = RichTextField.of(tipRadius);
        radiusRich = RichTextField.of(radius);
        lenScaleRich = RichTextField.of(lenScale);
        globalScaleRich = RichTextField.of(globalScale);
        FPSRich = RichTextField.of(FPS);

        tipLenRich.set(scene3DOptions.get().vectorProperties.tipLen);
        tipRadiusRich.set(scene3DOptions.get().vectorProperties.tipRadius);
        radiusRich.set(scene3DOptions.get().vectorProperties.radius);
        lenScaleRich.set(scene3DOptions.get().vectorProperties.lenScale);
        globalScaleRich.set(scene3DOptions.get().globalScale);
        FPSRich.set(scene3DOptions.get().FPS);

        gradientToEdit1 = new SimpleObjectProperty<>(scene3DOptions.get().gradient1);
        gradientToEdit2 = new SimpleObjectProperty<>(scene3DOptions.get().gradient2);

        Gradient1.setOnAction(event -> {
            currentGradientEdititng = GradientsAvailable.GRADIENT1;
            gradientToEdit1 = new SimpleObjectProperty<>(scene3DOptions.get().gradient1);
            gradientToEdit1.addListener((observable, oldValue, newValue) -> {
                recalculate();
            });
            gradientEditor.exec(gradientToEdit1);
        });
        Gradient2.setOnAction(event -> {
            currentGradientEdititng = GradientsAvailable.GRADIENT2;
            gradientToEdit2 = new SimpleObjectProperty<>(scene3DOptions.get().gradient2);
            gradientToEdit2.addListener((observable, oldValue, newValue) -> {
                recalculate();
            });
            gradientEditor.exec(gradientToEdit2);
        });

        Grad2Enable.setSelected(scene3DOptions.get().gradient2enable);
        isVectorsCheckBox.setSelected(scene3DOptions.get().isVectors);
    }

    public void setHints(double minHint1, double maxHint1,
                         double minHint2, double maxHint2) {
        if( currentGradientEdititng == GradientsAvailable.GRADIENT1 ) {
            gradientEditor.setHints(minHint1, maxHint1);
        } else {
            gradientEditor.setHints(minHint2, maxHint2);
        }
    }

    private void recalculate() {
        Scene3DOptions newScene = new Scene3DOptions(tipLenRich.getDouble(), tipRadiusRich.getDouble(),
                radiusRich.getDouble(), lenScaleRich.getDouble(), scene3DOptions.get().boxProperties.dimensions,
                globalScaleRich.getDouble(), FPSRich.getDouble(),
                gradientToEdit1.get(), gradientToEdit2.get(),
                Grad2Enable.isSelected(), isVectorsCheckBox.isSelected());
        scene3DOptions.set(newScene);
    }

    @FXML
    private void ok_click() {
        recalculate();
        GUIUtils.closeJFrame(frame);
    }

    @FXML
    private void cancel_click() {
        GUIUtils.closeJFrame(frame);
    }
}