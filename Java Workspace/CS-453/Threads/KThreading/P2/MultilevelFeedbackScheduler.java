package nachos.threads;

import nachos.machine.*;
import nachos.threads.PriorityScheduler.PriorityQueue;
import nachos.threads.PriorityScheduler.ThreadState;

import java.util.LinkedList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;


public class MultilevelFeedbackScheduler extends Scheduler {
    /**
     * Allocate a new MultilevelFeedbackScheduler scheduler.
     */
    public MultilevelFeedbackScheduler() {
    }
    
    /**
     * A <tt>ThreadQueue</tt> that sorts threads by Round Robin queue.
     */
    protected class MultilevelFeedbackQueue extends ThreadQueue {
    	
    	MultilevelFeedbackQueue(boolean transferPriority) {
		    this.transferPriority = transferPriority;
		}

    	/**
    	 * Add the thread to the end of the Queue
    	 * Must check which queue we're in and add accordingly
    	 * Get Thread's priority
    	 * This is where scheduling comes in to place the thread
    	 * @param	thread	the thread to append to the queue.
    	 */ 
		public void waitForAccess(KThread thread) {
			
		    Lib.assertTrue(Machine.interrupt().disabled());
		    ThreadState TS = getThreadState(thread);
		    ThreadState agingTS;
		    
		    if(runningQueue == 0){
		    	if(TS.getTimeStop()-TS.getTimeStart() < TS0){ // if it ran over timeslice
		    		setPriority(thread,0); // sets the priority
		    		RQ0.addLast(TS); // adds to the back of the queue
		    	}
		    	else{				// Demoting 
		    		setPriority(thread,1); //Demoted
		    		RQ1.addLast(TS);
		    		System.out.println(KThread.currentThread().getName() + 
		    				" was demoted to RQ1 because it finished quantum");
		    	}
		    	
		    	//aging 
			    if(!RQ1.isEmpty()){//if the queue isn't empty	
			    	 while(true){
			    		 agingTS = RQ1.peekLast();
			    		 if(agingTS.getTimeStop()+RQ1Aging >= Machine.timer().getTime()){
			    			 agingTS.setPriority(0);
			    		 	 RQ1.remove(agingTS);
			    		 	 RQ0.addLast(agingTS);
			    		  	 System.out.println(thread.getName() + " was promoted by aging");
			    		 }
			    		 else
			    			 break;
			    	 }
			    }
		    }
		    else if(runningQueue == 1){
		    	if(TS.getTimeStop()-TS.getTimeStart()  < TS1){
		    		setPriority(thread,1);
		    		RQ1.addLast(TS);
		    	}
		    	else{
		    		setPriority(thread,2);
		    		RQ2.addLast(TS);
		    		System.out.println(KThread.currentThread().getName() + 
		    				" was demoted to RQ2 because it finished quantum -- NO OP");
		    	}
		    	//aging 
			    if(!RQ2.isEmpty()){//if the queue isn't empty	
			    	 while(true){
			    		 agingTS = RQ2.peekLast();
			    		 if(agingTS.getTimeStop()+RQ2Aging >= Machine.timer().getTime()){
			    			 agingTS.setPriority(0);
			    		 	 RQ2.remove(agingTS);
			    		 	 RQ1.addLast(agingTS);
					   	  	 System.out.println(thread.getName() + " was promoted by aging");
			    		 }
			    		 else
			    			 break;
			    	 }
			    }
		    }
		    else if(runningQueue == 2){
		    	if(TS.getTimeStop()-TS.getTimeStart()  < TS2){
		    		RQ2.addLast(TS);	//gets placed in the back of the queue anyways
		    	}
		    	else{
		    		setPriority(thread,2);
		    		RQ2.addLast(TS);
		    		System.out.println(KThread.currentThread().getName() + 
		    				" was demoted to RQ2 because it finished quantum");
		    	}
		    }
		    
		    TS.waitForAccess(this); // let the scheduler know that this is available for access
		}

