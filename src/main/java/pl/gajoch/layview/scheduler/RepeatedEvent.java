package pl.gajoch.layview.scheduler;

abstract public class RepeatedEvent extends Event {
    final private int deltaT, times;
    private int counter;

    public RepeatedEvent(EventType type, int offset_us, int times) {
        super(type, offset_us);
        this.times = times;
        deltaT = offset_us;
        counter = 0;
    }

    @Override
    void dispatchHandler() {
        dispatch();
        offset_us += deltaT;
        counter++;
        if( counter < times ) {
            Scheduler.schedule(this);
        }
    }
}
