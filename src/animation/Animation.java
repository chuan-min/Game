package animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Animation:¶¯»­
 */
public class Animation {
	
	public static final int Active = 0;
	public static final int Stop = 1;
	public static final int Suspended = 2;
	public static final int NoStart = 3;
	
	private boolean isActiveLock = false;
	private Spirit defaultActiveSpirit = null;
	private Spirit activeSpirit = null;
	private List<Spirit> spirits = new ArrayList<Spirit>();
	public Animation() {
		init();
	}
	
	public void init(){
		
	}
	public void setActive(String spiritName){
		if(isActiveLock == true){
			return;
		}
		//?????????????¦Ì??????§¹
		if(activeSpirit.getName().equals(spiritName) == true){
			return;
		}else {
			activeSpirit.stop();
			for(Spirit spirit : spirits){
				if(spirit.getName().equals(spiritName)){
					activeSpirit = spirit;
					activeSpirit.start();
					break;
				}
			}
		}
		
	}

	public void setDefaultActive(String spiritName){
		for(Spirit spirit : spirits){
			if(spirit.getName().equals(spiritName)){
				defaultActiveSpirit = spirit;
				break;
			}
		}
	}

	public void setDefaultActive(Spirit spirit){
		defaultActiveSpirit = spirit;
	}
	
	public BufferedImage getOneFrame(){
		if(activeSpirit == null){
			System.out.println("??????¦Ä???");
		}
		return activeSpirit.getOneFrame();
	}
	
	public void start(){
		if(defaultActiveSpirit != null){
			activeSpirit = defaultActiveSpirit;
			activeSpirit.start();
		}else {
			if(spirits.size() != 0){
				defaultActiveSpirit = spirits.get(0);
				activeSpirit = defaultActiveSpirit;
				activeSpirit.start();
			}else {
				System.out.println("??§Ø???????");
			}
		}
	}
	
	public void suspended(){
		if(activeSpirit != null){
			activeSpirit.suspended();
		}else {
			System.out.println("??????¦Ä????????????");
		}
	}
	
	public void stop(){
		if(activeSpirit != null){
			activeSpirit.stop();
		}else {
			System.out.println("??????¦Ä???");
		}
	}
	
	public void addSpirit(Spirit spirit){
		spirits.add(spirit);
	}
	
	public int getStatus(){
		if(activeSpirit != null){
			return activeSpirit.getState();
		}
		return Animation.NoStart;//????¦Ä???
	}
	
	public Animation setActiveLock(boolean lock){
		isActiveLock = lock;
		return this;
	}
}
