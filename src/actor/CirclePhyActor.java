package actor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;

import animation.Animation;
import static core.GameWorld.RETA;

public class CirclePhyActor extends PhyActor {
	private float r = 0;
	
	public CirclePhyActor(float x,float y,float r,Color color,boolean isFill,boolean isStatic,boolean isBeTriggered) {
		super();
		this.color = color;
		this.r = r;
		this.isFill = isFill;
		this.isStatic = isStatic;
		this.x = x;
		this.y = y;
		this.isBeTriggered = isBeTriggered;
		shape = new Ellipse2D.Double(this.x-this.r,this.y-this.r,2*this.r,2*this.r);
	}
	
	public CirclePhyActor(float x,float y,float r,Color color,boolean isStatic,boolean isBeTriggered) {
		super();
		this.color = color;
		this.r = r;
		this.isBeTriggered = isBeTriggered;
		this.isStatic = isStatic;
		this.x = x;
		this.y = y;
		shape = new Ellipse2D.Double(this.x-this.r,this.y-this.r,2*this.r,2*this.r);
	}
	
	public CirclePhyActor(float x,float y,float r,Color color,boolean isFill) {
		super();
		this.color = color;
		this.r = r;
		this.isFill = isFill;
		this.x = x;
		this.y = y;
		shape = new Ellipse2D.Double(this.x-this.r,this.y-this.r,2*this.r,2*this.r);
	}
	
	public CirclePhyActor(float x,float y,float r,Color color) {
		super();
		this.color = color;
		this.r = r;
		this.x = x;
		this.y = y;
		shape = new Ellipse2D.Double(this.x-this.r,this.y-this.r,2*this.r,2*this.r);
	}
	
	@Override
	public void renderShape(Graphics2D g2d) {
		Vec2 vec2 = body.getPosition();
		this.x = vec2.x * RETA;
		this.y = vec2.y * RETA;
		this.angle = body.getAngle();
		Ellipse2D.Double circle = new Ellipse2D.Double(x -r,y -r,2*r,2*r);
		
		if(isRender == false){
			return;
		}
		
		if(spirit == null && (animation == null || animation.getStatus() == Animation.NoStart)){
			if(isFill == false){
				g2d.setColor(color);
				g2d.draw(circle);
			}else {
				g2d.setColor(color);
				g2d.fill(circle);
			}
		}
		shape = circle;
	}
	
	public float getR() {
		return r;
	}

	public CirclePhyActor setR(float r) {
		this.r = r;
		if(body != null){
			CircleShape circleShape = (CircleShape) body.getFixtureList().getShape();
			circleShape.m_radius = r/RETA;
		}
		return this;
	}
	
	@Override
	public CirclePhyActor rotate(float angle, float x, float y) {
		return this;
	}
}
