package pl.gajoch.layview.scheduler;

public class Update3DEvent extends Event {
    public Update3DEvent(int offset_us) {
        super(EventType.UPDATE3D, offset_us);
    }

    @Override
    void dispatch() {
        System.out.println("Update3D: " + this.offset_us);
    }
}
