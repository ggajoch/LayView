package pl.gajoch.layview.utils.parsers.OMFParser;

import com.sun.javafx.geom.Vec3d;
import pl.gajoch.layview.graphics3d.SurfacePoint;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;


public class OMFParser {

    private enum ParserState {
        Segment, Header, Data, NoState
    }

    private class Header {
        HashMap<String, String> map;
        Header() {
            map = new HashMap<>();
        }
        void modify(String name, String value) {
            map.put(name, value);
        }
        String getStr(String name) {
            return map.get(name);
        }
        int getInt(String name) {
            return Integer.valueOf(getStr(name));
        }
        double getDouble(String name) {
            return Double.parseDouble(getStr(name));
        }
    }

    private class DataText {
        ArrayList<SurfacePoint> points;
        DataText() {
            points = new ArrayList<>();
        }
    }

    private class Segment {
        Header header;
        DataText data;
        Segment() {
            header = new Header();
            data = new DataText();
        }
    }

    private ParserState state;
    private ArrayList<Segment> segments;
    private Segment actual;
    private int x_nodes, y_nodes, z_nodes;
    private int actual_x_pts = 0, actual_y_pts = 0, actual_z_pts = 0;

    private OMFParser() {
        state = ParserState.NoState;
        segments = new ArrayList<>();
    }

    private void startSegment() {
        actual = new Segment();
    }

    private void endSegment() {
        segments.add(actual);
    }

    private void startData() {
        x_nodes = actual.header.getInt("xnodes");
        y_nodes = actual.header.getInt("ynodes");
        z_nodes = actual.header.getInt("znodes");
        actual_x_pts = actual_y_pts = actual_z_pts = 0;
    }

    private void parseHeader(String line) {
        String s = line;
        try {
            s = s.replace("#", " ");
            s = s.trim();
            String [] lineSplitted = s.split(":");
            String name = lineSplitted[0].trim();
            String value = lineSplitted[1].trim();
            actual.header.modify(name, value);
        } catch(Exception e) {
            //not a problem, just blank lines
            //println("Problem with" + line);
            //e.printStackTrace();
        }
    }

    private void parseData(String line) {
        line = line.trim();
        List<String> list = new ArrayList<>(Arrays.asList(line.split(" ")));

        double x = Double.parseDouble(list.get(0));
        double y = Double.parseDouble(list.get(1));
        double z = Double.parseDouble(list.get(2));

        Vec3d actual_position = new Vec3d(actual.header.getDouble("xbase") + actual_x_pts * actual.header.getDouble("xstepsize"),
                actual.header.getDouble("ybase") + actual_y_pts * actual.header.getDouble("ystepsize"),
                actual.header.getDouble("zbase") + actual_z_pts * actual.header.getDouble("zstepsize"));

        actual.data.points.add(new SurfacePoint(actual_position, new Vec3d(x, y, z)));

        actual_x_pts++;
        if( actual_x_pts == x_nodes ) {
            actual_x_pts = 0;
            actual_y_pts++;

            if( actual_y_pts == y_nodes ) {
                actual_y_pts = 0;
                actual_z_pts++;
            }
        }
    }

    private void parseLine(String line) {
        if( state == ParserState.NoState) {
            if( line.contains("# Begin: Segment")) {
                state = ParserState.Segment;
                startSegment();
            }
        } else if( state == ParserState.Segment) {
            if( line.contains("# Begin: Header")) {
                state = ParserState.Header;
            } else if( line.contains("# Begin: Data Text")) {
                state = ParserState.Data;
                startData();
            } else if( line.contains("# End: Segment")) {
                state = ParserState.NoState;
                endSegment();
            }
        } else if( state == ParserState.Header) {
            if( line.contains("# End: Header")) {
                state = ParserState.Segment;
            } else {
                parseHeader(line);
            }
        } else if( state == ParserState.Data) {
            if( line.contains("# End: Data Text")) {
                state = ParserState.Segment;
            } else {
                parseData(line);
            }
        }
    }

    public static OMFData parseFile(File file) {
        OMFParser parser = new OMFParser();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while(true) {
                try {
                    line = reader.readLine();
                    if( line == null ) {
                        break;
                    }
                    parser.parseLine(line);
                } catch(IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if(reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new OMFData(parser.segments.get(0).data.points,
                parser.segments.get(0).header.getDouble("xstepsize"),
                parser.segments.get(0).header.getDouble("ystepsize"),
                parser.segments.get(0).header.getDouble("zstepsize"));
    }
}
