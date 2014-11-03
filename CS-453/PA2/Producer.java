package PA2;

import java.util.ArrayList;
import java.util.Vector;

// sends requests?

public class Producer implements Runnable{

	
	private final ArrayList<MetaData> sharedQueue;
	private final int SIZE;

	public Producer(ArrayList<MetaData> sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
