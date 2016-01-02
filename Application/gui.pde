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

public void btnAddFile_handler(GButton source, GEvent event) { //_CODE_:btnAddFile:373792:
  println("btnSelectFile - GButton >> GEvent." + event + " @ " + millis());
  String name = G4P.selectInput("Input Dialog", "txt", "OMF file select");
  println(name);
  //showFile(name);
  file_list.add(new File(name));
} //_CODE_:btnAddFile:373792:

public void btnSelectFolder_handler(GButton source, GEvent event) { //_CODE_:btnSelectFolder:445216:
  println("btnSelectFolder - GButton >> GEvent." + event + " @ " + millis());
  String name = G4P.selectFolder("Folder Dialog");
  println(name);
  file_list.addFolder(name);
} //_CODE_:btnSelectFolder:445216:

public void Boxes_handler(GOption source, GEvent event) { //_CODE_:Boxes:297206:
  println("Boxes - GOption >> GEvent." + event + " @ " + millis());
  println(source.tagNo);
  vectors = 0;
} //_CODE_:Boxes:297206:

public void Vectors_handler(GOption source, GEvent event) { //_CODE_:Vectors:644165:
  println("Vectors - GOption >> GEvent." + event + " @ " + millis());
  println(source.tagNo);
  vectors = 1;
} //_CODE_:Vectors:644165:

public void btnPlay_handler(GImageButton source, GEvent event) { //_CODE_:btnPlay:434127:
  println("btnPlay - GImageButton >> GEvent." + event + " @ " + millis());
  println("play!");
  record = 2;
  gradients.edit(0);
  
} //_CODE_:btnPlay:434127:

