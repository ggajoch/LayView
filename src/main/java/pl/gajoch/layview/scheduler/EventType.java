package pl.gajoch.layview.scheduler;

public enum EventType {
    SNAPSHOT(2),
    UPDATE3D(1),
    UPDATE2D(1);

    public final int priority;

    EventType(int priority) {
        this.priority = priority;
    }
}
