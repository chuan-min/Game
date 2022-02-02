package trigger;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import actor.Actor;
import script.Script;

public class BoxTrigger extends Trigger {
	private float width = 0;
	private float height = 0;
	
	public BoxTrigger(Actor actor,float w,float h) {
		this.width = w;
		this.height = h;
		this.actor = actor;
	}

	public BoxTrigger(Actor actor){
		this.actor = actor;
		this.width = (float)actor.getShape().getBounds2D().getWidth();
		this.height = (float)actor.getShape().getBounds2D().getHeight();
	}

	@Override
	public void isTrigger(Actor a) {
		if(actor != a){
			Area own = new Area(getShape());
			Area other = new Area(a.getShape());
			own.intersect(other);

			if(own.isEmpty() == false && isOn == false){
				for(Script script : actor.getScripts()){
					script.TriggerHit(a, true);
				}
				
				isOn = true;
			}else if(own.isEmpty() == true && isOn == true){
				for(Script script : actor.getScripts()){
					script.TriggerHit(a, false);
				}
				isOn = false;
			}
		}
	}

	@Override
	public Shape getShape() {
		float x = actor.getCenter().x;
		float y = actor.getCenter().y;
		return new Rectangle2D.Double(x - width/2,y - height/2,width,height);
	}
}
