package actor;

import static core.GameWorld.RETA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

import animation.Animation;

public class PolygonPhyActor extends PhyActor {
	private Vec2[] points = null;
	public Vec2 shapeCenter = new Vec2();
	
	public PolygonPhyActor(float x,float y,Vec2[] points, Color color,boolean isFill,boolean isStatic,boolean isBeTriggered) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.points =points;
		this.isFill = isFill;
		this.isStatic = isStatic;
		this.isBeTriggered = isBeTriggered;
		this.shape = getShapeByVec2(this.points);
		Rectangle2D rect = shape.getBounds2D();
		this.shapeCenter.x = (float)(rect.getX() + rect.getWidth()/2);
		this.shapeCenter.y = (float)(rect.getY() + rect.getHeight()/2);
	}
	
	public PolygonPhyActor(float x,float y,Vec2[] points, Color color,boolean isStatic,boolean isBeTriggered) {
		super();
		this.x = x;
		this.y = y;
		this.points =points;
		this.isStatic = isStatic;
		this.color = color;
		this.isBeTriggered = isBeTriggered;
		this.shape = getShapeByVec2(this.points);
		Rectangle2D rect = shape.getBounds2D();
		this.shapeCenter.x = (float)(rect.getX() + rect.getWidth()/2);
		this.shapeCenter.y = (float)(rect.getY() + rect.getHeight()/2);
	}
	
	public PolygonPhyActor(float x,float y,Vec2[] points, Color color,boolean isFill) {
		super();
		this.x = x;
		this.y = y;
		this.points = points;
		this.color = color;
		this.shape = getShapeByVec2(this.points);
		Rectangle2D rect = shape.getBounds2D();
		this.shapeCenter.x = (float)(rect.getX() + rect.getWidth()/2);
		this.shapeCenter.y = (float)(rect.getY() + rect.getHeight()/2);
	}
	
	public PolygonPhyActor(float x,float y,Vec2[] points, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.points = points;
		this.color = color;
		this.shape = getShapeByVec2(this.points);
		Rectangle2D rect = shape.getBounds2D();
		this.shapeCenter.x = (float)(rect.getX() + rect.getWidth()/2);
		this.shapeCenter.y = (float)(rect.getY() + rect.getHeight()/2);
	}
	
	@Override
	public void renderShape(Graphics2D g2d) {
		this.x = body.getPosition().x * RETA;
		this.y = body.getPosition().y * RETA;
		this.angle = body.getAngle();
		PolygonShape pShape = (PolygonShape)body.getFixtureList().getShape();
		Vec2[] vec2s = pShape.getVertices();
		GeneralPath polygon = new GeneralPath();
		polygon.moveTo(vec2s[0].x * RETA, vec2s[0].y * RETA);
		for(int i = 1; i < pShape.m_vertexCount;i++){
			polygon.lineTo(vec2s[i].x * RETA, vec2s[i].y * RETA);
		}
		polygon.closePath();
		
		polygon.transform(AffineTransform.getRotateInstance(angle, 0, 0));
		polygon.transform(AffineTransform.getTranslateInstance(x, y));
		
		if(isRender == false){
			return;
		}
		
		if(spirit == null && (animation == null || animation.getStatus() == Animation.NoStart)){
			if(isFill == false){
				g2d.setColor(color);
				g2d.draw(polygon);
			}else {
				g2d.setColor(color);
				g2d.fill(polygon);
			}
		}
		shape = polygon;
	}

	private Shape getShapeByVec2(Vec2[] a){
		if(a != null){
			GeneralPath rect = new GeneralPath();
			rect.moveTo(a[0].x, a[0].y);
			for(int i = 1;i < a.length;i++){
				rect.lineTo(a[i].x, a[i].y);
			}
			rect.closePath();

			return rect;
		}
		return null;
	}

	public Vec2[] getPoints() {
		return points;
	}

	public PolygonPhyActor setPoints(Vec2[] points) {
		this.points = points;
		
		for(Vec2 v : points){
			v.x = v.x/RETA;
			v.y = v.y/RETA;
		}
		
		if(body != null){
			PolygonShape polygonShape = (PolygonShape) body.getFixtureList().getShape();
			polygonShape.set(points, points.length);
		}
		
		this.shape = getShapeByVec2(this.points);
		Rectangle2D rect = shape.getBounds2D();
		this.shapeCenter.x = (float)(rect.getX() + rect.getWidth()/2);
		this.shapeCenter.y = (float)(rect.getY() + rect.getHeight()/2);
		return this;
	}
	
	public Vec2 getShapeCenter(){
		return shapeCenter;
	}
}
