package pl.gajoch.layview;

import java.util.Timer;
import java.util.TimerTask;

import pl.gajoch.layview.gui.GraphicsWindowManager;
import pl.gajoch.layview.scheduler.Scheduler;


public class Main {
    static Timer timer;
    public static void main(String[] args) {
        new GraphicsWindowManager();
    }
}

