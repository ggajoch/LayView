package pl.gajoch.layview.videoExporter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SubScene;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class VideoExporter {
    private SubScene scene;
    private String tmpPath;
    private String videoPath;

    private long frameNumber;
    private int FPS;

    private int width;
    private int height;

    public VideoExporter(SubScene scene, String tmpPath, String videoPath, int FPS, int width, int height){
        this.scene = scene;
        this.tmpPath = tmpPath;
        this.videoPath = videoPath;
        this.FPS = FPS;
        this.width = width;
        this.height = height;

        reset();
    }

    private void cleanDir(File file){
        if( file.isDirectory() ) {
            for(File next : file.listFiles()) {
                deleteAllFiles(next);
            }
        }
    }

    private boolean deleteAllFiles(File file) {
        if( file.isDirectory() ) {
            for(File next : file.listFiles()) {
                deleteAllFiles(next);
            }
        }
        return file.delete();
    }

    public void reset(){
        frameNumber = 0;
        cleanDir(new File(tmpPath));
    }

    public void saveSnapshot(){
        WritableImage image = new WritableImage(width, height);
        scene.snapshot(null,image);
        File file = new File(tmpPath+String.format("\\anim_%09d.png",frameNumber++));
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }catch (IOException e) {
            //TODO: handle exception here
        }
    }

    public void closeVideo() {
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i",
                tmpPath+"\\anim_%09d.png", "-c:v", "libx264",
                "-r", String.format("%d",FPS), "-y", "-an", "-pix_fmt", "yuv420p", videoPath );
        try {
            processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("SAVED\r\n");
    }
}
