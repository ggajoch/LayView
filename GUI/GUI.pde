import g4p_controls.*;
import java.util.*;

GButton BtnFileSelect, BtnFolderSelect, BtnColorChoose;
GImageButton BtnPlay, BtnPause;
GradientPointsList gradientList;
GTextField txfMdTitle;
GOption[] optMmessType;
GToggleGroup opgMmessType;

public void handleButtonEvents(GButton button, GEvent event) { 
  // Folder selection
  if (button == BtnFileSelect ) {
    String name = G4P.selectInput("Input Dialog", "txt", "OMF file select");
    println(name);
  }
  else if (button == BtnFolderSelect) {
    String name = G4P.selectFolder("Folder Dialog");
    println(name);
  } else if(button == BtnColorChoose) {
    gradientList.select_color();
  }
}

public void handleButtonEvents(GImageButton button, GEvent event) {
  if(button == BtnPlay) {
    println("play!");
  } else if(button == BtnPause) {
    println("pause!");
  }
}

class GradientPoint {
  public String name;
  public double val;
  public int colour;
  GradientPoint(String name_, double val_, int color_) {
    name = name_;
    val = val_;
    colour = color_;
  }
};

public class GradientPointComparator implements Comparator<GradientPoint> {
  @Override
  public int compare(GradientPoint o1, GradientPoint o2) {
    if( o1.val < o2.val ) {
      return -1;
    } else if( o1.val == o2.val ) {
      return 0;
    } else {
      return 1;
    }
  }
}

class GradientPointsList extends GDropList {
  ArrayList<GradientPoint> DropListElements;
  GradientPointsList(PApplet theApplet, float p0, float p1, float p2, float p3) {
    super(theApplet, p0, p1, p2, p3);
    DropListElements = new ArrayList<GradientPoint>();
    DropListElements.add(new GradientPoint("a", 0, 0));
    reEnumerate();
  }
  void reEnumerate() {
    int index = getSelectedIndex();
    GradientPoint selected = DropListElements.get(index);
    
    Collections.sort(DropListElements, new GradientPointComparator());
    int len = DropListElements.size();
    String[] val = new String[len+1];
    for(int i = 0; i < len; ++i) {
      val[i] = DropListElements.get(i).name;
      if( selected == DropListElements.get(i) ) {
        index = i;
      }
    }
    val[len] = "Add item";
    setItems(val, 0);
    setSelected(index);
  }
  void handler(GDropList list, GEvent event) {
    if( list == this ) {
      if( this.getSelectedText() == "Add item" ) {
        DropListElements.add(new GradientPoint("0", 0, 0));
        reEnumerate();
      }
    }
  }
  void select_color() {
    
    
    int index = getSelectedIndex();
    GradientPoint now = DropListElements.get(index);
    
    int sel_col = G4P.selectColor();
    //BtnColorChoose.setLocalColorScheme(sel_col, true);
    //txfMdTitle.setLocalColorScheme(sel_col, true);
    println(sel_col);
    
    
    now.colour = sel_col;
    DropListElements.set(index, now);
  }
  void set_value(double value) {
    int index = getSelectedIndex();
    GradientPoint now = DropListElements.get(index);
    now.val = value;
    now.name = String.valueOf(now.val);
    DropListElements.set(index, now);
    reEnumerate();
  }
};



public void handleDropListEvents(GDropList list, GEvent event) {
  println("event");
  gradientList.handler(list, event);
}

public void handleTextEvents(GEditableTextControl textcontrol, GEvent event) {
  if( textcontrol == txfMdTitle && event == GEvent.ENTERED ) {
    println(event);
    String now = txfMdTitle.getText();
    gradientList.set_value(Double.parseDouble(now));
  }
}

public void handleToggleControlEvents(GToggleControl checkbox, GEvent event) {
  println(checkbox.tagNo);
}

void setup() {
  size(1000, 1000);
  BtnFileSelect = new GButton(this, 0, 0, 100, 100, "File");
  BtnFolderSelect = new GButton(this, 0, 100, 100, 100, "Folder");
  BtnColorChoose = new GButton(this, 200, 300, 50, 50, "Color");
  String[] names;
  names = new String[] {
    "play.png"
  };
  BtnPlay = new GImageButton(this, 100, 0, 100, 100, names);
  names = new String[] {
    "pause.png"
  };
  BtnPause = new GImageButton(this, 200, 0, 100, 100, names);
  
  gradientList = new GradientPointsList(this, 200, 200, 100, 100);
 
  txfMdTitle = new GTextField(this, 300, 300, 190, 20);
  txfMdTitle.setPromptText("Enter value");
  
  
  String[] t = new String[] { 
    "Boxes", "Vectors"
  };
  opgMmessType = new GToggleGroup();
  optMmessType = new GOption[t.length];
  
  for (int i = 0; i < optMmessType.length; i++) {
    optMmessType[i] = new GOption(this, 500, 500+i*18, 94, 18);
    optMmessType[i].setText(t[i]);
    optMmessType[i].tagNo = 9000 + i;
    opgMmessType.addControl(optMmessType[i]);
  }
  optMmessType[0].setSelected(true);
}


void draw() {
  background(255);
}