package ui;

import java.awt.*;
import java.util.ArrayList;

import core.HasShape;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public abstract class UICommpont implements UI{
	protected int state = UI.Normal;
	protected Shape shape = null;
	protected float x = 0;
	protected float y = 0;
	protected Color bkColor = Color.LIGHT_GRAY;
	protected Color borderColor = Color.GREEN;
	protected Color fontColor = Color.BLACK;

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return shape;
	}

	@Override
	public HasShape setShape(Shape shape) {
		this.shape = shape;
		return this;
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	public Color getBkColor() {
		return bkColor;
	}

	public void setBkColor(Color bkColor) {
		this.bkColor = bkColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	@Override
	public void addListener(UIListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(UIListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyAllListener() {
		// TODO Auto-generated method stub
		
	}

}
