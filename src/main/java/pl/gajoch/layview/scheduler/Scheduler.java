package pl.gajoch.layview.scheduler;

import java.util.*;

public class Scheduler {
    private static final PriorityQueue<Event> queue = new PriorityQueue<>();

    public static void start() {

        long prev_time = 0;

        while( ! queue.isEmpty() ) {
            Event now = queue.poll();
            long next = micros() + now.offset_us - prev_time;
            while (micros() < next) {
            }
            prev_time = now.offset_us;
            now.dispatchHandler();
        }
    }

    public static void schedule(Event event) {
        queue.add(event);
    }

    private static long micros() {
        return (System.nanoTime()/1000);
    }
}
