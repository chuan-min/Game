package actor;


import animation.Animation;
import animation.Spirit;
import org.jbox2d.common.Vec2;
import scene.Scene;
import script.Script;
import trigger.Trigger;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DrawActor implements Actor {
	private Display display = new ScaleDisplay();
	private Spirit spirit = null;
	private Animation animation = null;
	private Color color = Color.red;
	private boolean isFill = true;
	private boolean isRender = true;
	private boolean isBeTriggered = true;
	private float x = 0;
	private float y = 0;
	private float width = 0;
	private float height= 0;
	private Vec2[] points = null;
	private float angle = 0;
	private float r = 0;
	private Path2D line = null;
	private Shape shape = null;
	private String name = "drawActor";
	private List<Script> scripts = new CopyOnWriteArrayList<Script>();
	private List<Trigger> triggers = new CopyOnWriteArrayList<Trigger>();
	private Scene scene = null;
	
	
	public DrawActor(float x,float y,float w,float h) {
		this.x = x + w/2;
		this.y = y + h/2;
		this.width = w;
		this.height = h;
		GeneralPath path = new GeneralPath();
		path.moveTo(x, y);
		path.lineTo(x + w, y);
		path.lineTo(x + w, y + h);
		path.lineTo(x, y + h);
		path.closePath();
		shape = path;
	}
	
	public DrawActor(float x,float y,float r){
		this.x = x;
		this.y = y;
		this.r = r;
		shape = new Ellipse2D.Double(x-r,y-r,2*r,2*r);
	}
	
	public DrawActor(float x,float y,Vec2[] points){
		this.x = x;
		this.y = y;
		this.points = points;
		GeneralPath path = new GeneralPath();
		path.moveTo(points[0].x, points[0].x);
		for(int i = 1; i < points.length;i++){
			path.lineTo(points[i].x,points[i].y);
		}
		path.closePath();
		Rectangle2D rect = path.getBounds2D();
		double centerX = rect.getCenterX();
		double centerY = rect.getCenterY();
		path.transform(AffineTransform.getTranslateInstance(x - centerX, y - centerY));
		shape = path;
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

	public DrawActor setName(String name) {
		this.name = name;
		return this;
	}
	
	public void addScript(Script script){
		scripts.add(script);
		script.setActor(this);
	}
	
	@Override
	public void removeScript(String name){
		
	}
	

	@Override
	public void removeScript(Script script) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "ÀàÐÍ:" + this.getClass().getSimpleName() + "Ãû×Ö:"+name
				+"----hashcode" + this.getClass().hashCode();
	}
	
	public Vec2 getCenter(){
		return new Vec2(x,y);
	}
	
	public Shape getShape(){
		return shape;
	}

	public boolean isFill() {
		return isFill;
	}


	public DrawActor setFill(boolean isFill) {
		this.isFill = isFill;
		return this;
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
	
	public boolean isBeTriggered() {
		return isBeTriggered;
	}
	
	@Override
	public Animation getAnimation() {
		return animation;
	}
	
	@Override
	public DrawActor setAnimation(Animation animation) {
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
	public void renderShape(Graphics2D g2d) {
		if(isRender == false){
			return;
		}
		
		if(spirit == null && (animation == null || animation.getStatus() == Animation.NoStart)){
			if(isFill == false){
				g2d.setColor(color);
				g2d.draw(shape);
			}else {
				g2d.setColor(color);
				g2d.fill(shape);
			}
		}
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
		line.moveTo(0, 0);
		line.lineTo(0,shape.getBounds2D().getHeight()/2-3);
		line.transform(AffineTransform.getRotateInstance(angle,0,0));
		line.transform(AffineTransform.getTranslateInstance(x, y));
		g2d.setColor(c);
		g2d.draw(line);
	}
	
	@Override
	public DrawActor setShape(Shape shape) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DrawActor translate(float x,float y){
		if(shape instanceof Ellipse2D){
			Ellipse2D.Double ellipse2d = (Ellipse2D.Double) shape;
			ellipse2d.x += x;
			ellipse2d.y += y;
		}else {
			GeneralPath path = (GeneralPath) shape;
			path.transform(AffineTransform.getTranslateInstance(x, y));
		}
		return this;
	}
	
	public DrawActor rotate(float angle,float x,float y){
		this.angle = angle;
		if(shape instanceof GeneralPath){
			GeneralPath path = (GeneralPath) shape;
			path.transform(AffineTransform.getRotateInstance(angle, x, y));
		}
		return this;
	}
	
	public DrawActor scale(float x,float y){
		if(shape instanceof Ellipse2D){
			Ellipse2D.Double ellipse2d = (Ellipse2D.Double) shape;
			ellipse2d.width *=x;
			ellipse2d.height *=y;
		}else {
			GeneralPath path = (GeneralPath) shape;
			path.transform(AffineTransform.getScaleInstance(x, y));
		}
		return this;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Vec2[] getPoints() {
		return points;
	}

	public void setPoints(Vec2[] points) {
		this.points = points;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setBeTriggered(boolean isBeTriggered) {
		this.isBeTriggered = isBeTriggered;
	}
	
	public void setRender(boolean flag){
		this.isRender = flag;
	}
	
	public boolean isRender(){
		return isRender;
	}

	@Override
	public void setDisplay(Display display) {
		this.display =display;
		
	}

	@Override
	public Display getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}
	
}
