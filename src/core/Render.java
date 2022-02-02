package core;

import java.awt.Graphics2D;

/**
 * Render�����ƹ���
 */
public interface Render{
	/**
	 * ������״���������߿�����ϵ�������߿�
	 */
	public void renderShape(Graphics2D g2d);
	public void renderBg(Graphics2D g2d);
	public void renderBorder(Graphics2D g2d);
	public void renderAxis(Graphics2D g2d);
	public void renderTriggerBorder(Graphics2D g2d);
}
