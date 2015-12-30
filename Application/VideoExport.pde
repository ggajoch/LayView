class VideoExport{
  ProcessBuilder processBuilder;
  File folder;
  Process process;
  String outputFilePath;
  String inputFilePath;
  String actualFile;
  
  long frameNumber;
  
  VideoExport(){
    frameNumber = 0;
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
  
  void saveVideoFrame(){
    saveFrame(String.format("temp/anim_%05d.png", frameNumber++));
  }
  
  void closeVideo(){
    outputFilePath = new String(sketchPath("")+"test.mp4");
    inputFilePath = new String(sketchPath("")+"temp/anim_%05d.png");
    processBuilder = new ProcessBuilder(sketchPath("")+"ffmpeg/ffmpeg.exe", "-r", "60", "-i",
        inputFilePath, "-c:v", "libx264",
        "-r", "30", "-y", "-an", "-pix_fmt", "yuv420p", outputFilePath );
    try {
      process = processBuilder.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}