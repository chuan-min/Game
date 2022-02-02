package core;

import java.awt.Shape;

/**
 * 拥有形状的功能
 */
public interface HasShape {
	public Shape getShape();
	public HasShape setShape(Shape shape);
}
