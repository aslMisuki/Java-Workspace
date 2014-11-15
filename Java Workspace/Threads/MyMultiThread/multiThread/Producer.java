package multiThread;

import java.util.Vector;

public class Producer implements Runnable{

	
	private final Vector sharedQueue;
	private final int SIZE;

	public Producer(Vector sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
