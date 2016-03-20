package pl.gajoch.layview.scheduler;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Scheduler {
    private static final PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
    private static final PriorityBlockingQueue<Event> queueCopy = new PriorityBlockingQueue<>();

    private Scheduler() {
    }

    public static void start() {
        queueCopy.clear();
        queueCopy.addAll(queue);

        for (Event event : queue) {
            event.resetHandler();
        }

        long start = micros();
        while (!queueCopy.isEmpty()) {
            Event now;
            long next;
            now = queueCopy.peek();
            next = start + now.offset_actual;
            while (micros() < next) {
            }
            now.dispatchHandler();
            queueCopy.remove();
        }
    }

    public static void remove(Event event) {
        queue.remove(event);
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
        return (System.nanoTime() / 1000);
    }
}
