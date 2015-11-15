/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.

 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
     // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */

synchronized public void win_draw2(PApplet appc, GWinData data) { //_CODE_:window1:376141:
  appc.background(230);
} //_CODE_:window1:376141:

public void btnSelectFile_handler(GButton source, GEvent event) { //_CODE_:btnSelectFile:373792:
  println("btnSelectFile - GButton >> GEvent." + event + " @ " + millis());
  String name = G4P.selectInput("Input Dialog", "txt", "OMF file select");
  println(name);
  showFile(name);
} //_CODE_:btnSelectFile:373792:

public void btnSelectFolder_handler(GButton source, GEvent event) { //_CODE_:btnSelectFolder:445216:
  println("btnSelectFolder - GButton >> GEvent." + event + " @ " + millis());
  String name = G4P.selectFolder("Folder Dialog");
  println(name);
} //_CODE_:btnSelectFolder:445216:

public void Boxes_handler(GOption source, GEvent event) { //_CODE_:Boxes:297206:
  println("Boxes - GOption >> GEvent." + event + " @ " + millis());
  println(source.tagNo);
} //_CODE_:Boxes:297206:

public void Vectors_handler(GOption source, GEvent event) { //_CODE_:Vectors:644165:
  println("Vectors - GOption >> GEvent." + event + " @ " + millis());
  println(source.tagNo);
} //_CODE_:Vectors:644165:

public void btnPlay_handler(GImageButton source, GEvent event) { //_CODE_:btnPlay:434127:
  println("btnPlay - GImageButton >> GEvent." + event + " @ " + millis());
  println("play!");
} //_CODE_:btnPlay:434127:

public void btnPause_handler(GImageButton source, GEvent event) { //_CODE_:btnPause:773048:
  println("btnPause - GImageButton >> GEvent." + event + " @ " + millis());
  println("pause!");
} //_CODE_:btnPause:773048:

public void GradientList_handler(GDropList source, GEvent event) { //_CODE_:GradientList:414730:
  println("dropList1 - GDropList >> GEvent." + event + " @ " + millis());
  println("GradientList - GDropList >> GEvent." + event + " @ " + millis());
  gradientList.handler();
} //_CODE_:GradientList:414730:

public void btnColor_handler(GButton source, GEvent event) { //_CODE_:btnColor:569956:
  println("btnColor - GButton >> GEvent." + event + " @ " + millis());
  gradientList.select_color();
} //_CODE_:btnColor:569956:

public void pointValue_handler(GTextField source, GEvent event) { //_CODE_:pointValue:437692:
  println("pointValue - GTextField >> GEvent." + event + " @ " + millis());
  if( event == GEvent.ENTERED ) {
    String now = source.getText();
    println(now);
    gradientList.set_value(Double.parseDouble(now));
  }
} //_CODE_:pointValue:437692:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setCursor(ARROW);
  surface.setTitle("Sketch Window");
  window1 = GWindow.getWindow(this, "Window title", 0, 0, 500, 150, JAVA2D);
  window1.addDrawHandler(this, "win_draw2");
  btnSelectFile = new GButton(window1, 10, 10, 80, 30);
  btnSelectFile.setText("Select file");
  btnSelectFile.addEventHandler(this, "btnSelectFile_handler");
  btnSelectFolder = new GButton(window1, 10, 50, 80, 30);
  btnSelectFolder.setText("Select folder");
  btnSelectFolder.addEventHandler(this, "btnSelectFolder_handler");
  togGroup1 = new GToggleGroup();
  Boxes = new GOption(window1, 110, 10, 120, 20);
  Boxes.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  Boxes.setText("Boxes");
  Boxes.setOpaque(false);
  Boxes.addEventHandler(this, "Boxes_handler");
  Vectors = new GOption(window1, 110, 30, 120, 20);
  Vectors.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  Vectors.setText("Vectors");
  Vectors.setOpaque(false);
  Vectors.addEventHandler(this, "Vectors_handler");
  togGroup1.addControl(Boxes);
  Boxes.setSelected(true);
  togGroup1.addControl(Vectors);
  btnPlay = new GImageButton(window1, 10, 90, 50, 50, new String[] { "play.png", "play.png", "play.png" } );
  btnPlay.addEventHandler(this, "btnPlay_handler");
  btnPause = new GImageButton(window1, 60, 90, 50, 50, new String[] { "pause.png", "pause.png", "pause.png" } );
  btnPause.addEventHandler(this, "btnPause_handler");
  GradientList = new GDropList(window1, 240, 10, 90, 120, 5);
  GradientList.setItems(loadStrings("list_414730"), 0);
  GradientList.addEventHandler(this, "GradientList_handler");
  btnColor = new GButton(window1, 240, 40, 80, 30);
  btnColor.setText("Select color");
  btnColor.addEventHandler(this, "btnColor_handler");
  pointValue = new GTextField(window1, 340, 10, 100, 20, G4P.SCROLLBARS_NONE);
  pointValue.setOpaque(true);
  pointValue.addEventHandler(this, "pointValue_handler");
  colorPad = new GSketchPad(window1, 340, 40, 100, 30);
}

// Variable declarations 
// autogenerated do not edit
GWindow window1;
GButton btnSelectFile; 
GButton btnSelectFolder; 
GToggleGroup togGroup1; 
GOption Boxes; 
GOption Vectors; 
GImageButton btnPlay; 
GImageButton btnPause; 
GDropList GradientList; 
GButton btnColor; 
GTextField pointValue; 
GSketchPad colorPad; 