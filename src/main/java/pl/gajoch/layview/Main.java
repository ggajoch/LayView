package pl.gajoch.layview;

import java.util.Timer;
import java.util.TimerTask;

import pl.gajoch.layview.gui.GraphicsWindowManager;
import pl.gajoch.layview.scheduler.Scheduler;


public class Main {
    static Timer timer;
    public static void main(String[] args) {
        new GraphicsWindowManager();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                while(true) {
                    Scheduler.start();
                    //System.out.println("DONE");
                }
            }
        }, 100);
    }
}

