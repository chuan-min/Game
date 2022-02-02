package view;

import javax.swing.JFrame;

import core.GameWorld;

public abstract class ViewPort extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x = 0;
	public int y = 0;
	
	public ViewPort() {
		GameWorld.view = this;
	}
	
	public abstract void moveView(int xv,int yv);
}
