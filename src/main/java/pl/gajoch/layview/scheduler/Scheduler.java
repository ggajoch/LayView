package pl.gajoch.layview.scheduler;

import java.util.*;

public class Scheduler {
    public SortedSet<Event> set;

    public Scheduler() {
        set = new TreeSet<>();
    }

    public void dispatchAll() {
        Iterator it = set.iterator();

        Event prev = set.first();
        long next = micros() + prev.offset_us;
        while (micros() < next);
        prev.dispatch();
        while(it.hasNext()) {
            Event now = (Event) it.next();
            next = micros() + now.offset_us - prev.offset_us;
            while (micros() < next);
            now.dispatch();
            prev = now;
        }
    }

    private long micros() {
        return (System.nanoTime()/1000);
    }
}