public void btnPause_handler(GImageButton source, GEvent event) { //_CODE_:btnPause:773048:
  println("btnPause - GImageButton >> GEvent." + event + " @ " + millis());
  println("pause!");
  gradients.edit(1);
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

public void listFiles_handler(GDropList source, GEvent event) { //_CODE_:listFiles:728947:
  println("dropList1 - GDropList >> GEvent." + event + " @ " + millis());
} //_CODE_:listFiles:728947:

public void btnClearAll_handler(GButton source, GEvent event) { //_CODE_:btnClearAll:658246:
  println("btnClearAll - GButton >> GEvent." + event + " @ " + millis());
  file_list.clear();
} //_CODE_:btnClearAll:658246:

public void btnRemoveSelected_handler(GButton source, GEvent event) { //_CODE_:btnRemoveSelected:830927:
  println("btnRemoveSelected - GButton >> GEvent." + event + " @ " + millis());
  file_list.remove();
} //_CODE_:btnRemoveSelected:830927:

public void valFPS_handler(GTextField source, GEvent event) { //_CODE_:valFPS:581728:
  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valFPS:581728:

public void btnExport_handler(GButton source, GEvent event) { //_CODE_:btnExport:476853:
  println("btnExport - GButton >> GEvent." + event + " @ " + millis());
} //_CODE_:btnExport:476853:

public void valThreshold_handler(GTextField source, GEvent event) { //_CODE_:valThreshold:972234:
  println("textfield2 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valThreshold:972234:

public void valArrowScale_handler(GTextField source, GEvent event) { //_CODE_:valArrowScale:222385:
  println("textfield2 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valArrowScale:222385:

public void valArrowWidth_handler(GTextField source, GEvent event) { //_CODE_:valArrowWidth:200671:
  println("textfield3 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valArrowWidth:200671:

public void valArrowTip_handler(GTextField source, GEvent event) { //_CODE_:valArrowTip:383864:
  println("textfield4 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valArrowTip:383864:

public void valScaleValues_handler(GTextField source, GEvent event) { //_CODE_:valScaleValues:324509:
  println("valScaleValues - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valScaleValues:324509:

public void valScaleXYZ_handler(GTextField source, GEvent event) { //_CODE_:valScaleXYZ:424871:
  println("valScaleXYZ - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valScaleXYZ:424871:

synchronized public void win_draw1(PApplet appc, GWinData data) { //_CODE_:GradientEditWindow:607983:
  appc.background(230);
} //_CODE_:GradientEditWindow:607983:

Double valX = new Double(0.0);
  int colX = 0;
  
public void btnGradAdd_handler(GButton source, GEvent event) { //_CODE_:btnGradAdd:714538:
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
  
  
  valX += 0.1;
  colX += 100;
  println(valX);
  gradients.gradient_view.add(new GradientPoint("", valX, colX));
} //_CODE_:btnGradAdd:714538:

public void btnGradRemove_handler(GButton source, GEvent event) { //_CODE_:btnGradRemove:368151:
  println("button2 - GButton >> GEvent." + event + " @ " + millis());
  gradients.gradient_view.remove();
} //_CODE_:btnGradRemove:368151:

public void btnGradClear_handler(GButton source, GEvent event) { //_CODE_:btnGradClear:424281:
  println("button3 - GButton >> GEvent." + event + " @ " + millis());
  gradients.gradient_view.clear();
} //_CODE_:btnGradClear:424281:

public void btnGradOK_handler(GButton source, GEvent event) { //_CODE_:btnGradOK:994464:
  println("button4 - GButton >> GEvent." + event + " @ " + millis());
  gradients.OK_Handler();
} //_CODE_:btnGradOK:994464:

public void btnGradCancel_handler(GButton source, GEvent event) { //_CODE_:btnGradCancel:328569:
  println("btnGradCancel - GButton >> GEvent." + event + " @ " + millis());
  gradients.Cancel_Handler();
} //_CODE_:btnGradCancel:328569:

synchronized public void win_draw3(PApplet appc, GWinData data) { //_CODE_:window3:640294:
  appc.background(230);
} //_CODE_:window3:640294:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(true);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setCursor(ARROW);
  surface.setTitle("Sketch Window");
  window1 = GWindow.getWindow(this, "KEEP_OPEN", 100, 100, 1000, 1000, JAVA2D);
  window1.setActionOnClose(G4P.EXIT_APP);
  window1.addDrawHandler(this, "win_draw2");
  btnAddFile = new GButton(window1, 190, 20, 80, 30);
  btnAddFile.setText("Add file");
  btnAddFile.addEventHandler(this, "btnAddFile_handler");
  btnSelectFolder = new GButton(window1, 190, 60, 80, 30);
  btnSelectFolder.setText("Select folder");
  btnSelectFolder.addEventHandler(this, "btnSelectFolder_handler");
  togGroup1 = new GToggleGroup();
  Boxes = new GOption(window1, 370, 20, 120, 20);
  Boxes.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  Boxes.setText("Boxes");
  Boxes.setOpaque(false);
  Boxes.addEventHandler(this, "Boxes_handler");
  Vectors = new GOption(window1, 370, 50, 120, 20);
  Vectors.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  Vectors.setText("Vectors");
  Vectors.setOpaque(false);
  Vectors.addEventHandler(this, "Vectors_handler");
  togGroup1.addControl(Boxes);
  Boxes.setSelected(true);
  togGroup1.addControl(Vectors);
  btnPlay = new GImageButton(window1, 20, 250, 50, 50, new String[] { "play.png", "play.png", "play.png" } );
  btnPlay.addEventHandler(this, "btnPlay_handler");
  btnPause = new GImageButton(window1, 80, 250, 50, 50, new String[] { "pause.png", "pause.png", "pause.png" } );
  btnPause.addEventHandler(this, "btnPause_handler");
  GradientList = new GDropList(window1, 520, 20, 90, 120, 5);
  GradientList.setItems(loadStrings("list_414730"), 0);
  GradientList.addEventHandler(this, "GradientList_handler");
  btnColor = new GButton(window1, 600, 60, 90, 30);
  btnColor.setText("Select color");
  btnColor.addEventHandler(this, "btnColor_handler");
  pointValue = new GTextField(window1, 620, 20, 100, 20, G4P.SCROLLBARS_NONE);
  pointValue.setOpaque(true);
  pointValue.addEventHandler(this, "pointValue_handler");
  colorPad = new GSketchPad(window1, 630, 60, 100, 30);
  listFiles = new GDropList(window1, 10, 20, 170, 330, 10);
  listFiles.setItems(loadStrings("list_728947"), 0);
  listFiles.addEventHandler(this, "listFiles_handler");
  btnClearAll = new GButton(window1, 10, 60, 80, 30);
  btnClearAll.setText("Clear all");
  btnClearAll.addEventHandler(this, "btnClearAll_handler");
  btnRemoveSelected = new GButton(window1, 100, 60, 80, 30);
  btnRemoveSelected.setText("Remove selected");
  btnRemoveSelected.addEventHandler(this, "btnRemoveSelected_handler");
  valFPS = new GTextField(window1, 10, 190, 70, 20, G4P.SCROLLBARS_NONE);
  valFPS.setOpaque(true);
  valFPS.addEventHandler(this, "valFPS_handler");
  labelFPS = new GLabel(window1, 10, 170, 70, 20);
  labelFPS.setText("FPS");
  labelFPS.setOpaque(false);
  btnExport = new GButton(window1, 90, 170, 80, 40);
  btnExport.setText("Export");
  btnExport.setTextBold();
  btnExport.addEventHandler(this, "btnExport_handler");
  valThreshold = new GTextField(window1, 250, 190, 70, 20, G4P.SCROLLBARS_NONE);
  valThreshold.setOpaque(true);
  valThreshold.addEventHandler(this, "valThreshold_handler");
  labelThreshold = new GLabel(window1, 250, 170, 70, 20);
  labelThreshold.setText("Threshold");
  labelThreshold.setOpaque(false);
  lableArrowWidth = new GLabel(window1, 470, 240, 80, 20);
  lableArrowWidth.setText("Width");
  lableArrowWidth.setOpaque(false);
  labelArrowScale = new GLabel(window1, 390, 240, 80, 20);
  labelArrowScale.setText("Scale");
  labelArrowScale.setOpaque(false);
  valArrowScale = new GTextField(window1, 390, 260, 80, 30, G4P.SCROLLBARS_NONE);
  valArrowScale.setOpaque(true);
  valArrowScale.addEventHandler(this, "valArrowScale_handler");
  valArrowWidth = new GTextField(window1, 470, 260, 80, 30, G4P.SCROLLBARS_NONE);
  valArrowWidth.setOpaque(true);
  valArrowWidth.addEventHandler(this, "valArrowWidth_handler");
  labelArrowTip = new GLabel(window1, 550, 240, 80, 20);
  labelArrowTip.setText("Tip");
  labelArrowTip.setOpaque(false);
  valArrowTip = new GTextField(window1, 550, 260, 80, 30, G4P.SCROLLBARS_NONE);
  valArrowTip.setOpaque(true);
  valArrowTip.addEventHandler(this, "valArrowTip_handler");
  labelArrowControl = new GLabel(window1, 390, 220, 240, 20);
  labelArrowControl.setText("Arrow control");
  labelArrowControl.setOpaque(false);
  labelXYZ = new GLabel(window1, 800, 240, 80, 20);
  labelXYZ.setText("XYZ");
  labelXYZ.setOpaque(false);
  labelValues = new GLabel(window1, 720, 240, 80, 20);
  labelValues.setText("Values");
  labelValues.setOpaque(false);
  labelDataScale = new GLabel(window1, 720, 220, 160, 20);
  labelDataScale.setText("Data scale");
  labelDataScale.setOpaque(false);
  valScaleValues = new GTextField(window1, 720, 260, 80, 30, G4P.SCROLLBARS_NONE);
  valScaleValues.setOpaque(true);
  valScaleValues.addEventHandler(this, "valScaleValues_handler");
  valScaleXYZ = new GTextField(window1, 800, 260, 80, 30, G4P.SCROLLBARS_NONE);
  valScaleXYZ.setOpaque(true);
  valScaleXYZ.addEventHandler(this, "valScaleXYZ_handler");
  GradientEditWindow = GWindow.getWindow(this, "Window title", 200, 200, 500, 200, JAVA2D);
  GradientEditWindow.setActionOnClose(G4P.CLOSE_WINDOW);
  GradientEditWindow.addDrawHandler(this, "win_draw1");
  padGradGradPrev = new GSketchPad(GradientEditWindow, 400, 10, 40, 180);
  listGradList = new GDropList(GradientEditWindow, 20, 20, 120, 80, 3);
  listGradList.setItems(loadStrings("list_798901"), 0);
  btnGradAdd = new GButton(GradientEditWindow, 160, 20, 90, 30);
  btnGradAdd.setText("Add point");
  btnGradAdd.addEventHandler(this, "btnGradAdd_handler");
  btnGradRemove = new GButton(GradientEditWindow, 280, 20, 80, 30);
  btnGradRemove.setText("Remove point");
  btnGradRemove.addEventHandler(this, "btnGradRemove_handler");
  btnGradClear = new GButton(GradientEditWindow, 220, 60, 90, 30);
  btnGradClear.setText("Clear");
  btnGradClear.addEventHandler(this, "btnGradClear_handler");
  padGradColorPrev = new GSketchPad(GradientEditWindow, 20, 50, 120, 40);
  valGradX = new GTextField(GradientEditWindow, 20, 110, 90, 30, G4P.SCROLLBARS_NONE);
  valGradX.setText("X");
  valGradX.setPromptText("X");
  valGradX.setOpaque(true);
  valGradY = new GTextField(GradientEditWindow, 150, 110, 90, 30, G4P.SCROLLBARS_NONE);
  valGradY.setText("Y");
  valGradY.setPromptText("Y");
  valGradY.setOpaque(true);
  valGradZ = new GTextField(GradientEditWindow, 280, 110, 90, 30, G4P.SCROLLBARS_NONE);
  valGradZ.setText("Z");
  valGradZ.setPromptText("Z");
  valGradZ.setOpaque(true);
  btnGradOK = new GButton(GradientEditWindow, 90, 160, 90, 30);
  btnGradOK.setText("OK");
  btnGradOK.setTextBold();
  btnGradOK.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  btnGradOK.addEventHandler(this, "btnGradOK_handler");
  btnGradCancel = new GButton(GradientEditWindow, 220, 160, 90, 30);
  btnGradCancel.setText("Cancel");
  btnGradCancel.setTextBold();
  btnGradCancel.setLocalColorScheme(GCScheme.RED_SCHEME);
  btnGradCancel.addEventHandler(this, "btnGradCancel_handler");
  window3 = GWindow.getWindow(this, "Window title", 0, 0, 240, 120, JAVA2D);
  window3.addDrawHandler(this, "win_draw3");
}

// Variable declarations 
// autogenerated do not edit
GWindow window1;
GButton btnAddFile; 
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
GDropList listFiles; 
GButton btnClearAll; 
GButton btnRemoveSelected; 
GTextField valFPS; 
GLabel labelFPS; 
GButton btnExport; 
GTextField valThreshold; 
GLabel labelThreshold; 
GLabel lableArrowWidth; 
GLabel labelArrowScale; 
GTextField valArrowScale; 
GTextField valArrowWidth; 
GLabel labelArrowTip; 
GTextField valArrowTip; 
GLabel labelArrowControl; 
GLabel labelXYZ; 
GLabel labelValues; 
GLabel labelDataScale; 
GTextField valScaleValues; 
GTextField valScaleXYZ; 
GWindow GradientEditWindow;
GSketchPad padGradGradPrev; 
GDropList listGradList; 
GButton btnGradAdd; 
GButton btnGradRemove; 
GButton btnGradClear; 
GSketchPad padGradColorPrev; 
GTextField valGradX; 
GTextField valGradY; 
GTextField valGradZ; 
GButton btnGradOK; 
GButton btnGradCancel; 
GWindow window3;