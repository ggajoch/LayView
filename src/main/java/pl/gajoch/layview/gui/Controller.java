package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Controller {
    private Gradient grad1;
    private Gradient grad2;
    private FileInput files;

    @FXML
    private SubScene Scene3D;

    @FXML
    private Button add;

    @FXML
    private TextField xPos;
    @FXML
    private TextField yPos;
    @FXML
    private TextField xSize;
    @FXML
    private TextField ySize;
    @FXML
    private Pane Pane3D;

    private GradientEditor edit1, edit2;

    Pane pane3D;
    ArrayList<MovableSubScene> subScenes;
    SimpleObjectProperty<MovableSubScene> actual_scene;
    Stage primaryStage;

    private void recalculate() {
        pane3D = new Pane();

        pane3D.getChildren().addAll(
                subScenes.stream()
                        .map(scene -> scene.scene)
                        .collect(Collectors.toList()));

        if (actual_scene.getValue() != null) {
            final Rectangle redBorder = new Rectangle(0, 0, Color.TRANSPARENT);
            redBorder.setStroke(Color.RED);
            redBorder.setManaged(false);
            redBorder.setDisable(true);
            redBorder.setStrokeWidth(10);
            redBorder.setLayoutX(actual_scene.getValue().scene.getLayoutX() + 5);
            redBorder.setLayoutY(actual_scene.getValue().scene.getLayoutY() + 5);
            redBorder.setWidth(actual_scene.getValue().scene.getWidth() - 10);
            redBorder.setHeight(actual_scene.getValue().scene.getHeight() - 10);

            pane3D.getChildren().add(redBorder);
            redBorder.setOnMouseDragged(event -> System.out.println("rect dragged"));
        }
        Scene3D.setRoot(pane3D);
    }

    void setTextFields() {
        String xpos, ypos, xsize, ysize;
        if (actual_scene.getValue() == null) {
            xpos = ypos = "-";
            xsize = ysize = "-";
        } else {
            xpos = Integer.toString((int) actual_scene.getValue().getLayoutX());
            ypos = Integer.toString((int) actual_scene.getValue().getLayoutY());
            xsize = Integer.toString((int) actual_scene.getValue().getWidth());
            ysize = Integer.toString((int) actual_scene.getValue().getHeight());
        }
        xPos.setText(xpos);
        yPos.setText(ypos);
        xSize.setText(xsize);
        ySize.setText(ysize);
    }

    public void setup(Stage stage) throws IOException {
        this.primaryStage = stage;
        grad1 = new Gradient();
        grad1.add(new GradientPoint(0, Color.BLUE));
        grad1.add(new GradientPoint(1, Color.RED));
        grad2 = new Gradient();
        grad2.add(new GradientPoint(0, Color.BLACK));
        grad2.add(new GradientPoint(1, Color.WHITE));
        edit1 = new GradientEditor();
        edit2 = new GradientEditor();

        files = new FileInput();

        actual_scene = new SimpleObjectProperty<>();
        subScenes = new ArrayList<>();

        add.setOnAction(event -> {
            subScenes.add(new MovableSubScene(100, 100, actual_scene));
            actual_scene.getValue().redraw();
            recalculate();
            setTextFields();
        });

        xPos.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                actual_scene.getValue().setLayoutX(Double.valueOf(newValue));
                recalculate();
                recalculateWindowSize();
            } catch (Exception ignored) {
            }
        });

        yPos.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                actual_scene.getValue().setLayoutY(Double.valueOf(newValue));
                recalculate();
                recalculateWindowSize();
            } catch (Exception ignored) {
            }
        });

        xSize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                actual_scene.getValue().setWidth(Double.valueOf(newValue));
                recalculate();
                recalculateWindowSize();
            } catch (Exception ignored) {
            }
        });

        ySize.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                actual_scene.getValue().setHeight(Double.valueOf(newValue));
                recalculate();
                recalculateWindowSize();
            } catch (Exception ignored) {
            }
        });

        actual_scene.addListener((observable, oldValue, newValue) -> {
            try {
                recalculate();
                recalculateWindowSize();
                setTextFields();
            } catch (Exception ignored) {
            }
        });


        Scene3D.setOnMouseClicked(event -> {
            if( event.isStillSincePress() ) {
                actual_scene.setValue(null);
                setTextFields();
            }
        });


        pane3D = new Pane();
        Scene3D.setRoot(pane3D);
    }

    private void recalculateWindowSize() {
        double maxWidth = Collections.max(
                subScenes.stream()
                .map(sc -> sc.getLayoutX()+sc.getWidth())
                .collect(Collectors.toList()));

        double maxHeight = Collections.max(
                subScenes.stream()
                .map(sc -> sc.getLayoutY()+sc.getHeight())
                .collect(Collectors.toList()));

        Scene3D.setWidth(maxWidth);
        Scene3D.setHeight(maxHeight);
        primaryStage.sizeToScene();
    }

    void printGradient(Gradient x) {
        System.out.println("\n\nhandler!");
        actual_scene.getValue().grad = x;
        actual_scene.getValue().redraw();
    }

    @FXML
    private void Gradient1_handler() throws Exception {
        SimpleObjectProperty<Gradient> g = new SimpleObjectProperty<>(actual_scene.getValue().grad);
        g.addListener((observable, oldValue, newValue) -> printGradient(newValue));
        actual_scene.getValue().grad = edit1.exec(g, 1, 2);
        actual_scene.getValue().redraw();

    }

    @FXML
    private void Gradient2_handler() throws Exception {
        SimpleObjectProperty<Gradient> g = new SimpleObjectProperty<>(grad2);
        g.addListener((observable, oldValue, newValue) -> printGradient(newValue));
        grad2 = edit2.exec(g, 3, 4);
    }

    @FXML
    private void file_select_handler() throws Exception {
        FileInputSelector edit = new FileInputSelector();
        files = edit.exec(files);
    }
}