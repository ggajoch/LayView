package pl.gajoch.layview.utils.parsers.ODTParser;

import pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers.DataParser;
import pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers.DescriptionParser;
import pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers.TitleParser;
import pl.gajoch.layview.utils.parsers.ODTParser.rowTypesParsers.UnitParser;

import java.io.*;

public class ODTParser {
    private ODTData data;

    private ODTParser() {
        data = new ODTData();
    }

    private void parseLine(String line) {
        line = line.trim();
        if( line.charAt(0) == '#') {
            parseHashLine(line);
        } else {
            parseDataLine(line);
        }
    }

    private void parseHashLine(String line) {
        String cutLine = line.substring(line.indexOf(' ', 2));

        if( ConfigRowTypes.TITLE.checkType(line) ) {
            data.title = TitleParser.parse(cutLine);
        } else if( ConfigRowTypes.UNIT.checkType(line) ) {
            data.registerUnits(UnitParser.parse(cutLine));
        } else if( ConfigRowTypes.COLUMNS.checkType(line) ) {
            data.registerDescriptions(DescriptionParser.parse(cutLine));
        }
    }

    private void parseDataLine(String line) {
        data.registerDataLine(DataParser.parse(line));
    }

    public static ODTData parseFile(File file) {
        ODTParser parser = new ODTParser();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                try {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    parser.parseLine(line);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return parser.data;
    }

    public static void main(String[] args) {
        ODTParser parser = new ODTParser();

        parser.parseLine("# Title: mmArchive Data Table, Mon Sep 07 16:02:38 CEST 2015");
        parser.parseLine("# Units: J {kg/a} s e");
        parser.parseLine("# Columns: adas dsak {a w e r } se");
        parser.parseLine("      \t1e-19\t   2.5 331292131   1.832179e12     ");

        System.out.println("title: " + parser.data.title);

        for(Column x : parser.data.dataColumns) {
            System.out.println("name: " + x.name + ", unit: " + x.unit);
            for(double now : x.values) {
                System.out.println(now);
            }
        }

        File f = new File("G:\\repos\\LayView\\src\\main\\java\\pl\\gajoch\\layview\\utils\\parsers\\ODTParser\\a.odt");
        ODTData data = ODTParser.parseFile(f);

        for(Column x : data.dataColumns) {
            System.out.println("name: " + x.name + ", unit: " + x.unit);
            for(double now : x.values) {
                System.out.println(now);
            }
        }
    }
}
