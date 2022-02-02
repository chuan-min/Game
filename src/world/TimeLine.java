package world;

public class TimeLine {
	public static long timeFragment = 1000/60;
	protected static long curTime = 0;
	
	public static long getCurTime(){
		return curTime;
	}
}
