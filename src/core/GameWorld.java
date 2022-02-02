package core;

import scene.Scene;
import view.ViewPort;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld {
	
	
	/*??????????????????????¦Ë?????*/
	public static final float RETA = 10;
	
	public static ViewPort view = null;
	
	public static List<Scene> scenes = new CopyOnWriteArrayList<Scene>();
	
	public static Scene getSceneByName(String name){
		for(Scene scene : scenes){
			if(scene.getName() != null && scene.getName().equals(name)){
				return scene;
			}
		}
		return null;
	}
	
}
