package pl.gajoch.layview.scheduler;

import java.util.concurrent.TimeUnit;

public class EventTester {
    public static void main(String[] args) {
        Scheduler.schedule(new Event(EventType.UPDATE3D, 1) {
            @Override
            void dispatch() {
                System.out.println("Event 1");
            }
        });

        Scheduler.schedule(new Event(EventType.SNAPSHOT, 0) {
            @Override
            void dispatch() {
                System.out.println("Event 2");
            }
        });

        Scheduler.schedule(new Event(EventType.UPDATE3D, 1) {
            @Override
            void dispatch() {
                System.out.println("Event 3");
            }
        });

        for(int i = 0; i < 10; ++i) {
            Scheduler.schedule(new Event(EventType.UPDATE3D, i*1000000) {
                @Override
                void dispatch() {
                    System.out.println("Event 4");
                }
            });
        }

        Scheduler.schedule(new RepeatedEvent(EventType.SNAPSHOT, 1000000, 20) {
            int i = 0;
            @Override
            void dispatch() {
                i++;
                System.out.println("Event 5, " + i + " time.");
            }
        });

        Scheduler.schedule(new RepeatedEvent(EventType.SNAPSHOT, 100000, 10) {
            int i = 0;
            @Override
            void dispatch() {
                i++;
                System.out.println("Event 6, " + i + " time.");
            }
        });
        Scheduler.start();
    }
}
