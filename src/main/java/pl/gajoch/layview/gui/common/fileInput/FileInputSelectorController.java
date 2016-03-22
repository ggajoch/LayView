package pl.gajoch.layview.gui.common.fileInput;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import pl.gajoch.layview.options.FileInput;
import pl.gajoch.layview.utils.GUIUtils;
import pl.gajoch.layview.utils.gui.RichTextField;
import pl.gajoch.layview.utils.parsers.OMFParser.OMFParser;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static pl.gajoch.layview.utils.GUIUtils.showErrorMessage;


public class FileInputSelectorController {
    private JFrame frame;
    private FileInput files;
    private SimpleObjectProperty<FileInput> filesProperty;

    @FXML
    private ChoiceBox<File> fileChoiceBox;
    @FXML
    private Button addFileButton;
    @FXML
    private Button addFolderButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField thresholdField;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressIndicator;

    public void setup(JFrame frame, SimpleObjectProperty<FileInput> filesProperty) {
        this.frame = frame;
        this.filesProperty = filesProperty;
        this.fileChoiceBox.setConverter(new FileConverter());
        setFiles(filesProperty.get());
        recalculateView();
        closeButton.setVisible(true);
    }

    public final FileInput getFiles() {
        return this.files;
    }

    private void setFiles(FileInput files) {
        try {
            this.files = new FileInput(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recalculateView() {
        ObservableList<File> DescriptionList = FXCollections.observableArrayList();
        DescriptionList.addAll(files.inputFiles.stream().collect(Collectors.toList()));
        fileChoiceBox.setItems(DescriptionList);
        fileChoiceBox.getSelectionModel().selectLast();
    }

    private void showProgressBar(String text) {
        this.closeButton.setVisible(false);
        this.progressIndicator.setText(text);
    }

    private void hideProgressBar() {
        this.closeButton.setVisible(true);
        this.progressIndicator.setText("");
    }

    private void setProgress(double val) {
        this.progressBar.setProgress(val);
    }

    @FXML
    private void addFile_handler() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select omf file(s)...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("OMF file", "*.omf"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        List<File> fileList = fileChooser.showOpenMultipleDialog(null);
        new Thread() {
            public void run() {
                Platform.runLater(() -> showProgressBar("Adding ..."));
                try {
                    double len = fileList.size();
                    int iter = 0;

                    for (File file : fileList) {
                        if (file == null)
                            continue;
                        if (!files.inputFiles.contains(file)) {
                            files.inputFiles.add(file);
                            setProgress(iter++ / len);
                        }
                    }
                } catch (Exception ignored) {
                } finally {
                    Platform.runLater(() -> {
                        recalculateView();
                        hideProgressBar();
                    });
                }
            }
        }.start();
    }

    @FXML
    private void addFolder_handler() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select folder...");
        File folder = directoryChooser.showDialog(null);
        new Thread() {
            public void run() {
                double len = 0;
                if (folder != null) {
                    Platform.runLater(() -> showProgressBar("Adding ..."));
                    for (File file : folder.listFiles()) {
                        if (!file.isDirectory() && isOMF(file)) {
                            len += 1;
                        }
                    }

                    int iter = 0;
                    for (File file : folder.listFiles()) {
                        if (!file.isDirectory() && isOMF(file)) {
                            if (!files.inputFiles.contains(file)) {
                                files.inputFiles.add(file);
                                setProgress(iter++ / len);
                            }
                        }
                    }
                }
                Platform.runLater(() -> {
                    recalculateView();
                    hideProgressBar();
                });
            }
        }.start();
    }

    private boolean isOMF(File file) {
        return file.getName().substring(file.getName().lastIndexOf('.')).equals(".omf");
    }

    @FXML
    private void remove_handler() {
        files.inputFiles.remove(fileChoiceBox.getValue());
        recalculateView();
    }

    @FXML
    private void clear_handler() {
        files.inputFiles.clear();
        recalculateView();
    }

    @FXML
    private void close_handler() {
        try {
            this.files.threshold = RichTextField.of(thresholdField).getDouble();
            ;
        } catch (NumberFormatException ex) {
            showErrorMessage("Bad number format!", "Cannot parse \"" + thresholdField.getText() + "\"");
            return;
        }
        new Thread() {
            public void run() {
                Platform.runLater(() -> showProgressBar("Parsing ..."));
                files.omfDataList.clear();
                int iteration = 0;
                setProgress(0);
                double len = getFiles().inputFiles.size();
                for (File file : getFiles().inputFiles) {
                    files.omfDataList.add(OMFParser.parseFile(file));
                    double x = iteration++;
                    x /= len;
                    setProgress(x);
                }
                filesProperty.set(files);
                Platform.runLater(() -> {
                    hideProgressBar();
                    GUIUtils.closeJFrame(frame);
                });
            }
        }.start();
    }

    private class FileConverter extends StringConverter<File> {
        public String toString(File file) {
            try {
                return file.getName();
            } catch (Exception e) {
                return "";
            }
        }

        public File fromString(String s) {
            return new File(s);
        }
    }
}
