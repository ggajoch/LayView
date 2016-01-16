import java.io.File;
import java.util.ArrayList;

public class FileInput extends ArrayList<File> {
    @Override
    public FileInput clone() {
        return (FileInput) super.clone();
    }

}
