package pl.gajoch.layview.scheduler;

import java.util.*;

public class Scheduler {
    private static final PriorityQueue<Event> queue = new PriorityQueue<>();
    private static final PriorityQueue<Event> queueCopy = new PriorityQueue<>();

    private static Object mutex = new Object();

    private Scheduler() {
    }

    public static void start() {
        synchronized (mutex) {
            queueCopy.clear();
            queueCopy.addAll(queue);

            for (Event event : queue) {
                event.resetHandler();
            }
        }

        long start = micros();
        while (!queueCopy.isEmpty()) {
            Event now;
            long next;
            synchronized (mutex) {
                now = queueCopy.peek();
                next = start + now.offset_actual;
            }
            while (micros() < next) {
            }
            synchronized (mutex) {
                now.dispatchHandler();
                queueCopy.remove();
            }
        }
    }

    public static void remove(Event event) {
        synchronized (mutex) {
            queue.remove(event);
        }
    }

    static Boolean isRunning() {
        synchronized (mutex) {
            return queueCopy.isEmpty();
        }
    }

    public static void schedule(Event event) {
        synchronized (mutex) {
            queue.add(event);
        }
    }

    public static void __scheduleCurrentRun(Event event) {
        synchronized (mutex) {
            queueCopy.add(event);
        }
    }

    private static long micros() {
        return (System.nanoTime() / 1000);
    }
}
