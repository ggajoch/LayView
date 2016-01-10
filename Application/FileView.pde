import g4p_controls.GDropList;

public class FileView extends ListView<File> {
    FileView(GDropList dropList) {
        super(dropList, new FileModel());
    }

    void addFolder(String path) {
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory() && isOMF(file)) {
                this.add(file);
            }
        }
        reEnumerate();
    }

    private boolean isOMF(File file){
        return file.getName().substring(file.getName().lastIndexOf('.')).equals(".omf");
    }

    public List<File> getListFile() {
        return this.model.list;
    }
}