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

public void btnGradMax_handler(GButton source, GEvent event) { //_CODE_:btnGradMax:524538:
  println("btnGradMax - GButton >> GEvent." + event + " @ " + millis());
        gradients.maxHintCopy();
} //_CODE_:btnGradMax:524538:

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
        InputSelectorWindow.setVisible(false);
        showFiles(file_list.getListFile());
} //_CODE_:btnInputClose:675739:

synchronized public void DisplayOptionsWindow_draw(PApplet appc, GWinData data) { //_CODE_:DisplayOptionsWindow:225623:
  appc.background(230);
} //_CODE_:DisplayOptionsWindow:225623:

public void optDisplayTypeBox_handler(GOption source, GEvent event) { //_CODE_:optDisplayTypeBox:999994:
  println("optDisplayTypeBox - GOption >> GEvent." + event + " @ " + millis());
    display_options_manager.setVectors(false);
} //_CODE_:optDisplayTypeBox:999994:

public void optDisplayTypeVectors_handler(GOption source, GEvent event) { //_CODE_:optDisplayTypeVectors:890085:
  println("option1 - GOption >> GEvent." + event + " @ " + millis());
        display_options_manager.setVectors(true);
} //_CODE_:optDisplayTypeVectors:890085:

public void valDisplayThreshold_handler(GTextField source, GEvent event) { //_CODE_:valDisplayThreshold:229313:
  println("valDisplayThreshold - GTextField >> GEvent." + event + " @ " + millis());
  display_options_manager.setThreshold(Double.valueOf(source.getText()));
} //_CODE_:valDisplayThreshold:229313:

public void valDisplayScale_handler(GTextField source, GEvent event) { //_CODE_:valDisplayScale:563055:
  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
  display_options_manager.setArrowScale(Double.valueOf(source.getText()));
} //_CODE_:valDisplayScale:563055:

public void valDisplayWidth_handler(GTextField source, GEvent event) { //_CODE_:valDisplayWidth:446561:
  println("textfield2 - GTextField >> GEvent." + event + " @ " + millis());
        display_options_manager.setArrowWidth(Double.valueOf(source.getText()));
} //_CODE_:valDisplayWidth:446561:

public void valDisplayTip_handler(GTextField source, GEvent event) { //_CODE_:valDisplayTip:725029:
  println("textfield3 - GTextField >> GEvent." + event + " @ " + millis());
        display_options_manager.setArrowTip(Double.valueOf(source.getText()));
} //_CODE_:valDisplayTip:725029:

public void valDisplayValues_handler(GTextField source, GEvent event) { //_CODE_:valDisplayValues:471739:
  println("textfield4 - GTextField >> GEvent." + event + " @ " + millis());
        display_options_manager.setScaleValues(Double.valueOf(source.getText()));
} //_CODE_:valDisplayValues:471739:

public void valDisplayXYZ_handler(GTextField source, GEvent event) { //_CODE_:valDisplayXYZ:506724:
  println("textfield5 - GTextField >> GEvent." + event + " @ " + millis());
        display_options_manager.setScaleXYZ(Double.valueOf(source.getText()));
} //_CODE_:valDisplayXYZ:506724:

public void btnDisplayClose_handler(GButton source, GEvent event) { //_CODE_:btnDisplayClose:715831:
  println("btnDisplayClose - GButton >> GEvent." + event + " @ " + millis());
        DisplayOptionsWindow.setVisible(false);
} //_CODE_:btnDisplayClose:715831:

synchronized public void VideoControlWindow_draw(PApplet appc, GWinData data) { //_CODE_:VideoControlWindow:840922:
  appc.background(230);
} //_CODE_:VideoControlWindow:840922:

