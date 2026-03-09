package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Ponger extends ActiveComponent {
	private Semaphore pingDoneEvent;
	private Semaphore pongDoneEvent;

	
	public Ponger(final Semaphore pingDoneEvent, final Semaphore pongDoneEvent) {
		this.pingDoneEvent = pingDoneEvent;
		this.pongDoneEvent = pongDoneEvent;
	}
	
	public void run() {
		while (true) {
            try {
                this.pingDoneEvent.acquire();
				println("pong");
				this.pongDoneEvent.release();

			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
		}
	}
}