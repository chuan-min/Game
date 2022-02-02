package core;

import java.awt.Graphics2D;

/**
 * Render：绘制功能
 */
public interface Render{
	/**
	 * 绘制形状、背景、边框、坐标系、触发边框
	 */
	public void renderShape(Graphics2D g2d);
	public void renderBg(Graphics2D g2d);
	public void renderBorder(Graphics2D g2d);
	public void renderAxis(Graphics2D g2d);
	public void renderTriggerBorder(Graphics2D g2d);
}
