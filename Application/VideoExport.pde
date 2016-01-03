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
    outputFilePath = new String(sketchPath("")+"test.mp4");
    inputFilePath = new String(sketchPath("")+"temp/anim_%05d.png");
    FPS = new Integer(30);
  }
  
  VideoExport(String fileName){
    frameNumber = 0;
    outputFilePath = new String(fileName);
    inputFilePath = new String(sketchPath("")+"temp/anim_%05d.png");
    FPS = new Integer(30);
  }
  
  private boolean deleteDirectory(File directory) {
    if(directory.exists()){
        File[] files = directory.listFiles();
        if(null!=files){
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
    }
    return(directory.delete());
  }
  
  boolean cleanFolder(){
    folder = new File(sketchPath("")+"temp");
    return deleteDirectory(folder);
  }
  
  void setFPS(int FPS_){
    FPS = new Integer(FPS_);
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