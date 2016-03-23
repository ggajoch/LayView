package pl.gajoch.layview.options;

import java.io.File;

public class ExportOptions {
    public final File file;
    public final double FPS;

    public ExportOptions() {
        this.file = new File("");
        this.FPS = 0;
    }

    public ExportOptions(File file, double FPS) {
        this.file = file;
        this.FPS = FPS;
    }

    public ExportOptions(ExportOptions rhs) {
        this.file = new File(rhs.file.getAbsolutePath());
        this.FPS = rhs.FPS;
    }
}
