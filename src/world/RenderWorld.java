package world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import actor.Actor;
import scene.Scene;
import script.Script;
import ui.UIPanel;
import static core.GameWorld.*;

/**
 * RenderWorld£∫ªÊ÷∆ ¿ΩÁ
 */
public class RenderWorld extends Thread implements AbstractWorld{
	
	private List<Actor> actors = null;
	private UIPanel panel = null;
	public boolean isBorderRender = false;
	public boolean isAxisRender = false;
	public boolean isTriggerBorder = false;
	public boolean isStop = false;
	
	private BufferedImage image = null;
	private Graphics2D g2d = null;
	private Color color = null;
	private int width = 0;
	private int height = 0;
	private Scene scene = null;
	private float x = 10;
	private float y = 20;
	
	public RenderWorld(Scene scene,int width,int height,Color color){
		this.color = color;
		this.height = height;
		this.width = width;
		this.scene = scene;
		actors = new CopyOnWriteArrayList<Actor>();
		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	}

	@Override
	public void run() {
		Graphics2D ig = (Graphics2D)image.getGraphics();
		ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		while(isStop == false){
			ig.setColor(color);
			
			//?????????????
			ig.fillRect(0, 0, width, height);
			
			//????????????
			scene.renderBg(ig);
			
			//?????ß÷?actor????????????????????
			for(Actor actor : actors){
				actor.renderBg(ig);
				actor.renderShape(ig);
			}
			
			//???????????????????
			if(isBorderRender == true){
				for(Actor actor : actors){
					actor.renderBorder(ig);
				}
			}
			
			//????????????????????
			if(isAxisRender == true){
				for(Actor actor : actors){
					actor.renderAxis(ig);
				}
			}
			
			//?????????????????????????
			if(isTriggerBorder == true){
				for(Actor actor : actors){
					actor.renderTriggerBorder(ig);
				}
			}
			
			//ui??‚Ï???????????????
			if(panel != null){
				panel.render(ig);
			}
			
			ig.setColor(Color.white);
			
			if(isBorderRender == true){
				ig.drawString("???????????", x, y);
				y += 14;
			}
			
			if(isAxisRender == true){
				ig.drawString("????????????", x, y);
				y += 14;
			}
			
			if(isTriggerBorder == true){
				ig.drawString("?????????????????", x, y);
				y += 14;
			}
			
			y = 20;
			//??????????????????????
			g2d = (Graphics2D)scene.getGraphics();
			if(g2d != null){
				g2d.drawImage(image,view.x, view.y, null);
			}
			
			for(Actor actor : actors){
				for(Script script : actor.getScripts()){
					script.update();
				}
			}
			
			try {
				sleep(TimeLine.timeFragment);
				TimeLine.curTime += TimeLine.timeFragment;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void addActor(Actor actor){
		actors.add(actor);
	}
	
	@Override
	public void removeActor(Actor actor) {
		actors.remove(actor);
	}
	
	@Override
	public void removeActor(String actorName) {
		// TODO Auto-generated method stub
		
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
	
	public void setUIPanel(UIPanel panel){
		this.panel = panel;
	}
	
	public void setScene(Scene scene){
		this.scene = scene;
	}
}
