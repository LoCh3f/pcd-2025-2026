package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Pinger extends ActiveComponent {

	private final Semaphore pongDoneEvent;
	private final Semaphore pingDoneEvent;

	public Pinger(final Semaphore pongDoneEvent, final Semaphore pingDoneEvent) {
		this.pongDoneEvent = pongDoneEvent;
		this.pingDoneEvent = pingDoneEvent;
	}	
	
	public void run() {
		while (true) {
            try {
                this.pongDoneEvent.acquire();
				println("ping");
				this.pingDoneEvent.release();

			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
		}
	}
}