		/**
		 * Remove a thread from the beginning of the queue.
		 * queue is empty. 
		 *			Must check which queue we're in, and remove
		 *			accordingly
		 *
		 * @return	the first thread on the queue, or <tt>null</tt> if the
		 *	       	
		 */
		public KThread nextThread() {
			
			Lib.assertTrue(Machine.interrupt().disabled());
			if(!RQ0.isEmpty()){
				runningQueue=0; //sets the current Queue being acted on
				RQ0.getFirst().setTimeStart(Machine.timer().getTime()); // sets the starting time for the thread
				return RQ0.poll().getThread(); // gets the thread, and removes it from the head of the queue
			}
			else if(!RQ1.isEmpty()){
				runningQueue=1;
				RQ0.getFirst().setTimeStart(Machine.timer().getTime());
				return RQ1.poll().getThread();
			}
			else if(!RQ2.isEmpty()){
				runningQueue=2;
				RQ0.getFirst().setTimeStart(Machine.timer().getTime());
				return RQ2.poll().getThread();
			}
			else{ // if all are empty
				return null;
			}
		}
	    

		/**
		 * The specified thread has received exclusive access, without using
		 * <tt>waitForAccess()</tt> or <tt>nextThread()</tt>. Assert that no
		 * threads are waiting for access.
		 */
		public void acquire(KThread thread) {
		    Lib.assertTrue(Machine.interrupt().disabled());
            ThreadState TS = getThreadState(thread);
            TS.acquire(this);
		}

		/**
		 * prints out the contents of the queue
		 */
		public void print() {
			// doesn't have to do anything
			
		}
		KThread thread;
		public boolean transferPriority;
		private int runningQueue =0; // default starter
		private long TS0 = 3500;
		private long TS1 = 7000;
		private long TS2 = 14000;
		private long RQ1Aging = 14000; // aging threshold for RQ1
		private long RQ2Aging = 28000; // aging threshold for RQ2
    	private LinkedList<ThreadState> RQ0 = new LinkedList<ThreadState>(); // highest priority
    	private LinkedList<ThreadState> RQ1 = new LinkedList<ThreadState>();
    	private LinkedList<ThreadState> RQ2 = new LinkedList<ThreadState>(); // lowest priority
    	
    }
	
    /**
     * The default priority for a new thread. Do not change this value.
     */
    public static final int priorityDefault = 0;
    /**
     * The minimum priority that a thread can have. Do not change this value.
     */
    public static final int priorityLow = 2;
    /**
     * The maximum priority that a thread can have. Do not change this value.
     */
    public static final int priorityHigh = 0; 
	
    public void setPriority(KThread thread, int priority) {
		Lib.assertTrue(Machine.interrupt().disabled());
			       
		Lib.assertTrue(priority <= priorityLow && priority >= priorityHigh);
		
		getThreadState(thread).setPriority(priority);
    }		
    
    
    /**
     * The scheduling state of a thread. This should include the thread's
     * priority, its effective priority, any objects it owns, and the queue
     * it's waiting for, if any.
     *
     * @see	nachos.threads.KThread#schedulingState
     */
	protected class ThreadState {
		/** constructor
		 * 
		 * @param thread
		 */
		public ThreadState(KThread thread) {
		    this.thread = thread;
		    timeStart = Machine.timer().getTime();
		    setPriority(priorityDefault);
		}

		public void acquire(MultilevelFeedbackQueue multilevelFeedbackQueue) {
			multilevelFeedbackQueue.thread = this.thread;
		}

		/**
		 * Called when <tt>waitForAccess(thread)</tt> (where <tt>thread</tt> is
		 * the associated thread) is invoked on the specified priority queue.
		 * The associated thread is therefore waiting for access to the
		 * resource guarded by <tt>waitQueue</tt>. This method is only called
		 * if the associated thread cannot immediately obtain access.
		 *
		 * @param	waitQueue	the queue that the associated thread is
		 *				now waiting on.
		 *
		 * @see	nachos.threads.ThreadQueue#waitForAccess
		 */

