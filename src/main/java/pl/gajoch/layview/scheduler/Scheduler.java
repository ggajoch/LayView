package pl.gajoch.layview.scheduler;

import java.util.*;

public class Scheduler {
    private static final PriorityQueue<Event> queue = new PriorityQueue<>(),
            queueCopy = new PriorityQueue<>();

    private Scheduler() {}

    public static void start() {
        queueCopy.clear();
        queueCopy.addAll(queue);

        for(Event event : queue) {
            event.resetHandler();
        }

        long start = micros();
        while( ! queueCopy.isEmpty() ) {
            Event now = queueCopy.peek();
            long next = start + now.offset_actual;
            while (micros() < next) {
            }
            now.dispatchHandler();
            queueCopy.remove();
        }
    }

    static Boolean isRunning() {
        return queueCopy.isEmpty();
    }

    public static void schedule(Event event) {
        queue.add(event);
    }

    public static void __scheduleCurrentRun(Event event) {
        queueCopy.add(event);
    }

    private static long micros() {
        return (System.nanoTime()/1000);
    }
}
