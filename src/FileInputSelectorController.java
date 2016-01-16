import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;


public class FileInputSelectorController {
    // ----------------------------- Public API  -----------------------------

    public void setup(Stage stage, FileInput files) {
        this.stage = stage;
        setFiles(files);
        this.fileChoiceBox.setConverter(new FileConverter());
    }

    public final FileInput getFiles() {
        return this.files;
    }

    // -------------------------- Private variables  -------------------------

    private Stage stage;
    private FileInput files;

    // --------------------------- Private methods  --------------------------

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

    private void setFiles(FileInput files) {
        try {
            this.files = files.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recalculateView() {
        ObservableList<File> DescriptionList = FXCollections.observableArrayList();
        DescriptionList.addAll(files.stream().collect(Collectors.toList()));
        fileChoiceBox.setItems(DescriptionList);
        fileChoiceBox.getSelectionModel().selectLast();
    }

    // ------------------------------- Objects  ------------------------------

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
    private TextField threshold;

    // --------------------------- button handlers ---------------------------

    @FXML
    private void addFile_handler() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select omf file(s)...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("OMF file", "*.omf"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        List<File> readed_files = fileChooser.showOpenMultipleDialog(stage);
        readed_files.stream().filter(file -> file != null).forEach(file -> {
            files.add(file);
            recalculateView();
        });
    }

    @FXML
    private void addFolder_handler() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select folder...");
        File folder = directoryChooser.showDialog(stage);
        if (folder != null) {
            for (File file : folder.listFiles()) {
                try {
                    if (!file.isDirectory() && isOMF(file)) {
                        files.add(file);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        recalculateView();
    }

    private boolean isOMF(File file) {
        return file.getName().substring(file.getName().lastIndexOf('.')).equals(".omf");
    }

    @FXML
    private void remove_handler() {
        files.remove(fileChoiceBox.getValue());
        recalculateView();
    }

    @FXML
    private void clear_handler() {
        files.clear();
        recalculateView();
    }

    @FXML
    private void close_handler() {
        this.stage.close();
    }

}
