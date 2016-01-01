public class Parameter {
  String name, value;
};

class Header {
  HashMap<String, String> map;
  Header() {
    map = new HashMap<String, String>();
  }
  void modify(String name, String value) {
    map.put(name, value);
    //println("Adding (" + name + ") -> " + value);
    //println(map);
  }
  String getStr(String name) {
    return map.get(name);
  }
  int getInt(String name) {
    return int(getStr(name));
  }
  double getDouble(String name) {
    return Double.parseDouble(getStr(name));
  }
};

class DataText {
  ArrayList<PointVector> points;
  DataText() {
    points = new ArrayList<PointVector>();
  }
};

class Segment {
  Header header;
  DataText data;
  Segment() {
    header = new Header();
    data = new DataText();
  }
};


public enum ParserState {
    Segment, Header, Data, NoState 
}

class FileParser {
  ParserState state;
  FileParser() {
    state = ParserState.NoState;
    segments = new ArrayList<Segment>(); 
  }
  ArrayList<Segment> segments;
  
  Segment actual;
  int x_nodes, y_nodes, z_nodes;
  int actual_x_pts = 0, actual_y_pts = 0, actual_z_pts = 0;
  
  void startSegment() {
    actual = new Segment();
    //println("new segment!");
  }
  
  void endSegment() {
    segments.add(actual);
  }
  
  void startData() {
    x_nodes = actual.header.getInt("xnodes");
    y_nodes = actual.header.getInt("ynodes");
    z_nodes = actual.header.getInt("znodes");
    actual_x_pts = actual_y_pts = actual_z_pts = 0;
  }
  
  void parseHeader(String line) {
    String s = line;
    try {
      s = s.replace("#", " ");
      s = s.trim();
      String [] lineSplitted = s.split(":");
      //println("Fail: " + line);
      String name = lineSplitted[0].trim();
      String value = lineSplitted[1].trim();
      //println("Name: |" + name + "|");
      //println("value: |" + value + "|");
      actual.header.modify(name, value);
    } catch(Exception e) {
      //println("Problem with" + line);
      //e.printStackTrace();
    }
  }
  
  void parseData(String line) {
    line = line.trim();
    String[] list = split(line, ' ');
    
    for(String x : list) {
      x = x.trim();
    }
    //print("data ");
    //println("0:" + list[0]);
    //println("1:" + list[1]);
    //println("2:" + list[2]);
    double x = Double.parseDouble(list[0]);
    double y = Double.parseDouble(list[1]);
    double z = Double.parseDouble(list[2]);
    
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
  
  void parseLine(String line) {
    if( state == ParserState.NoState) {
      if( line.contains("# Begin: Segment")) {
          state = ParserState.Segment;
          startSegment();
      }
    } else if( state == ParserState.Segment) {
       if( line.contains("# Begin: Header")) {
          state = ParserState.Header;
          //println("Header!");
       } else if( line.contains("# Begin: Data Text")) {
          state = ParserState.Data;
          startData();
          //println("Data!");
       } else if( line.contains("# End: Segment")) {
          state = ParserState.NoState;
          //println("NoState!");
          endSegment();
       } else {
       }
    } else if( state == ParserState.Header) {
      if( line.contains("# End: Header")) {
        state = ParserState.Segment;
        //println("Segment!");
     } else {
       parseHeader(line);
     }
    } else if( state == ParserState.Data) {
      if( line.contains("# End: Data Text")) {
        state = ParserState.Segment;
        //println("Segment!");
      } else {
        parseData(line);
      }
    }
  }
  
  void parseFile(String name) {
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
        e.printStackTrace();
        line = null;
        break;
      }
    }
  }
};