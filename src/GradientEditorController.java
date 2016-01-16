import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;


public class GradientEditorController {
    // --------------------------- Public API  ---------------------------

    public Gradient getGradient() {
        return gradientToEdit;
    }

    public void setup(Stage stage, Gradient gradient) {
        this.stage = stage;
        setGradient(gradient);
        recalculateView();
        choiceBox.getSelectionModel().selectFirst();
        recalculateColor();
        recalculateGradient();
    }

    // --------------------------- Private variables  ---------------------------

    private Stage stage;
    private Set<GradientPoint> gradientPoints;
    private Gradient gradientToEdit;

    // --------------------------- Private methods  ---------------------------

    private void setGradient(Gradient gradient) {
        gradientToEdit = gradient;
        this.gradientPoints = new TreeSet<>(gradient.getPoints());
        recalculateView();
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            recalculateColor(newValue);
        });
    }

    private void setBackground(Pane pane, Paint paint) {
        BackgroundFill myBF = new BackgroundFill(paint, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));
        pane.setBackground(new Background(myBF));
    }

    private void recalculateView() {
        ObservableList<GradientPoint> DescriptionList = FXCollections.observableArrayList();
        DescriptionList.addAll(gradientPoints.stream().collect(Collectors.toList()));
        choiceBox.setItems(DescriptionList);
    }

    private void recalculateColor(Number active) {
        try {
            setBackground(colorPane, choiceBox.getItems().get(active.intValue()).getColor());
        } catch (Exception ignored) {

        }
    }

    private void recalculateColor() {
        try {
            GradientPoint point = choiceBox.getValue();
            setBackground(colorPane, point.getColor());
        } catch (Exception ignore) {
            setBackground(colorPane, Color.WHITE);
        }
    }

    private void recalculateGradient() {
        if( gradientPoints.isEmpty() ) {
            setBackground(gradientViewPane, Color.WHITE);
            return;
        } else if( gradientPoints.size() == 1 ) {
            Iterator<GradientPoint> it = gradientPoints.iterator();
            setBackground(gradientViewPane, it.next().getColor());
            return;
        }
        List<Stop> list = new ArrayList<>();
        double mini = Double.MAX_VALUE;
        double maxi = Double.MIN_VALUE;

        for(GradientPoint point : gradientPoints) {
            mini = Math.min(mini, point.getOffset());
            maxi = Math.max(maxi, point.getOffset());
        }
        for(GradientPoint point : gradientPoints) {
            double value = point.getOffset();
            value -= mini;
            value /= (maxi-mini);
            list.add(new Stop(value, point.getColor()));
        }

        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1.0, true, CycleMethod.REPEAT, list);
        setBackground(gradientViewPane, lg1);
    }

    private void showDuplicateAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Duplicate values");
        alert.setContentText("Cannot insert point with already existing value!");
        alert.showAndWait();
    }

    private GradientPoint askForPoint(GradientPoint actualPoint) throws Exception {
        GradientPointEditor editor;
        editor = new GradientPointEditor();
        return editor.exec(actualPoint);
    }

    private boolean addPoint(GradientPoint point) {
        if( gradientPoints.contains(point) ) {
            showDuplicateAlert();
            return false;
        }
        gradientPoints.add(point);
        return true;
    }

    private boolean editPoint(GradientPoint prev, GradientPoint now) {
        if( prev == null ) {
            return addPoint(now);
        }
        if( now.toString().equals(prev.toString()) ) {
            gradientPoints.remove(prev);
            return addPoint(now);
        } else {
            boolean successful = addPoint(now);
            if (successful) {
                gradientPoints.remove(prev);
            }
            return successful;
        }
    }

    // --------------------------- Objects  ---------------------------

    @FXML
    private ChoiceBox<GradientPoint> choiceBox;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button clearButton;
    @FXML
    private Pane colorPane;
    @FXML
    private Pane gradientViewPane;

    // --------------------------- button handlers ---------------------------

    @FXML
    private void add_handler() {
        try {
            GradientPoint point = new GradientPoint();
            do {
                point = askForPoint(point);
            } while( ! addPoint(point) );
            recalculateView();
            choiceBox.getSelectionModel().select(point);
            recalculateColor();
            recalculateGradient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void edit_handler() {
        try {
            GradientPoint original = choiceBox.getValue();
            GradientPoint point = (GradientPoint)original.clone();
            do {
                point = askForPoint(point);
            } while( ! editPoint(original, point) );
            recalculateView();
            choiceBox.getSelectionModel().select(point);
            recalculateColor();
            recalculateGradient();
        } catch (Exception ignored) {
        }
    }

    @FXML
    private void delete_handler() {
        try {
            GradientPoint point = choiceBox.getValue();
            gradientPoints.remove(point);
            recalculateView();
            choiceBox.getSelectionModel().selectFirst();
            recalculateColor();
            recalculateGradient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clear_handler() {
        try {
            gradientPoints.clear();
            recalculateView();
            recalculateColor();
            recalculateGradient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ok_handler() {
        gradientPoints.forEach(System.out::println);
        gradientToEdit.clear();
        for(GradientPoint p : gradientPoints) {
            gradientToEdit.add(p);
        }
        this.stage.close();
    }

    @FXML
    private void cancel_handler() {
        this.stage.close();
    }
}
