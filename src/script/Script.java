package script;

import actor.Actor;
import actor.PhyActor;
import control.UserContorl;
import core.HasName;

/**
 * Script:½Å±¾
 *
 */
public abstract class Script extends UserContorl implements HasName{
	private String name = "script";
	protected Actor actor = null;

	public String getName() {
		return name;
	}

	public Script setName(String name) {
		this.name = name;
		return this;
	}
	
	public void setActor(Actor actor){
		this.actor = actor;
	}
	
	public Actor getActor(){
		return actor;
	}
	
	public void phyCollisionBegin(PhyActor a){}
	
	public void phyCollisonEnd(PhyActor a){}
	
	public void update(){}
	
	public void start(){}
	
	public void TriggerHit(Actor a,boolean isOn){}

}
