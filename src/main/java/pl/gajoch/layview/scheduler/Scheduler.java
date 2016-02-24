package pl.gajoch.layview.scheduler;

import java.util.*;

public class Scheduler {
    private SortedSet<Event> set;

    public Scheduler() {
        set = new TreeSet<>();

        set.add(new SnapshotEvent(1));
        set.add(new SnapshotEvent(0));
        set.add(new Update3DEvent(1));

        for(Event i: set) {
            i.dispatch();
        }
    }
}
