package pl.gajoch.layview.scheduler;

import java.util.concurrent.TimeUnit;

public class EventTester {
    public static void main(String[] args) {
        Scheduler sch = new Scheduler();
        sch.set.add(new SnapshotEvent(1));
        sch.set.add(new SnapshotEvent(0));
        sch.set.add(new Update3DEvent(1));

        for(int i = 0; i < 10; ++i) {
            sch.set.add(new SnapshotEvent(i*1000000));
        }

        sch.dispatchAll();
    }
}
