package pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers;

import java.util.ArrayList;
import java.util.List;

public class CurlyBracketLineParser {
    public static List<String> parse(String line) {
        List<String> list = new ArrayList<>();

        line = line.trim();
        while( ! line.equals("") ) {
            String now;
            if( line.charAt(0) == '{' ) {
                now = line.substring(1, line.indexOf('}'));
                line = line.substring(line.indexOf('}')+1);
            } else {
                int nextBlank = line.indexOf(' ');
                if( nextBlank == -1 ) { // empty string
                    now = line;
                    line = "";
                } else {
                    now = line.substring(0, nextBlank);
                    line = line.substring(nextBlank + 1);
                }
            }
            list.add(now);
            line = line.trim();
        }
        return list;
    }
}
