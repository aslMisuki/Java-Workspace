package nachos.threads;

public class TimerWaitContainer {
	private KThread thread;
	private long wakeTime;
	
	public TimerWaitContainer(long wakeTime, KThread thread){
		this.wakeTime = wakeTime;
		this.thread = thread;
	}
	
	public KThread getKThread(){
		return thread;
	}
	
	public long getWakeTime(){
		return wakeTime;
	}
}
