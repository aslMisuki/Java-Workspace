package multiThread;

import java.util.Vector;

public class main {
	
	
	public static void main(String args[]) {
		Vector sharedQueue = new Vector();
		int size = 4;
		Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
		Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
		
		// starts the threads
		prodThread.start();
		consThread.start();
	}
}
