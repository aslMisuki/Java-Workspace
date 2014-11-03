package PA2;

import java.util.ArrayList;
import java.util.Vector;

// gets responses?
public class Consumer implements Runnable {
	
	private final ArrayList<MetaData> sharedQueue; // TODO: may need to be byte[] maybe...
	private final int SIZE;

	public Consumer(ArrayList<MetaData> sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
	
}
