package view;

import javax.swing.JFrame;

import scene.Scene;

public class RectViewPort extends ViewPort {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RectViewPort(int x,int y,int width,int height) {
		setSize(width, height + 29);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

	}

	@Override
	public void moveView(int xv, int yv) {
		x +=xv;
		y +=yv;
	}
	
	@Override
	public int getHeight() {
		return super.getHeight() - 29;
	}
	
	public void setScene(Scene scene){
		add(scene);
	}

}
