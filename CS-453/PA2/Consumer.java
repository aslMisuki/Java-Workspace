package PA2;

import java.util.Vector;

// consumer builds the file
public class Consumer implements Runnable {
	
	private final Vector sharedQueue;
	private final int SIZE;

	public Consumer(Vector sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	
}
