package scene;
/**
 *
 * Scene:chang
 */

import actor.Actor;
import animation.Animation;
import animation.Spirit;
import core.GameWorld;
import core.Render;
import ui.UIPanel;
import world.AbstractWorld;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public abstract class Scene extends JPanel implements AbstractWorld,Render{
	
	/**
	 * Scene是场景的意思
	 */
	private static final long serialVersionUID = 1L;
	private Spirit spirit = null;
	private Animation animation = null;
	protected int width = 0;
	protected int height = 0;
	protected List<Actor> actors = null;
	protected UIPanel panel = null;

	public Scene() {
		GameWorld.scenes.add(this);
	}
	
	

	public Spirit getSpirit() {
		return spirit;
	}



	public void setSpirit(Spirit spirit) {
		this.spirit = spirit;
	}



	public Animation getAnimation() {
		return animation;
	}



	public void setAnimation(Animation animation) {
		this.animation = animation;
	}



	@Override
	public void renderShape(Graphics2D g2d) {
		// TODO Auto-generated method stub
	}

	@Override
	public void renderBg(Graphics2D g2d) {
		if(spirit != null){
			g2d.drawImage(spirit.getOneFrame(),0,0,
					width, height,  null);
		}
		
		if(animation != null && animation.getStatus() != Animation.NoStart){
			g2d.drawImage(animation.getOneFrame(),0,0,
					width, height,  null);
		}
	}

	@Override
	public void renderBorder(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderAxis(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderTriggerBorder(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
	public Actor getActorByName(String name){
		Actor m = null;
		for(Actor actor : actors){
			if(actor.getName().equals(name)){
				m = actor;
				break;
			}
		}
		return m;
	}
	
	public void setUIPanel(UIPanel panel){
		this.panel = panel;
	}
}
