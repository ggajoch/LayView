package pl.gajoch.layview.scheduler;

abstract public class Event implements Comparable<Event> {
    public final EventType type;
    public final int offset_us;

    public Event(EventType type, int offset_us) {
        this.type = type;
        this.offset_us = offset_us;
    }

    @Override
    public int compareTo(Event o) {
        if( this.offset_us == o.offset_us ) {
            return Integer.compare(this.type.priority, o.type.priority);
        }
        return Integer.compare(this.offset_us, o.offset_us);
    }

    abstract void dispatch();
}
