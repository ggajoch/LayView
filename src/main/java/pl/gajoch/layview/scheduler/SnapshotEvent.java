package pl.gajoch.layview.scheduler;

public class SnapshotEvent extends Event {
    public SnapshotEvent(int offset_us) {
        super(EventType.SNAPSHOT, offset_us);
    }

    @Override
    void dispatch() {
        System.out.println("Snapshot: " + this.offset_us);
    }
}
