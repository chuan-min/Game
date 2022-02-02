package actor;

import static core.GameWorld.RETA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import animation.Animation;
import animation.Spirit;
import core.HasShape;
import scene.Scene;
import script.Script;
import trigger.Trigger;

public abstract class PhyActor implements Actor,Physical{
	protected Display display = new ScaleDisplay();
	protected boolean isFixedRotation = false;
	public Body body = null;
	protected Spirit spirit = null;
	protected boolean isRender = true; 
	protected Animation animation = null;
	protected Color color = Color.red;
	protected boolean isFill = true;
	protected boolean isStatic = false;
	protected boolean isBeTriggered = true;
	protected float x = 0;
	protected float y = 0;
	protected float angle = 0;
	protected Path2D line = null;
	protected Shape shape = null;
	private String name = "phyActor";
	private List<Script> scripts = new CopyOnWriteArrayList<Script>();
	private List<Trigger> triggers = new CopyOnWriteArrayList<Trigger>();
	protected float density = 1f;
	protected float friction = 0.1f;
	protected float restitution = 0.5f;
	protected Scene scene = null;
	
	public void init(World world){
	
		FixtureDef fixtureDef = new FixtureDef();
		BodyDef bodyDef = new BodyDef();
		
		if(this instanceof CirclePhyActor){
			CirclePhyActor actor = (CirclePhyActor)this;
			CircleShape circleShape = new CircleShape();
			circleShape.m_radius = actor.getR()/RETA;
			fixtureDef.shape = circleShape;
			bodyDef.position.set(x/RETA, y/RETA);
		}else if(this instanceof RectPhyActor){
			RectPhyActor actor = (RectPhyActor)this;
			PolygonShape polygon =new PolygonShape();  
	        polygon.setAsBox(actor.getWidth()/2/RETA, actor.getHeight()/2/RETA);
	        fixtureDef.shape = polygon;
	        bodyDef.position.set(x/RETA, y/RETA);
		}else if(this instanceof PolygonPhyActor){
			PolygonPhyActor actor = (PolygonPhyActor)this;
			PolygonShape polygon =new PolygonShape();
			Vec2[] points = actor.getPoints();
			for(Vec2 v : points){
				v.x = v.x/RETA;
				v.y = v.y/RETA;
			}
			polygon.set(points, points.length);
			fixtureDef.shape = polygon;
			bodyDef.position.set((x-actor.shapeCenter.x)/RETA,(y-actor.shapeCenter.y)/RETA);
		}

		if(isStatic) {  
			/**密度为0时，在物理世界中不受外力影响，为静止的 */  
			fixtureDef.density=0;  
		}else{  
			/**密度不为0时，在物理世界中会受外力影响 */  
			fixtureDef.density=density;  
		}  
		
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;
		
		//设置不为禁止物体
		bodyDef.type=isStatic?BodyType.STATIC:BodyType.DYNAMIC;  
		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		
		if(isFixedRotation == true){
			body.setFixedRotation(true);
		}
		body.m_userData = this;
		this.body = body;
	}
	
	public String getName() {
		return name;
	}

	
	public List<Trigger> getTriggers() {
		return triggers;
	}

	public void addTrigger(Trigger trigger){
		triggers.add(trigger);
	}
	
	public List<Script> getScripts() {
		return scripts;
	}

	public PhyActor setName(String name) {
		this.name = name;
		return this;
	}
	
	public void addScript(Script script){
		scripts.add(script);
		script.setActor(this);
	}
	
	public void removeScript(String name){
		
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Vec2 getCenter(){
		return new Vec2(x,y);
	}
	
	public Shape getShape(){
		return shape;
	}

//	public abstract void transfrom(float x,float y,float angle);


	public boolean isFill() {
		return isFill;
	}


	public void setFill(boolean isFill) {
		this.isFill = isFill;
	}


	public boolean isStatic() {
		return isStatic;
	}


	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
		if(body != null){
			if(isStatic == false){
				body.m_type = BodyType.DYNAMIC;
			}else {
				body.m_type = BodyType.STATIC;
			}
		}
	}
	
