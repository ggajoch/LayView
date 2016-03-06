package pl.gajoch.layview.scheduler;

abstract public class RepeatedEvent extends Event {
    final private int times;
    private int counter;

    public RepeatedEvent(EventType type, int offset_us, int times) {
        super(type, offset_us);
        this.times = times;
        counter = 0;
    }

    @Override
    void dispatchHandler() {
        dispatch();
        offset_actual += offset_us;
        counter++;
        if( counter < times ) {
            Scheduler.__scheduleCurrentRun(this);
        }
    }

    @Override
    void resetHandler() {
        counter = 0;
        offset_actual = offset_us;
        reset();
    }
}
