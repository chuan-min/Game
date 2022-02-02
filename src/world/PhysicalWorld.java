package world;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import actor.Actor;
import actor.PhyActor;
import script.Script;

public class PhysicalWorld extends Thread implements ContactListener,AbstractWorld{
	private int velocityIterations = 10;      
    private int positionIterations = 8;
    
    private List<Actor> actors = null;
    
    public boolean isStop = false;
    private float time_step = 2.0f/60.0f;
    private Vec2 gravity = null;
    
	private World world = null;
	
	public PhysicalWorld(float time_step,float gravity) {
		
		this.time_step = time_step;
		 /**?????????*/  
        this.gravity=new Vec2(0,gravity);  
          
        /**????????????*/  
        world=new World(this.gravity, true); 
        
        actors = new CopyOnWriteArrayList<Actor>();
        
        /**?????????????§Ö????????*/  
        world.setContactListener(this);
	}
	
	public PhysicalWorld(float gravity) {
		 /**?????????*/  
       this.gravity=new Vec2(0,gravity);  
         
       /**????????????*/  
       world=new World(this.gravity, true);
       
       actors = new CopyOnWriteArrayList<Actor>();
       
       /**?????????????§Ö????????*/  
       world.setContactListener(this);
	}
	
	@Override
	public void run() {
		while(isStop == false){
			world.step(time_step, velocityIterations, positionIterations);
			
//			for(Actor actor : actors){
//				for(Script script : actor.getScripts()){
//					script.update();
//				}
//			}
			
			try {
				Thread.sleep(TimeLine.timeFragment);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addActor(Actor actor){
		if(actor instanceof PhyActor){
			PhyActor actor2 = (PhyActor)actor;
		    actor2.init(world);
		}
		actors.add(actor);
	}
	
	@Override
	public void removeActor(Actor actor) {
		if(actor instanceof PhyActor){
			PhyActor actor2 = (PhyActor)actor;
			world.destroyBody(actor2.body);
		}
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
	public void beginContact(Contact contact) {
		PhyActor a = (PhyActor)contact.getFixtureA().getBody().getUserData();
		PhyActor b = (PhyActor)contact.getFixtureB().getBody().getUserData();
		
		for(Script script : a.getScripts()){
			script.phyCollisionBegin(b);
		}
		
		for(Script script : b.getScripts()){
			script.phyCollisionBegin(a);
		}
	}

	@Override
	public void endContact(Contact contact) {
		PhyActor a = (PhyActor)contact.getFixtureA().getBody().getUserData();
		PhyActor b = (PhyActor)contact.getFixtureB().getBody().getUserData();
		
		for(Script script : a.getScripts()){
			script.phyCollisonEnd(b);
		}
		
		for(Script script : b.getScripts()){
			script.phyCollisonEnd(a);
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

	@Override
	public void removeActor(String actorName) {
		// TODO Auto-generated method stub
		
	}
	
}