	public Spirit getSpirit() {
		return spirit;
	}


	public void setSpirit(Spirit spirit) {
		this.spirit = spirit;
	}


	@Override
	public void renderTriggerBorder(Graphics2D g2d) {
		g2d.setColor(color);
		for(Trigger trigger : getTriggers()){
			g2d.draw(trigger.getShape());
		}
	}
	
	public float getDensity() {
		return density;
	}

	public PhyActor setDensity(float density) {
		this.density = density;
		if(body != null){
			body.getFixtureList().m_density = density;
		}
		return this;
	}

	public float getFriction() {
		return friction;
	}

	public PhyActor setFriction(float friction) {
		this.friction = friction;
		if(body != null){
			body.getFixtureList().m_friction = friction;
		}
		return this;
		
	}

	public float getRestitution() {
		return restitution;
	}

	public PhyActor setRestitution(float restitution) {
		this.restitution = restitution;
		if(body != null){
			body.getFixtureList().m_restitution = restitution;
		}
		return this;
	}
	
	public PhyActor setFixedRotation(boolean f){
		this.isFixedRotation = f;
		if(body != null){
			body.setFixedRotation(isFixedRotation);
		}
		return this;
	}
	
	public PhyActor setPhysicalAttr(float d,float f,float r){
		this.density =d ;
		this.friction = f;
		this.restitution = r;
		
		if(body != null){
			setDensity(density);
			setFriction(friction);
			setRestitution(restitution);
		}
		return this;
	}
	
	public boolean isFixedRotation(){
		return isFixedRotation;
	}
	
	public boolean isBeTriggered() {
		return isBeTriggered;
	}
	
	@Override
	public Animation getAnimation() {
		return animation;
	}
	
	@Override
	public PhyActor setAnimation(Animation animation) {
		this.animation = animation;
		return this;
	}
	
	@Override
	public void destroy() {
		scene.removeActor(this);
		this.animation = null;
		this.spirit = null;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	@Override
	public HasShape setShape(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PhyActor translate(float x,float y){
		return this;
	}
	
	public PhyActor rotate(float angle,float x,float y){
		return this;
	}
	
	public PhyActor scale(float x,float y){
		return this;
	}
	
	@Override
	public void removeScript(Script script) {
		// TODO Auto-generated method stub
		
	}

	public boolean isRender() {
		return isRender;
	}

	public void setRender(boolean isRender) {
		this.isRender = isRender;
	}
	
	@Override
	public void renderBg(Graphics2D g2d) {
		if(isRender == false){
			return;
		}
		if(spirit != null){
			display.display(this, spirit.getOneFrame(), g2d);
		}
		
		if(animation != null && animation.getStatus() != Animation.NoStart){
			display.display(this, animation.getOneFrame(), g2d);
		}
	}

	@Override
	public void renderBorder(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.draw(shape.getBounds2D());
	}

	@Override
	public void renderAxis(Graphics2D g2d) {
		Color c = new Color(255-color.getRed(),255-color.getGreen(),255-color.getBlue()); 
		line = new Path2D.Double();
		if(this instanceof CirclePhyActor){
			line.moveTo(0, 0);
			line.lineTo(0,((CirclePhyActor)this).getR()-3);
		}else if(this instanceof RectPhyActor){
			line.moveTo(0, 0);
			line.lineTo(0,((RectPhyActor)this).getHeight()/2-3);
		}else if(this instanceof PolygonPhyActor){
			Vec2 cn = ((PolygonPhyActor)this).getShapeCenter();
			line.moveTo(cn.x, cn.y);
			line.lineTo(cn.x,cn.y-shape.getBounds2D().getHeight()/2-3);
		}
		
		line.transform(AffineTransform.getRotateInstance(angle,0,0));
		line.transform(AffineTransform.getTranslateInstance(x, y));
		g2d.setColor(c);
		g2d.draw(line);
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public void setAngle(float angle) {
		
	}

	@Override
	public void setDisplay(Display display) {
		this.display = display;
		
	}

	@Override
	public Display getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}
}
