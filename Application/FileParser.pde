import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

private enum ParserState {
  Segment, Header, Data, NoState
};
  
public class FileParser {
  private class Header {
    HashMap<String, String> map;
    Header() {
      map = new HashMap<String, String>();
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
    ArrayList<PointVector> points;
    DataText() {
      points = new ArrayList<PointVector>();
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

  FileParser() {
    state = ParserState.NoState;
    segments = new ArrayList<Segment>(); 
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
    List<String> list = new ArrayList<String>(Arrays.asList(line.split(" ")));

    double x = Double.parseDouble(list.get(0));
    double y = Double.parseDouble(list.get(1));
    double z = Double.parseDouble(list.get(2));
    
    DVector actual_position = new DVector(actual.header.getDouble("xbase") + actual_x_pts * actual.header.getDouble("xstepsize"),
                                          actual.header.getDouble("ybase") + actual_y_pts * actual.header.getDouble("ystepsize"),
                                          actual.header.getDouble("zbase") + actual_z_pts * actual.header.getDouble("zstepsize"));

    actual.data.points.add(new PointVector(actual_position, new DVector(x, y, z)));
    
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
       } else {
         //error ?
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
  
  public void parseFile(String name) {
    BufferedReader reader;
    String line;
    reader = createReader(name);
    
    while(true) {
      try {
        line = reader.readLine();
        if( line == null ) {
          break;
        }
        parseLine(line);
      } catch(IOException e) {
        println("Problem with" + name);
        e.printStackTrace();
        line = null;
        break;
      }
    }
  }
};