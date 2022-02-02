package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import util.ImageHandle;
import control.UserContorl;


public class UIPanel extends UserContorl{
	private List<UI> uis = null;
	private BufferedImage oneFrame = null;
	private int width = 0;
	private int height = 0;
	private float opacity = 0f;
	private Color color = Color.white;
	
	public UIPanel(int w,int h) {
		width = w;
		height = h;
		oneFrame = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		uis = new ArrayList<UI>();
	}
	
	public void addUI(UI ui){
		uis.add(ui);
	}
	
	public List<UI> getUIs(){
		return uis;
	}
	
	protected void renderOneFrame(){
		Graphics2D g = oneFrame.createGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		oneFrame = ImageHandle.setAlpha(oneFrame, opacity*255);
		g.dispose();
        g = oneFrame.createGraphics();
		for(UI ui : uis){
			ui.drawUI(g);
		}
		g.dispose();
	}
	
	public void render(Graphics2D g2d){
		renderOneFrame();
		g2d.drawImage(oneFrame, 0, 0, null);
	}
	
	protected void Checked(Point2D p,int s){
		for(UI ui : uis){
			int m = ui.getState();
			if(ui.getShape().contains(p) == true){
				if(s == 5 &&(m == UI.Normal || m == UI.mouseExit)){
					ui.setState(UI.mouseEnter);
				}else {
					ui.setState(s);
				}
			}else {
				if(m == UI.mouseEnter){
					ui.setState(UI.mouseExit);
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Checked(e.getPoint(), UI.MousePressed);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Checked(e.getPoint(), UI.MouseReleased);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Checked(e.getPoint(), 5);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
