/*import g4p_controls.GDropList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileList {
    GDropList dropList;
    List<File> file_list = new ArrayList<File>();

    FileList(GDropList dropList) {
        this.dropList = dropList;
        reEnumerate();
    }

    void reEnumerate() {
        ArrayList<String> list = new ArrayList<String>();
        if (file_list.isEmpty()) {
            list.add(" ");
        } else {
            for (File file : file_list) {
                list.add(file.getName());
            }
        }
        dropList.setItems(list, 0);
    }

    void add(File file) {
        file_list.add(file);
        reEnumerate();
    }

    void addFolder(String path) {
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory() && isOMF(file)) {
                file_list.add(file);
            }
        }
        reEnumerate();
    }

    private boolean isOMF(File file){
        return file.getName().substring(file.getName().lastIndexOf('.')).equals(".omf");
    }

    void remove() {
        String selected = dropList.getSelectedText();
        for (File file : file_list) {
            if (file.getName().equals(selected)) {
                file_list.remove(file);
            }
        }
        reEnumerate();
    }

    void clear() {
        file_list.clear();
        reEnumerate();
    }
}













FileList file_list;*/