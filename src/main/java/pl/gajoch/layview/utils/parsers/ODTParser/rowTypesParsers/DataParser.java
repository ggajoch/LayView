package pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    public static List<Double> parse(String line) {
        String [] strings = line.split("\\s+");
        List<Double> res = new ArrayList<>();

        for( String val : strings ) {
            res.add(Double.parseDouble(val));
        }
        return res;
    }
}
