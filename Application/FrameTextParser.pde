import g4p_controls.G4P;

public class FrameTextParser {
    private String getName() {
        return G4P.selectInput("Input Dialog", "csv", "CSV file select");
    }
    private float colFileToByte(float x) {
        return (x*255);
    }
    private FrameText parseRow(TableRow row) {
        String text = row.getString("text");

        float r = row.getFloat("r");
        float g = row.getFloat("g");
        float b = row.getFloat("b");

        int frame = row.getInt("frame");

        float x = row.getFloat("x");
        float y = row.getFloat("y");
        float size = row.getFloat("size");

        FontDescriptor font = new FontDescriptor(x, y, size);
        color col = color(colFileToByte(r), colFileToByte(g), colFileToByte(b));
        return new FrameText(text, font, col, frame);
    }
    public Map<Integer, FrameText> getFromCSV() {
        Table table = loadTable(getName(), "header");
        List<FrameText> res = new ArrayList<FrameText>();
        Map<Integer, FrameText> map = new HashMap<Integer, FrameText>();

        for (TableRow row : table.rows()) {
            FrameText now = parseRow(row);
            map.put(now.frame, now);
        }


        return map;
    }
}