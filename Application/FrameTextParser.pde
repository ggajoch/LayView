import g4p_controls.G4P;
import processing.data.*;

import java.util.ArrayList;
import java.util.List;

public class FrameTextParser {
    private String getName() {
        return G4P.selectInput("Input Dialog", "csv", "CSV file select");
    }
    private FrameText parseRow(TableRow row) {
        FrameText rowFrame = new FrameText();
        rowFrame.r = row.getFloat("r");
        rowFrame.g = row.getFloat("g");
        rowFrame.b = row.getFloat("b");

        rowFrame.text = row.getString("text");

        rowFrame.frame = row.getInt("frame");
        rowFrame.size = row.getFloat("size");
        rowFrame.x = row.getFloat("x");
        rowFrame.y = row.getFloat("y");
        return rowFrame;
    }
    public List<FrameText> getFromCSV() {
        Table table = loadTable(getName(), "header");
        List<FrameText> res = new ArrayList<FrameText>();
        for (TableRow row : table.rows()) {
            res.add(parseRow(row));
        }
        return res;
    }
}