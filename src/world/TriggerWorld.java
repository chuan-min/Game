package world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import actor.Actor;
import trigger.Trigger;

public class TriggerWorld extends Thread implements AbstractWorld{
	private List<Actor> actors = null;
	
	public TriggerWorld() {
		actors = new CopyOnWriteArrayList<Actor>();
	}
	
	@Override
	public void run() {
		while(true){
			for(Actor actor1 : actors){
				for(Actor actor2 : actors){
					if(actor2.isBeTriggered() == true){
						for(Trigger trigger : actor1.getTriggers()){
							trigger.isTrigger(actor2);
						}
					}
				}
			}
			
			try {
				sleep(TimeLine.timeFragment);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void addActor(Actor actor) {
		actors.add(actor);
	}
	
	@Override
	public void removeActor(Actor actor) {
		actors.remove(actor);
	}
	
	@Override
	public void startWorld() {
		start();
	}
	
	@Override
	public void stopWorld() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void suspendedWorld() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeActor(String actorName) {
		// TODO Auto-generated method stub
		
	}
	
}
