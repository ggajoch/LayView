package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene3DOptionsController {
    // ----------------------------- Public API  -----------------------------
    public void setup(Stage stage, SimpleObjectProperty<Scene3DOptions> scene3DOptions) {
        this.stage = stage;
        this.scene3DOptions = scene3DOptions;
        this.start = new Scene3DOptions(scene3DOptions.get());

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
                if( ! newValue.getReference().equals(oldValue.getReference()) ) {
                    recalculate();
                }
            });
            gradientEditor.exec(gradientToEdit1, 0, 0);
        });
        Gradient2.setOnAction(event -> {
            gradientToEdit2 = new SimpleObjectProperty<>(scene3DOptions.get().gradient2);
            gradientToEdit2.addListener((observable, oldValue, newValue) -> {
                if( ! newValue.getReference().equals(oldValue.getReference()) ) {
                    recalculate();
                }
            });
            gradientEditor.exec(gradientToEdit2, 0, 0);
        });
    }

    // -------------------------- Private variables  -------------------------


    private Stage stage;
    private GradientEditor gradientEditor;
    private Scene3DOptions start;
    private SimpleObjectProperty<Scene3DOptions> scene3DOptions;

    private SimpleObjectProperty<HintGradient> gradientToEdit1, gradientToEdit2;

    private RichTextField tipLenRich, tipRadiusRich, radiusRich, lenScaleRich, globalScaleRich, FPSRich;

    // --------------------------- Private methods  --------------------------

    private void recalculate() {
        scene3DOptions.set(new Scene3DOptions(tipLenRich.getDouble(), tipRadiusRich.getDouble(),
                radiusRich.getDouble(), lenScaleRich.getDouble(),
                globalScaleRich.getDouble(), FPSRich.getDouble(),
                gradientToEdit1.get(), gradientToEdit2.get()));
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
        this.stage.close();
    }

    @FXML
    private void cancel_click() {
//        scene3DOptions.set(start);
        this.stage.close();
    }
}