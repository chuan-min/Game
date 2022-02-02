package animation;

import java.awt.image.BufferedImage;

/**
 * Strategy:策略; 计策; 行动计划; 策划; 规划; 部署; 统筹安排; 战略; 战略部署
 */
public interface Strategy {
	public static final int Start = 1;
	public static final int Stop = 2;
	public static final int Suspended = 3;
	public static final int NoStart = 4;
	
	public int getState();
	public void setState(int state);
	public BufferedImage getOneFrameByStart();
	public BufferedImage getOneFrameByStop();
	public BufferedImage getOneFrameBySus();
	public BufferedImage getOneFrameByNoStart();
}
