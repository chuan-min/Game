package trigger;

import java.awt.Shape;

import actor.Actor;
import core.HasName;
import core.HasShape;
import core.Transform;

/**
 * Trigger£º´¥·¢Æ÷
 */
public abstract class Trigger implements HasName,HasShape,Transform{
	protected String name = "trigger";
	protected Actor actor = null;
	protected boolean isOn = false;
	
	public abstract void isTrigger(Actor a);
	public abstract Shape getShape();
	@Override
	public Trigger setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Trigger setShape(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Trigger translate(float x, float y) {
		return null;
	}
	
	@Override
	public Trigger rotate(float angle, float x, float y) {
		return null;
	}
	
	@Override
	public Trigger scale(float x,float y) {
		return null;
	}
}