		public void waitForAccess(MultilevelFeedbackQueue multilevelFeedbackQueue) {
			multiQueue = multilevelFeedbackQueue;
		    this.setTimeStop(Machine.timer().getTime()); // save the timeStop, for aging
		}


		/**
		 * Set the priority of the associated thread to the specified value.
		 *
		 * @param	priority	the new priority.
		 */
		public void setPriority(int priority) {
		    if (this.priority == priority)
			return;
		    
		    this.priority = priority;
		    
		    // implement me
		}
		
		public void setTimeStop(long time){
			timeStop = time;
		}
		
		public void setTimeStart(Long time){
			timeStart = time;
		}
		
		public int getPriority() {
			return priority;
		}
		
		public long getTimeStart(){
			return timeStart;
		}
		
		public long getTimeStop(){
			return timeStop;
		}
		
		public KThread getThread(){
			return thread;
		}
		

		/** The thread with which this object is associated. */	   
		protected KThread thread;
		/** The priority of the associated thread. */
		protected int priority;
		
		protected long timeStart;
		
		protected long timeStop;
		
		MultilevelFeedbackQueue multiQueue; // the queue this ThreadState is waiting for
	}
	
	/** 
	 * @param thread
	 * @returns the ThreadState
	 */
    protected ThreadState getThreadState(KThread thread) {
		if (thread.schedulingState == null)
		    thread.schedulingState = new ThreadState(thread);
	
		return (ThreadState) thread.schedulingState;
		
    }

	@Override
	public ThreadQueue newThreadQueue(boolean transferPriority) {
		return new MultilevelFeedbackQueue(transferPriority); 
	}
	
	
	//test
	public static final Alarm alarm = new Alarm();
	
	public static void selfTest(){
		Lib.debug(dbgThread, "Enter Alarm.selfTest");
		System.out.println("Begin CPUTest");
		System.out.println("--- factorial calculations from 1! to 20! for 1000 loops");
		System.out.println("Begin at " + Machine.timer().getTime());
		KThread[] threadsList = new KThread[10];
		for(int i = 0; i < 5; i++){
			threadsList[i] = new KThread(new CPUTest());
			threadsList[i].setName("CPUThread "+i);
			System.out.println(threadsList[i].getName() + " forked");
			threadsList[i].fork();
		}
		System.out.println("End CPUTest at " + Machine.timer().getTime());
		System.out.println("Begin IOTest");
		for(int i = 5; i < 10; i++){
			threadsList[i] = new KThread(new IOTest());
			threadsList[i].setName("IOThread "+i);
			System.out.println(threadsList[i].getName() + " forked");
			threadsList[i].fork();
		}
		while(alarm.size() > 0){
			for(int i = 5; i < 10; i++){
				boolean status = Machine.interrupt().disable();
				alarm.timerInterrupt();
				Machine.interrupt().restore(status);
			}
		}
	}

	public static class CPUTest implements Runnable{
		CPUTest(){
		}
		public long factorial(long n){
			return ((n == 0) ? 1 : n * factorial(n - 1));  
		}
		//run many loops
		public void run(){
			for(int j = 1; j < 1000; j++){
				for(long i = 20; i > 0; i--){
					factorial(i);
				}
			}
		}
	}

	public static class IOTest implements Runnable{
		IOTest(){
		}
		static int count = 0;
		int threadnum;
		public void run(){
			long wait = (long)(30000.0 * Math.random() + 1);
			threadnum = count;
			count++;
			System.out.println("IOTest thread " + threadnum + " waiting for "+wait+" ticks");
			System.out.println("IOTest thread " + threadnum + " begin waiting at: "+Machine.timer().getTime());
			alarm.waitUntil(wait);
			System.out.println("IOTest thread " + threadnum + " done waiting at: "+Machine.timer().getTime());
			KThread.yield();
		}
	}

	private static final char dbgThread = 't';

}
