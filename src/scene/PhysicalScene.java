package scene;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

import script.Script;
import actor.Actor;
import world.PhysicalWorld;
import world.RenderWorld;
import world.TriggerWorld;


public class PhysicalScene extends Scene {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PhysicalWorld physicalWorld = null;
    public RenderWorld renderWorld = null;
    private TriggerWorld triggerWorld = null;
   
    
	public PhysicalScene(int width,int height,Color color,float t,float g) {
		super();
		this.width = width;
		this.height = height;
		setSize(width, height);
		setBackground(color);
		actors = new CopyOnWriteArrayList<Actor>();
		physicalWorld = new PhysicalWorld(t, g);
		renderWorld = new RenderWorld(this, getWidth(),getHeight(), getBackground());
		triggerWorld = new TriggerWorld();
		renderWorld.setScene(this);
	}
	
	@Override
	public void addActor(Actor actor){
		for(Script script : actor.getScripts()){
			addKeyListener(script);
			addMouseListener(script);
		}
		
		actor.setScene(this);
		actors.add(actor);
		physicalWorld.addActor(actor);
		renderWorld.addActor(actor);
		triggerWorld.addActor(actor);
		
		for(Script script : actor.getScripts()){
			script.start();
		}
	}

	@Override
	public void removeActor(Actor actor) {
		triggerWorld.removeActor(actor);
		renderWorld.removeActor(actor);
		physicalWorld.removeActor(actor);
		actors.remove(actor);
		for(Script script : actor.getScripts()){
			this.removeKeyListener(script);
			this.removeMouseListener(script);
		}
		actor.setScene(null);
	}

	@Override
	public void startWorld() {
		physicalWorld.startWorld();
		renderWorld.startWorld();
		triggerWorld.startWorld();
		if(panel != null){
			renderWorld.setUIPanel(panel);
		}
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
		triggerWorld.removeActor(actorName);
		renderWorld.removeActor(actorName);
		physicalWorld.removeActor(actorName);
	}
	
	
}
