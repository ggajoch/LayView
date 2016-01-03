import java.io.File;
import java.util.ArrayList;

public class FileModel extends ListModel<File> {
    FileModel() {
        super(new ArrayList<File>());
    }

    @Override
    String labelFor(File element) {
        return element.getName();
    }
}