package ui;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Button extends UICommpont{
	public String text = "button";
	
	/**
	 * ????????????????????
	 * @param x x????
	 * @param y y????
	 * @param w button???
	 * @param h button???
	 * @param arcw button??????
	 */
	public Button(float x,float y,float w,float h, float arcw,float arch){
		this.x = x;
		this.y = y;
		this.shape = new RoundRectangle2D.Float(x, y, w, h, arcw, arch);
	}
	
	/**
	 * ?????????????????
	 * @param x
	 * @param y
	 * @param w ??
	 */
	public Button(float x,float y,float w,float h){
		this.x = x;
		this.y = y;
		this.shape = new Ellipse2D.Float(x, y, w, h);
 	}
	
	public Button(String text) {
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	@Override
	public final void drawUI(Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D rc = fm.getStringBounds(text, g2d);
		float stringX = (float) (shape.getBounds2D().getCenterX() - rc.getWidth()/2);
		float stringY = (float) (shape.getBounds2D().getCenterY() + rc.getHeight()/4);
		switch (state) {
		case UI.Normal:
			g2d.setColor(bkColor);
			g2d.fill(shape);
			g2d.setColor(borderColor);
			g2d.draw(shape);
			g2d.setColor(fontColor);
			g2d.drawString(text,stringX,stringY);
			break;
		case UI.MousePressed:
			break;
		case UI.MouseReleased:
			state = UI.mouseEnter;
			break;
		case UI.mouseEnter:
			System.out.println("mouseEnter");
			state = UI.Normal;
			break;
		case UI.mouseExit:
			state = UI.Normal;
			break;
		default:
			break;
		}
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
