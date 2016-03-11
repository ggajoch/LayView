package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.gajoch.layview.utils.GUIUtils;

import javax.swing.*;

public class Scene3DOptionsController {
    // ----------------------------- Public API  -----------------------------
    public void setup(JFrame frame, SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        this.frame = frame;
        this.scene3DOptions = scene3DOptions;

        gradientEditor = new GradientEditor();

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

//        tipLen.textProperty().addListener(observable -> recalculate());
//        tipRadius.textProperty().addListener(observable -> recalculate());
//        radius.textProperty().addListener(observable -> recalculate());
//        lenScale.textProperty().addListener(observable -> recalculate());
//        globalScale.textProperty().addListener(observable -> recalculate());
//        FPS.textProperty().addListener(observable -> recalculate());

        gradientToEdit1 = new SimpleObjectProperty<>(scene3DOptions.get().gradient1);
        gradientToEdit2 = new SimpleObjectProperty<>(scene3DOptions.get().gradient2);

        Gradient1.setOnAction(event -> {
            gradientToEdit1 = new SimpleObjectProperty<>(scene3DOptions.get().gradient1);
            gradientToEdit1.addListener((observable, oldValue, newValue) -> {
                System.out.println("AAA");
                recalculate();
            });
            gradientEditor.exec(gradientToEdit1);
        });
        Gradient2.setOnAction(event -> {
            gradientToEdit2 = new SimpleObjectProperty<>(scene3DOptions.get().gradient2);
            gradientToEdit2.addListener((observable, oldValue, newValue) -> {
                recalculate();
            });
            gradientEditor.exec(gradientToEdit2);
        });
    }

    // -------------------------- Private variables  -------------------------


    private JFrame frame;
    private GradientEditor gradientEditor;
    private SimpleObjectProperty<Scene3DOptions> scene3DOptions;

    private SimpleObjectProperty<HintGradient> gradientToEdit1, gradientToEdit2;

    private RichTextField tipLenRich, tipRadiusRich, radiusRich, lenScaleRich, globalScaleRich, FPSRich;

    // --------------------------- Private methods  --------------------------

    private void recalculate() {
        Scene3DOptions newScene = new Scene3DOptions(tipLenRich.getDouble(), tipRadiusRich.getDouble(),
                radiusRich.getDouble(), lenScaleRich.getDouble(), scene3DOptions.get().boxProperties.dimensions,
                globalScaleRich.getDouble(), FPSRich.getDouble(),
                gradientToEdit1.get(), gradientToEdit2.get());
        scene3DOptions.set(newScene);
    }

    // ------------------------------- Objects  ------------------------------

    @FXML
    private Button okButton, cancelButton;
    @FXML
    private Button Gradient1, Gradient2;
    @FXML
    private TextField tipLen, tipRadius, radius, lenScale, globalScale, FPS;

    // --------------------------- button handlers ---------------------------

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