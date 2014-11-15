package nachos.threads;

import nachos.machine.*;

/**
 * A <i>communicator</i> allows threads to synchronously exchange 32-bit
 * messages. Multiple threads can be waiting to <i>speak</i>,
 * and multiple threads can be waiting to <i>listen</i>. But there should never
 * be a time when both a speaker and a listener are waiting, because the two
 * threads can be paired off at this point.
 */
public class Communicator {
	
	
	private int word;// word message to be sent/received
	public Lock lock; // mutual exclusion lock
	public Condition speakerC;  //condition variable speaker
	public Condition listenerC; // ...
	public boolean isBusy= false;
	
    /**
     * Allocate a new communicator.
     */
    public Communicator() {
    	lock=new Lock();
    	speakerC=new Condition(lock);
    	listenerC=new Condition(lock);
    }
	
    /**
     * Wait for a thread to listen through this communicator, and then transfer
     * <i>word</i> to the listener.
     *
     * <p>
     * Does not return until this thread is paired up with a listening thread.
     * Exactly one listener should receive <i>word</i>.
     *
     * @param	word	the integer to transfer.
     */
    public void speak(int word) {
    	lock.acquire();
    	this.word=word;
    	while(isBusy)
    	    speakerC.sleep();//go to sleep on this condition until another thread wakes it up;
    	isBusy=false;
    	listenerC.wakeAll(); // wakes all thread sleepin on this condition variable
    	lock.release(); // atomically release the lock for other threads
    }
	
    /**
     * Wait for a thread to speak through this communicator, and then return
     * the <i>word</i> that thread passed to <tt>speak()</tt>.
     *
     * @return	the integer transferred.
     */    
    public int listen() {
		lock.acquire();
		this.word=word;
		while(isBusy)
			listenerC.sleep();//go to sleep on this condition until another thread wakes it up;
		isBusy=false;
		speakerC.wakeAll(); // wakes all thread sleepin on this condition variable
		lock.release();// atomically release the lock for other threads
    	return word;
    }
}
