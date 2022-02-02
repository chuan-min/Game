package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import core.HasShape;

public interface UI extends HasShape,UIEvent{
	public static final int MousePressed = 0;
	public static final int MouseReleased = 1;
	public static final int Normal = 2;
	public static final int mouseEnter = 3;
	public static final int mouseExit = 4;
	
	public void drawUI(Graphics2D g2d);
	public int getState();
	public void setState(int state);
	
	public void setBkColor(Color color);
	public Color getBkColor();
	public void setBorderColor(Color color);
	public Color getBorderColor();
	
	public void setFontColor(Color color);
	public Color getFontColor();
}
