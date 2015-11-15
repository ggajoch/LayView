import g4p_controls.*;
import java.util.*;

GButton BtnFileSelect, BtnFolderSelect, BtnColorChoose;
GImageButton BtnPlay, BtnPause;
GradientPointsList gradientList;
GTextField txfMdTitle;
GOption[] optMmessType;
GToggleGroup opgMmessType;

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

class GradientPointsList {
  ArrayList<GradientPoint> DropListElements;
  PGraphics pg;
  GradientPointsList() {
    DropListElements = new ArrayList<GradientPoint>();
    DropListElements.add(new GradientPoint("0", 0, -1));
    DropListElements.add(new GradientPoint("1", 1, -16777216));
    pg = createGraphics(100, 30, JAVA2D);
    reEnumerate();
    update_color();
	update_text();
  }
  void reEnumerate() {
    int index = GradientList.getSelectedIndex();
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
    GradientList.setItems(val, 0);
    GradientList.setSelected(index);
  }
  void handler() {
    if( GradientList.getSelectedText() == "Add item" ) {
      DropListElements.add(new GradientPoint("0", 0, 0));
      reEnumerate();
    } else {
      update_text();
    }
    update_color();
  }
  void update_color() {
    int index = GradientList.getSelectedIndex();
    GradientPoint now = DropListElements.get(index);
    pg.beginDraw();
    pg.background(now.colour);
    pg.endDraw();
    colorPad.setGraphic(pg);
  }
  void update_text() {
    int index = GradientList.getSelectedIndex();
    GradientPoint selected = DropListElements.get(index);
    pointValue.setText(String.valueOf(selected.val));
  }
  void select_color() {
    int index = GradientList.getSelectedIndex();
    GradientPoint now = DropListElements.get(index);
    
    int sel_col = G4P.selectColor();
    //BtnColorChoose.setLocalColorScheme(sel_col, true);
    println(sel_col);
    
    now.colour = sel_col;
    DropListElements.set(index, now);
    update_color();
  }
  void set_value(double value) {
    int index = GradientList.getSelectedIndex();
    GradientPoint now = DropListElements.get(index);
    now.val = value;
    now.name = String.valueOf(now.val);
    DropListElements.set(index, now);
    reEnumerate();
  }
};


public void handleToggleControlEvents(GToggleControl checkbox, GEvent event) {
  println(checkbox.tagNo);
}

/*void setup() {
  size(450, 180);
  createGUI();
  gradientList = new GradientPointsList();
}


void draw() {
  background(255);
}*/