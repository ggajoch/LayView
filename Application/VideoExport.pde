class VideoExport{
  ProcessBuilder processBuilder;
  File folder;
  Process process;
  String outputFilePath;
  String inputFilePath;
  String actualFile;
  
  long frameNumber;
  
  int FPS;
  
  
  
  VideoExport(){
    frameNumber = 0;
    outputFilePath = sketchPath("")+"test.mp4";
    inputFilePath = sketchPath("")+"temp/anim_%05d.png";
    FPS = new Integer(30);
  }
  
  VideoExport(String fileName){
    frameNumber = 0;
    outputFilePath = fileName;
    inputFilePath = sketchPath("")+"temp/anim_%05d.png";
    FPS = 30;
  }
  
  private boolean deleteAllFiles(File file) {
    if( file.isDirectory() ) {
      for(File next : file.listFiles()) {
        deleteAllFiles(next);
      }
    }
    return file.delete();
  }
  
  boolean cleanFolder(){
    folder = new File(sketchPath("") + "temp");
    return deleteAllFiles(folder);
  }
  
  void setFPS(int FPS_){
    FPS = FPS_;
  }
  
  void saveVideoFrame(){
    saveFrame(String.format("temp/anim_%05d.png", frameNumber++));
  }
  
  void closeVideo(){
    processBuilder = new ProcessBuilder(sketchPath("")+"ffmpeg/ffmpeg.exe", "-i",
        inputFilePath, "-c:v", "libx264",
        "-r", String.format("%d",FPS), "-y", "-an", "-pix_fmt", "yuv420p", outputFilePath );
    try {
      process = processBuilder.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}