public void valVideoFPS_handler(GTextField source, GEvent event) { //_CODE_:valVideoFPS:260387:
  println("valVideoFPS - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:valVideoFPS:260387:

public void btnVideoPlay_handler(GImageButton source, GEvent event) { //_CODE_:btnVideoPlay:558393:
  println("btnVideoPlay - GImageButton >> GEvent." + event + " @ " + millis());
  play = 1;
} //_CODE_:btnVideoPlay:558393:

public void btnVideoPause_handler(GImageButton source, GEvent event) { //_CODE_:btnVideoPause:668308:
  println("btnVideoPause - GImageButton >> GEvent." + event + " @ " + millis());
  play = 0;
} //_CODE_:btnVideoPause:668308:

public void btnVideoExport_handler(GButton source, GEvent event) { //_CODE_:btnVideoExport:760889:
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
  String file_name = G4P.selectInput("Input Dialog", "mp4", "OMF file select");
  export = new VideoExport(file_name+".mp4");
  export.cleanFolder();
  record = 2;
  // coś tutaj?
  //może policzyć całkę?
  
} //_CODE_:btnVideoExport:760889:

synchronized public void MainWindow_draw(PApplet appc, GWinData data) { //_CODE_:MainWindow:899620:
  appc.background(230);
} //_CODE_:MainWindow:899620:

public void btnMainFileInput_handler(GButton source, GEvent event) { //_CODE_:btnMainFileInput:874382:
  println("btnMainFileInput - GButton >> GEvent." + event + " @ " + millis());
        InputSelectorWindow.setVisible(true);
} //_CODE_:btnMainFileInput:874382:

public void btnMainDisplayOption_handler(GButton source, GEvent event) { //_CODE_:btnMainDisplayOption:878656:
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
        DisplayOptionsWindow.setVisible(true);
} //_CODE_:btnMainDisplayOption:878656:

public void btnMainVideo_handler(GButton source, GEvent event) { //_CODE_:btnMainVideo:347040:
  println("btnMainVideo - GButton >> GEvent." + event + " @ " + millis());
        VideoControlWindow.setVisible(true);
} //_CODE_:btnMainVideo:347040:

public void btnMainGradient1_handler(GButton source, GEvent event) { //_CODE_:btnMainGradient1:574437:
  println("btnMainGradient1 - GButton >> GEvent." + event + " @ " + millis());
        gradients.edit(0);
} //_CODE_:btnMainGradient1:574437:

public void btnMainGradient2_handler(GButton source, GEvent event) { //_CODE_:btnMainGradient2:361852:
  println("btnMainGradient2 - GButton >> GEvent." + event + " @ " + millis());
        gradients.edit(1);
} //_CODE_:btnMainGradient2:361852:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(true);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setCursor(ARROW);
  surface.setTitle("Sketch Window");
  GradientEditWindow = GWindow.getWindow(this, "Gradient Edit", 200, 200, 500, 300, JAVA2D);
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
  btnGradOK = new GButton(GradientEditWindow, 80, 210, 90, 30);
  btnGradOK.setText("OK");
  btnGradOK.setTextBold();
  btnGradOK.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  btnGradOK.addEventHandler(this, "btnGradOK_handler");
  btnGradCancel = new GButton(GradientEditWindow, 220, 210, 90, 30);
  btnGradCancel.setText("Cancel");
  btnGradCancel.setTextBold();
  btnGradCancel.setLocalColorScheme(GCScheme.RED_SCHEME);
  btnGradCancel.addEventHandler(this, "btnGradCancel_handler");
  btnGradEdit = new GButton(GradientEditWindow, 160, 60, 90, 30);
  btnGradEdit.setText("Edit");
  btnGradEdit.addEventHandler(this, "btnGradEdit_handler");
  valGradMax = new GTextField(GradientEditWindow, 280, 160, 90, 30, G4P.SCROLLBARS_NONE);
  valGradMax.setOpaque(true);
  labelGradMax = new GLabel(GradientEditWindow, 20, 160, 90, 30);
  labelGradMax.setText("Max");
  labelGradMax.setOpaque(false);
  btnGradMax = new GButton(GradientEditWindow, 150, 160, 90, 30);
  btnGradMax.addEventHandler(this, "btnGradMax_handler");
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
  InputSelectorWindow = GWindow.getWindow(this, "Window title", 200, 200, 700, 170, JAVA2D);
  InputSelectorWindow.addDrawHandler(this, "InputSelectorWindow_draw");
  listInputListFiles = new GDropList(InputSelectorWindow, 20, 20, 660, 120, 3);
  listInputListFiles.setItems(loadStrings("list_748167"), 0);
  btnInputAddFile = new GButton(InputSelectorWindow, 20, 70, 80, 30);
  btnInputAddFile.setText("Add File");
  btnInputAddFile.addEventHandler(this, "btnInputAddFile_handler");
  btnInputClearAll = new GButton(InputSelectorWindow, 600, 70, 80, 30);
  btnInputClearAll.setText("Clear all");
  btnInputClearAll.setLocalColorScheme(GCScheme.RED_SCHEME);
  btnInputClearAll.addEventHandler(this, "btnInputClearAll_handler");
  btnInputRemove = new GButton(InputSelectorWindow, 500, 70, 80, 30);
  btnInputRemove.setText("Remove selected");
  btnInputRemove.setLocalColorScheme(GCScheme.ORANGE_SCHEME);
  btnInputRemove.addEventHandler(this, "btnInputRemove_handler");
  btnInputAddFolder = new GButton(InputSelectorWindow, 120, 70, 80, 30);
  btnInputAddFolder.setText("Add folder");
  btnInputAddFolder.addEventHandler(this, "btnInputAddFolder_handler");
  btnInputClose = new GButton(InputSelectorWindow, 210, 120, 280, 30);
  btnInputClose.setText("Close");
  btnInputClose.setTextBold();
  btnInputClose.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  btnInputClose.addEventHandler(this, "btnInputClose_handler");
  DisplayOptionsWindow = GWindow.getWindow(this, "Display Options", 200, 200, 500, 500, JAVA2D);
  DisplayOptionsWindow.addDrawHandler(this, "DisplayOptionsWindow_draw");
  groupDisplayDisplayType = new GToggleGroup();
  optDisplayTypeBox = new GOption(DisplayOptionsWindow, 360, 20, 120, 20);
  optDisplayTypeBox.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  optDisplayTypeBox.setText("Box");
  optDisplayTypeBox.setOpaque(false);
  optDisplayTypeBox.addEventHandler(this, "optDisplayTypeBox_handler");
  optDisplayTypeVectors = new GOption(DisplayOptionsWindow, 360, 40, 120, 20);
  optDisplayTypeVectors.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
  optDisplayTypeVectors.setText("Vectors");
  optDisplayTypeVectors.setOpaque(false);
  optDisplayTypeVectors.addEventHandler(this, "optDisplayTypeVectors_handler");
  groupDisplayDisplayType.addControl(optDisplayTypeBox);
  optDisplayTypeBox.setSelected(true);
  groupDisplayDisplayType.addControl(optDisplayTypeVectors);
  labelDisplayThreshold = new GLabel(DisplayOptionsWindow, 190, 20, 120, 20);
  labelDisplayThreshold.setText("Display threshold");
  labelDisplayThreshold.setOpaque(false);
  valDisplayThreshold = new GTextField(DisplayOptionsWindow, 190, 40, 120, 30, G4P.SCROLLBARS_NONE);
  valDisplayThreshold.setOpaque(true);
  valDisplayThreshold.addEventHandler(this, "valDisplayThreshold_handler");
  labelDisplayScale = new GLabel(DisplayOptionsWindow, 50, 220, 80, 20);
  labelDisplayScale.setText("Scale");
  labelDisplayScale.setOpaque(false);
  valDisplayScale = new GTextField(DisplayOptionsWindow, 10, 250, 160, 30, G4P.SCROLLBARS_NONE);
  valDisplayScale.setOpaque(true);
  valDisplayScale.addEventHandler(this, "valDisplayScale_handler");
  valDisplayWidth = new GTextField(DisplayOptionsWindow, 170, 250, 160, 30, G4P.SCROLLBARS_NONE);
  valDisplayWidth.setOpaque(true);
  valDisplayWidth.addEventHandler(this, "valDisplayWidth_handler");
  labelDisplayWidth = new GLabel(DisplayOptionsWindow, 200, 220, 80, 20);
  labelDisplayWidth.setText("Width");
  labelDisplayWidth.setOpaque(false);
  valDisplayTip = new GTextField(DisplayOptionsWindow, 330, 250, 160, 30, G4P.SCROLLBARS_NONE);
  valDisplayTip.setOpaque(true);
  valDisplayTip.addEventHandler(this, "valDisplayTip_handler");
  labelDisplayTip = new GLabel(DisplayOptionsWindow, 370, 220, 80, 20);
  labelDisplayTip.setText("Tip");
  labelDisplayTip.setOpaque(false);
  labelDisplayValues = new GLabel(DisplayOptionsWindow, 120, 330, 80, 20);
  labelDisplayValues.setText("Values");
  labelDisplayValues.setOpaque(false);
  valDisplayValues = new GTextField(DisplayOptionsWindow, 90, 350, 160, 30, G4P.SCROLLBARS_NONE);
  valDisplayValues.setOpaque(true);
  valDisplayValues.addEventHandler(this, "valDisplayValues_handler");
  valDisplayXYZ = new GTextField(DisplayOptionsWindow, 250, 350, 160, 30, G4P.SCROLLBARS_NONE);
  valDisplayXYZ.setOpaque(true);
  valDisplayXYZ.addEventHandler(this, "valDisplayXYZ_handler");
  labelDisplayXYZ = new GLabel(DisplayOptionsWindow, 290, 330, 80, 20);
  labelDisplayXYZ.setText("XYZ");
  labelDisplayXYZ.setOpaque(false);
  btnDisplayClose = new GButton(DisplayOptionsWindow, 90, 420, 320, 30);
  btnDisplayClose.setText("Close");
  btnDisplayClose.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  btnDisplayClose.addEventHandler(this, "btnDisplayClose_handler");
  VideoControlWindow = GWindow.getWindow(this, "Video Control", 200, 200, 500, 500, JAVA2D);
  VideoControlWindow.addDrawHandler(this, "VideoControlWindow_draw");
  labelVideoFPS = new GLabel(VideoControlWindow, 20, 20, 80, 20);
  labelVideoFPS.setText("FPS");
  labelVideoFPS.setOpaque(false);
  valVideoFPS = new GTextField(VideoControlWindow, 20, 40, 80, 20, G4P.SCROLLBARS_NONE);
  valVideoFPS.setOpaque(true);
  valVideoFPS.addEventHandler(this, "valVideoFPS_handler");
  btnVideoPlay = new GImageButton(VideoControlWindow, 22, 79, 70, 70, new String[] { "play.png", "play.png", "play.png" } );
  btnVideoPlay.addEventHandler(this, "btnVideoPlay_handler");
  btnVideoPause = new GImageButton(VideoControlWindow, 100, 80, 70, 70, new String[] { "pause.png", "pause.png", "pause.png" } );
  btnVideoPause.addEventHandler(this, "btnVideoPause_handler");
  btnVideoExport = new GButton(VideoControlWindow, 210, 20, 80, 60);
  btnVideoExport.setText("Export");
  btnVideoExport.addEventHandler(this, "btnVideoExport_handler");
  MainWindow = GWindow.getWindow(this, "OMF Viewer", 200, 200, 500, 120, JAVA2D);
  MainWindow.setActionOnClose(G4P.EXIT_APP);
  MainWindow.addDrawHandler(this, "MainWindow_draw");
  btnMainFileInput = new GButton(MainWindow, 20, 20, 80, 30);
  btnMainFileInput.setText("File input");
  btnMainFileInput.addEventHandler(this, "btnMainFileInput_handler");
  btnMainDisplayOption = new GButton(MainWindow, 120, 20, 80, 30);
  btnMainDisplayOption.setText("Display options");
  btnMainDisplayOption.addEventHandler(this, "btnMainDisplayOption_handler");
  btnMainVideo = new GButton(MainWindow, 220, 20, 80, 30);
  btnMainVideo.setText("Video");
  btnMainVideo.addEventHandler(this, "btnMainVideo_handler");
  btnMainGradient1 = new GButton(MainWindow, 390, 20, 80, 30);
  btnMainGradient1.setText("Gradient 1");
  btnMainGradient1.addEventHandler(this, "btnMainGradient1_handler");
  btnMainGradient2 = new GButton(MainWindow, 390, 70, 80, 30);
  btnMainGradient2.setText("Gradient 2");
  btnMainGradient2.addEventHandler(this, "btnMainGradient2_handler");
}

// Variable declarations 
// autogenerated do not edit
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
GTextField valGradMax; 
GLabel labelGradMax; 
GButton btnGradMax; 
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
GLabel labelDisplayScale; 
GTextField valDisplayScale; 
GTextField valDisplayWidth; 
GLabel labelDisplayWidth; 
GTextField valDisplayTip; 
GLabel labelDisplayTip; 
GLabel labelDisplayValues; 
GTextField valDisplayValues; 
GTextField valDisplayXYZ; 
GLabel labelDisplayXYZ; 
GButton btnDisplayClose; 
GWindow VideoControlWindow;
GLabel labelVideoFPS; 
GTextField valVideoFPS; 
GImageButton btnVideoPlay; 
GImageButton btnVideoPause; 
GButton btnVideoExport; 
GWindow MainWindow;
GButton btnMainFileInput; 
GButton btnMainDisplayOption; 
GButton btnMainVideo; 
GButton btnMainGradient1; 
GButton btnMainGradient2; 