package pl.gajoch.layview.scheduler;

public class EventTester {
    public static void main(String[] args) {
        Scheduler.schedule(new Event(EventType.UPDATE3D, 1) {
            @Override
            public void dispatch() {
                System.out.println("Event 1");
            }
        });

        Scheduler.schedule(new Event(EventType.SNAPSHOT, 0) {
            @Override
            public void dispatch() {
                System.out.println("Event 2");
            }
        });

        Scheduler.schedule(new Event(EventType.UPDATE3D, 1) {
            @Override
            public void dispatch() {
                System.out.println("Event 3");
            }
        });

        for(int i = 0; i < 10; ++i) {
            Scheduler.schedule(new Event(EventType.UPDATE3D, i*1000000) {
                @Override
                public void dispatch() {
                    System.out.println("Event 4");
                }
            });
        }

        Scheduler.schedule(new RepeatedEvent(EventType.SNAPSHOT, 1000000, 20) {
            int i = 0;
            @Override
            public void dispatch() {
                i++;
                System.out.println("Event 5, " + i + " time.");
            }
        });

        Event xxx = new RepeatedEvent(EventType.SNAPSHOT, 100000, 10) {
            int i = 0;
            @Override
            public void dispatch() {
                i++;
                System.out.println("Event 6, " + i + " time.");
            }

            @Override
            public void reset() {
                i = 0;
            }
        };
        Scheduler.schedule(xxx);

        Scheduler.start();
        System.out.println("---------------");
        Scheduler.remove(xxx);
        Scheduler.start();
        System.out.println("---------------");
        Scheduler.start();
    }
}
