package pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers;

import java.util.List;

public class UnitParser {
    public static List<String> parse(String line) {
        return CurlyBracketLineParser.parse(line);
    }
}