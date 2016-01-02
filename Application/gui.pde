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

synchronized public void GradientEditWindow_draw(PApplet appc, GWinData data) { //_CODE_:GradientEditWindow:607983:
  appc.background(230);
} //_CODE_:GradientEditWindow:607983:

public void listGradList_handler(GDropList source, GEvent event) { //_CODE_:listGradList:798901:
  println("listGradList - GDropList >> GEvent." + event + " @ " + millis());
  gradients.colorUpdate();
} //_CODE_:listGradList:798901:

public void btnGradAdd_handler(GButton source, GEvent event) { //_CODE_:btnGradAdd:714538:
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
  gradients.addPoint();
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

public void btnGradEdit_handler(GButton source, GEvent event) { //_CODE_:btnGradEdit:448885:
  println("btnGradEdit - GButton >> GEvent." + event + " @ " + millis());
gradients.editPoint();
} //_CODE_:btnGradEdit:448885:

synchronized public void GradientPointEditWindowDraw(PApplet appc, GWinData data) { //_CODE_:GradientPointEditWindow:640294:
  appc.background(230);
} //_CODE_:GradientPointEditWindow:640294:

public void btnGradEditOK_handler(GButton source, GEvent event) { //_CODE_:btnGradEditOK:586455:
  println("button3 - GButton >> GEvent." + event + " @ " + millis());
  gradients.gradient_point_editor.OK_handler();
} //_CODE_:btnGradEditOK:586455:

public void btnGradEditCancel_handler(GButton source, GEvent event) { //_CODE_:btnGradEditCancel:871784:
  println("button4 - GButton >> GEvent." + event + " @ " + millis());
  gradients.gradient_point_editor.Cancel_handler();
} //_CODE_:btnGradEditCancel:871784:

public void btnGradEditColor_handler(GButton source, GEvent event) { //_CODE_:btnGradEditColor:672090:
  println("btnGradEditColor - GButton >> GEvent." + event + " @ " + millis());
  gradients.gradient_point_editor.Color_handler();
} //_CODE_:btnGradEditColor:672090:

synchronized public void InputSelectorWindow_draw(PApplet appc, GWinData data) { //_CODE_:InputSelectorWindow:417565:
  appc.background(230);
} //_CODE_:InputSelectorWindow:417565:

public void btnInputAddFile_handler(GButton source, GEvent event) { //_CODE_:btnInputAddFile:299318:
  println("btnInputAddFile - GButton >> GEvent." + event + " @ " + millis());
        String name = G4P.selectInput("Input Dialog", "txt", "OMF file select");
        file_list.add(new File(name));
} //_CODE_:btnInputAddFile:299318:

public void btnInputClearAll_handler(GButton source, GEvent event) { //_CODE_:btnInputClearAll:840953:
  println("btnInputClearAll - GButton >> GEvent." + event + " @ " + millis());
        file_list.clear();
} //_CODE_:btnInputClearAll:840953:

public void btnInputRemove_handler(GButton source, GEvent event) { //_CODE_:btnInputRemove:495293:
  println("btnInputRemove - GButton >> GEvent." + event + " @ " + millis());
        file_list.remove();
} //_CODE_:btnInputRemove:495293:

public void btnInputAddFolder_handler(GButton source, GEvent event) { //_CODE_:btnInputAddFolder:844648:
  println("btnInputAddFolder - GButton >> GEvent." + event + " @ " + millis());
        String name = G4P.selectFolder("Select folder to load");
        file_list.addFolder(name);
} //_CODE_:btnInputAddFolder:844648:

public void btnInputClose_handler(GButton source, GEvent event) { //_CODE_:btnInputClose:675739:
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
} //_CODE_:btnInputClose:675739:

synchronized public void DisplayOptionsWindow_draw(PApplet appc, GWinData data) { //_CODE_:DisplayOptionsWindow:225623:
  appc.background(230);
} //_CODE_:DisplayOptionsWindow:225623:

public void optDisplayTypeBox_handler(GOption source, GEvent event) { //_CODE_:optDisplayTypeBox:999994:
  println("optDisplayTypeBox - GOption >> GEvent." + event + " @ " + millis());
        vectors = 0;
} //_CODE_:optDisplayTypeBox:999994:

public void optDisplayTypeVectors_handler(GOption source, GEvent event) { //_CODE_:optDisplayTypeVectors:890085:
  println("option1 - GOption >> GEvent." + event + " @ " + millis());
} //_CODE_:optDisplayTypeVectors:890085:

public void valDisplayThreshold_handler(GTextField source, GEvent event) { //_CODE_:valDisplayThreshold:229313:
  println("valDisplayThreshold - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valDisplayThreshold:229313:

synchronized public void VideoControlWindow_draw(PApplet appc, GWinData data) { //_CODE_:VideoControlWindow:840922:
  appc.background(230);
} //_CODE_:VideoControlWindow:840922:

public void valVideoFPS_handler(GTextField source, GEvent event) { //_CODE_:valVideoFPS:260387:
  println("valVideoFPS - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valVideoFPS:260387:

public void valVideoThreshold_handler(GTextField source, GEvent event) { //_CODE_:valVideoThreshold:214656:
  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valVideoThreshold:214656:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(true);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setCursor(ARROW);
  surface.setTitle("Sketch Window");
  window1 = GWindow.getWindow(this, "KEEP_OPEN", 200, 200, 1000, 1000, JAVA2D);
  window1.setActionOnClose(G4P.EXIT_APP);
  window1.addDrawHandler(this, "win_draw2");
  togGroup1 = new GToggleGroup();
  btnPlay = new GImageButton(window1, 20, 250, 50, 50, new String[] { "play.png", "play.png", "play.png" } );
  btnPlay.addEventHandler(this, "btnPlay_handler");
  btnPause = new GImageButton(window1, 80, 250, 50, 50, new String[] { "pause.png", "pause.png", "pause.png" } );
  btnPause.addEventHandler(this, "btnPause_handler");
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
  GradientEditWindow = GWindow.getWindow(this, "Gradient Edit", 200, 200, 500, 200, JAVA2D);
  GradientEditWindow.setActionOnClose(G4P.CLOSE_WINDOW);
  GradientEditWindow.addDrawHandler(this, "GradientEditWindow_draw");
  padGradGradPrev = new GSketchPad(GradientEditWindow, 400, 10, 40, 180);
  listGradList = new GDropList(GradientEditWindow, 20, 20, 120, 80, 3);
  listGradList.setItems(loadStrings("list_798901"), 0);
  listGradList.addEventHandler(this, "listGradList_handler");
  btnGradAdd = new GButton(GradientEditWindow, 160, 20, 90, 30);
  btnGradAdd.setText("Add point");
  btnGradAdd.addEventHandler(this, "btnGradAdd_handler");
  btnGradRemove = new GButton(GradientEditWindow, 280, 20, 80, 30);
  btnGradRemove.setText("Remove point");
  btnGradRemove.addEventHandler(this, "btnGradRemove_handler");
  btnGradClear = new GButton(GradientEditWindow, 280, 60, 80, 30);
  btnGradClear.setText("Clear");
  btnGradClear.setLocalColorScheme(GCScheme.ORANGE_SCHEME);
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
  btnGradEdit = new GButton(GradientEditWindow, 160, 60, 90, 30);
  btnGradEdit.setText("Edit");
  btnGradEdit.addEventHandler(this, "btnGradEdit_handler");
  GradientPointEditWindow = GWindow.getWindow(this, "Gradient Point Edit", 200, 200, 220, 120, JAVA2D);
  GradientPointEditWindow.addDrawHandler(this, "GradientPointEditWindowDraw");
  btnGradEditOK = new GButton(GradientPointEditWindow, 20, 70, 80, 30);
  btnGradEditOK.setText("OK");
  btnGradEditOK.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  btnGradEditOK.addEventHandler(this, "btnGradEditOK_handler");
  btnGradEditCancel = new GButton(GradientPointEditWindow, 120, 70, 80, 30);
  btnGradEditCancel.setText("Cancel");
  btnGradEditCancel.setLocalColorScheme(GCScheme.RED_SCHEME);
  btnGradEditCancel.addEventHandler(this, "btnGradEditCancel_handler");
  valGradEditValue = new GTextField(GradientPointEditWindow, 20, 20, 80, 30, G4P.SCROLLBARS_NONE);
  valGradEditValue.setText("value");
  valGradEditValue.setOpaque(true);
  btnGradEditColor = new GButton(GradientPointEditWindow, 120, 20, 80, 30);
  btnGradEditColor.setText("Select Color");
  btnGradEditColor.addEventHandler(this, "btnGradEditColor_handler");
  InputSelectorWindow = GWindow.getWindow(this, "Window title", 0, 0, 320, 170, JAVA2D);
  InputSelectorWindow.addDrawHandler(this, "InputSelectorWindow_draw");
  listInputListFiles = new GDropList(InputSelectorWindow, 20, 20, 180, 120, 3);
  listInputListFiles.setItems(loadStrings("list_748167"), 0);
  btnInputAddFile = new GButton(InputSelectorWindow, 220, 20, 80, 30);
  btnInputAddFile.setText("Add File");
  btnInputAddFile.addEventHandler(this, "btnInputAddFile_handler");
  btnInputClearAll = new GButton(InputSelectorWindow, 20, 70, 80, 30);
  btnInputClearAll.setText("Clear all");
  btnInputClearAll.addEventHandler(this, "btnInputClearAll_handler");
  btnInputRemove = new GButton(InputSelectorWindow, 120, 70, 80, 30);
  btnInputRemove.setText("Remove selected");
  btnInputRemove.addEventHandler(this, "btnInputRemove_handler");
  btnInputAddFolder = new GButton(InputSelectorWindow, 220, 70, 80, 30);
  btnInputAddFolder.setText("Add folder");
  btnInputAddFolder.addEventHandler(this, "btnInputAddFolder_handler");
  btnInputClose = new GButton(InputSelectorWindow, 20, 120, 280, 30);
  btnInputClose.setText("Close");
  btnInputClose.setTextBold();
  btnInputClose.addEventHandler(this, "btnInputClose_handler");
  DisplayOptionsWindow = GWindow.getWindow(this, "Display Options", 0, 0, 500, 500, JAVA2D);
  DisplayOptionsWindow.addDrawHandler(this, "DisplayOptionsWindow_draw");
  groupDisplayDisplayType = new GToggleGroup();
  optDisplayTypeBox = new GOption(DisplayOptionsWindow, 190, 40, 120, 20);
  optDisplayTypeBox.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  optDisplayTypeBox.setText("Box");
  optDisplayTypeBox.setOpaque(false);
  optDisplayTypeBox.addEventHandler(this, "optDisplayTypeBox_handler");
  optDisplayTypeVectors = new GOption(DisplayOptionsWindow, 190, 60, 120, 20);
  optDisplayTypeVectors.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  optDisplayTypeVectors.setText("Vectors");
  optDisplayTypeVectors.setOpaque(false);
  optDisplayTypeVectors.addEventHandler(this, "optDisplayTypeVectors_handler");
  groupDisplayDisplayType.addControl(optDisplayTypeBox);
  optDisplayTypeBox.setSelected(true);
  groupDisplayDisplayType.addControl(optDisplayTypeVectors);
  labelDisplayThreshold = new GLabel(DisplayOptionsWindow, 190, 100, 120, 20);
  labelDisplayThreshold.setText("Display threshold");
  labelDisplayThreshold.setOpaque(false);
  valDisplayThreshold = new GTextField(DisplayOptionsWindow, 190, 120, 120, 30, G4P.SCROLLBARS_NONE);
  valDisplayThreshold.setOpaque(true);
  valDisplayThreshold.addEventHandler(this, "valDisplayThreshold_handler");
  VideoControlWindow = GWindow.getWindow(this, "Video Control", 0, 0, 500, 500, JAVA2D);
  VideoControlWindow.addDrawHandler(this, "VideoControlWindow_draw");
  labelVideoFPS = new GLabel(VideoControlWindow, 20, 20, 80, 20);
  labelVideoFPS.setText("FPS");
  labelVideoFPS.setOpaque(false);
  valVideoFPS = new GTextField(VideoControlWindow, 20, 40, 80, 20, G4P.SCROLLBARS_NONE);
  valVideoFPS.setOpaque(true);
  valVideoFPS.addEventHandler(this, "valVideoFPS_handler");
  valVideoThreshold = new GTextField(VideoControlWindow, 120, 40, 80, 20, G4P.SCROLLBARS_NONE);
  valVideoThreshold.setOpaque(true);
  valVideoThreshold.addEventHandler(this, "valVideoThreshold_handler");
  labelVideoThreshold = new GLabel(VideoControlWindow, 120, 20, 80, 20);
  labelVideoThreshold.setText("Threshold");
  labelVideoThreshold.setOpaque(false);
}

// Variable declarations 
// autogenerated do not edit
GWindow window1;
GToggleGroup togGroup1; 
GImageButton btnPlay; 
GImageButton btnPause; 
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
GButton btnGradEdit; 
GWindow GradientPointEditWindow;
GButton btnGradEditOK; 
GButton btnGradEditCancel; 
GTextField valGradEditValue; 
GButton btnGradEditColor; 
GWindow InputSelectorWindow;
GDropList listInputListFiles; 
GButton btnInputAddFile; 
GButton btnInputClearAll; 
GButton btnInputRemove; 
GButton btnInputAddFolder; 
GButton btnInputClose; 
GWindow DisplayOptionsWindow;
GToggleGroup groupDisplayDisplayType; 
GOption optDisplayTypeBox; 
GOption optDisplayTypeVectors; 
GLabel labelDisplayThreshold; 
GTextField valDisplayThreshold; 
GWindow VideoControlWindow;
GLabel labelVideoFPS; 
GTextField valVideoFPS; 
GTextField valVideoThreshold; 
GLabel labelVideoThreshold; 