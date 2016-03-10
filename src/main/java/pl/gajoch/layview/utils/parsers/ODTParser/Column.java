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

    public Column(String name, String unit, List<Double> values) {
        this.name = name;
        this.unit = unit;
        this.values = values;
    }
}
