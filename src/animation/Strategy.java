package animation;

import java.awt.image.BufferedImage;

/**
 * Strategy:����; �Ʋ�; �ж��ƻ�; �߻�; �滮; ����; ͳ�ﰲ��; ս��; ս�Բ���
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
