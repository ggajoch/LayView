package pl.gajoch.layview.videoExporter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoExporter {
    private JFrame frame;
    private String tmpPath;
    private String videoPath;

    private long frameNumber;
    private int FPS;

    private int width;
    private int height;

    private boolean is_active;

    public VideoExporter(JFrame frame, String tmpPath, String videoPath, int FPS, int width, int height) {
        this.frame = frame;
        this.tmpPath = tmpPath;
        this.videoPath = videoPath;
        this.FPS = FPS;
        this.width = width;
        this.height = height;

        reset();
    }

    private static BufferedImage getScreenShot(
            Component component) {

        BufferedImage image = new BufferedImage(
                component.getWidth(),
                component.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        // call the Component's paint method, using
        // the Graphics object of the image.
        component.paint(image.getGraphics()); // alternately use .printAll(..)
        return image;
    }

    private void cleanDir(File file) {
        if (file.isDirectory()) {
            for (File next : file.listFiles()) {
                deleteAllFiles(next);
            }
        }
    }

    private boolean deleteAllFiles(File file) {
        if (file.isDirectory()) {
            for (File next : file.listFiles()) {
                deleteAllFiles(next);
            }
        }
        return file.delete();
    }

    public void reset() {
        frameNumber = 0;
        is_active = true;
        File file = new File(tmpPath);
        file.mkdirs();
        cleanDir(file);
        /*File video = new File(videoPath);
        video.mkdirs();*/
    }

    public void saveSnapshot() {
        BufferedImage img = getScreenShot(
                frame.getContentPane());
        File file = new File(tmpPath + String.format("\\anim_%09d.png", frameNumber++));
        try {
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            //TODO: handle exception here
        }
    }

    public void closeVideo() {
        if(is_active) {
            is_active = false;
            ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i",
                    tmpPath + "\\anim_%09d.png", "-c:v", "libx264",
                    "-r", String.format("%d", FPS), "-y", "-an", "-pix_fmt", "yuv420p", videoPath);

            System.out.println(tmpPath);
            System.out.println(videoPath);
            try {
                Process p = processBuilder.start();
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("SAVED\r\n");
        }
    }
}
