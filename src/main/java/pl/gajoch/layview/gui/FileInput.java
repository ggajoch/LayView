package pl.gajoch.layview.gui;

import pl.gajoch.layview.utils.parsers.OMFParser.OMFData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileInput {
    public List<OMFData> omfDataList;
    public List<File> inputFiles;
    public double threshold;

    public FileInput() {
        threshold = 0;
        omfDataList = new ArrayList<>();
        inputFiles = new ArrayList<>();
    }

    public FileInput(FileInput second) {
        this.omfDataList = new ArrayList<>(second.omfDataList);
        this.inputFiles = new ArrayList<>(second.inputFiles);
        this.threshold = second.threshold;
    }

}
