package nachos.threads;

import nachos.machine.*;

import java.util.LinkedList;

/**
 * Uses the hardware timer to provide preemption, and to allow threads to sleep
 * until a certain time.
 */
public class Alarm {
    /**
     * Allocate a new Alarm. Set the machine's timer interrupt handler to this
     * alarm's callback.
     *
     * <p><b>Note</b>: Nachos will not function correctly with more than one
     * alarm.
     */
    public Alarm() {
	Machine.timer().setInterruptHandler(new Runnable() {
		public void run() { timerInterrupt(); }
	    });
    }

    /**
     * The timer interrupt handler. This is called by the machine's timer
     * periodically (approximately every 500 clock ticks). Causes the current
     * thread to yield, forcing a context switch if there is another thread
     * that should be run.
     */
    
    public void timerInterrupt() {
    	for(int i = 0; i < wakeList.size(); i++){//iterate through wakeList
    		if(Machine.timer().getTime() >= wakeList.get(i).getWakeTime()){//check if it's time to wake up
    			KThread thread = wakeList.get(i).getKThread();//if so, grab the thread
    			wakeList.remove(i);//remove it
    			thread.ready();//and add thread to ready queue
    			System.out.println("Woken up at: " + Machine.timer().getTime());//for runTest
    		}
    	}
    	KThread.yield();//make currentThread yield
    }

    /**
     * Put the current thread to sleep for at least <i>x</i> ticks,
     * waking it up in the timer interrupt handler. The thread must be
     * woken up (placed in the scheduler ready set) during the first timer
     * interrupt where
     *
     * <p><blockquote>
     * (current time) >= (WaitUntil called time)+(x)
     * </blockquote>
     *
     * @param	x	the minimum number of clock ticks to wait.
     *
     * @see	nachos.machine.Timer#getTime()
     */
    public void waitUntil(long x) {
	/*
	 * // for now, cheat just to get something working (busy waiting is bad)
	long wakeTime = Machine.timer().getTime() + x;
	while (wakeTime > Machine.timer().getTime())
	    KThread.yield();
	*/
    	//start of real code
    	boolean intStatus = Machine.interrupt().disable();
    	long wakeTime = Machine.timer().getTime() + x;//log wake time
    	wakeList.add(new TimerWaitContainer(wakeTime, KThread.currentThread()));//add TimerWaitContainer to wakeList; contains the wake time and KThread to wake
    	System.out.println("Sleeping at: " + Machine.timer().getTime());//for runTest
    	KThread.sleep();//sleep, must be while interrupts are disabled
    	Machine.interrupt().restore(intStatus);
    }
    
    public static void runTest() {
    	Lib.debug(dbgThread, "Enter Alarm.selfTest");
    	System.out.println("Creating alarm...");
    	Alarm alarm = new Alarm();
    	System.out.println("done!\n---Test 1(wait 100)---");
    	alarm.waitUntil(100);
    	System.out.println("---Test 2(wait 1000)---");
    	alarm.waitUntil(1000);
    	System.out.println("---Test 3(wait 650)---");
    	alarm.waitUntil(650);
    	System.out.println("---Test 4(wait 12345)---");
    	alarm.waitUntil(12345);
    }
    private static final char dbgThread = 't';
    
    private LinkedList<TimerWaitContainer> wakeList = new LinkedList<TimerWaitContainer>();
}
