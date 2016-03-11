package pl.gajoch.layview.utils.parsers.ODTParser;

import java.util.ArrayList;
import java.util.List;

public class Column {
    public String name, unit;
    public List<Double> values;

    public Column() {
        name = unit = "";
        values = new ArrayList<>();
    }
}
