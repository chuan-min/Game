package actor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

import animation.Animation;
import static core.GameWorld.RETA;

public class RectPhyActor extends PhyActor{
	private float width = 0;
	private float height = 0;
	
	public RectPhyActor(float x,float y,float width,float height,Color color,boolean isFill,boolean isStatic,boolean isBeTriggered) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.isFill = isFill;
		this.isStatic = isStatic;
		this.isBeTriggered = isBeTriggered;
		this.x = x + width/2;
		this.y = y + height/2;
		shape = new Rectangle2D.Double(x,y,width,height);
	}
	
	public RectPhyActor(float x,float y,float width,float height,Color color,boolean isStatic,boolean isBeTriggered) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.isStatic = isStatic;
		this.isBeTriggered = isBeTriggered;
		this.x = x + width/2;
		this.y = y + height/2;
		shape = new Rectangle2D.Double(x,y,width,height);
	}
	
	public RectPhyActor(float x,float y,float width,float height,Color color,boolean isFill) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.isFill = isFill;
		this.x = x + width/2;
		this.y = y + height/2;
		shape = new Rectangle2D.Double(x,y,width,height);
	}
	
	public RectPhyActor(float x,float y,float width,float height,Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.x = x + width/2;
		this.y = y + height/2;
		shape = new Rectangle2D.Double(x,y,width,height);
	}
	
	public RectPhyActor(float x,float y,float width,float height) {
		this.width = width;
		this.height = height;
		this.x = x + width/2;
		this.y = y + height/2;
		shape = new Rectangle2D.Double(x,y,width,height);
	}
	
	@Override
	public void renderShape(Graphics2D g2d) {
		this.x = body.getPosition().x * RETA;
		this.y = body.getPosition().y * RETA;
		this.angle = body.getAngle();
		PolygonShape pShape = (PolygonShape)body.getFixtureList().getShape();
		Vec2[] vec2s = pShape.getVertices();
		Path2D.Double rect = new Path2D.Double();
		rect.moveTo(vec2s[0].x * RETA, vec2s[0].y * RETA);
		for(int i = 1; i < pShape.m_vertexCount;i++){
			rect.lineTo(vec2s[i].x * RETA, vec2s[i].y * RETA);
		}
		rect.closePath();
		
		rect.transform(AffineTransform.getRotateInstance(angle, 0, 0));
		rect.transform(AffineTransform.getTranslateInstance(x, y));
		
		if(isRender == false){
			return;
		}
		if(spirit == null && (animation == null || animation.getStatus() == Animation.NoStart)){
			if(isFill == false){
				g2d.setColor(color);
				g2d.draw(rect);
			}else {
				g2d.setColor(color);
				g2d.fill(rect);
			}
		}
		shape = rect;
	}

	@Override
	public void renderTriggerBorder(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	public float getWidth() {
		return width;
	}

	public RectPhyActor setWidth(float width) {
		this.width = width;
		if(body != null){
			PolygonShape polygonShape = (PolygonShape)body.getFixtureList().getShape();
			polygonShape.setAsBox(width/2/RETA, height/2/RETA);
		}
		return this;
	}

	public float getHeight() {
		return height;
	}

	public RectPhyActor setHeight(float height) {
		this.height = height;
		if(body != null){
			PolygonShape polygonShape = (PolygonShape)body.getFixtureList().getShape();
			polygonShape.setAsBox(width/2/RETA, height/2/RETA);
		}
		return this;
	}
}
