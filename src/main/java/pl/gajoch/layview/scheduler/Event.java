package pl.gajoch.layview.scheduler;

abstract public class Event implements Comparable<Event> {
    public final EventType type;
    public final int offset_us;
    public int offset_actual;

    public Event(EventType type, int offset_us) {
        this.type = type;
        this.offset_us = offset_us;
        this.offset_actual = offset_us;
    }

    @Override
    public int compareTo(Event o) {
        if( this.offset_actual == o.offset_actual ) {
            return Integer.compare(this.type.priority, o.type.priority);
        }
        return Integer.compare(this.offset_actual, o.offset_actual);
    }

    void dispatchHandler() {
        this.dispatch();
    }
    void resetHandler() {
        reset();
    }

    public abstract void dispatch();
    public void reset() {

    }

}
